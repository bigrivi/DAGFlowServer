package discompute.flow.model;

import discompute.flow.FlowContext;
import discompute.flow.FlowEngine;
import discompute.flow.Worker;
import discompute.flow.define.FlowDefine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * Created by wyj on 2016/11/19.
 */
public class Flow extends FlowDefine<Task> {


    private Vector<Task> activeTasks  = new Vector<>();

    @Override
    public boolean init() {
        super.init();
        //init parent status;
        initParentsStatus();

        //控制线程池停止
        initActiveTasks();

        return true;

    }

    private void initParentsStatus() {
        for (Task task : this.getNodes()) {
            task.setUnPreparedParents(task.getParents());
        }
    }

    private void initActiveTasks(){
        for (Task task : this.getNodes()){
            activeTasks.add(task);
        }
    }

    public int getActiveNodeSize(){
        return activeTasks.size();
    }

    public void startFlow(List<Task> starts,  FlowContext flowContext){
        System.out.println( "flow ----------------------------------------------start");
        ScheduledExecutorService flowFire  = Executors.newScheduledThreadPool(1);
        flowFire.schedule(new Executor(starts, flowContext),10000, TimeUnit.MILLISECONDS);
//        flowFire.execute(new Executor(starts, flowContext));
        flowFire.shutdown();
        System.out.println( "flow ----------------------------------------------stop");
    }

    public void finishFlow(FlowContext flowContext){
        ExecutorService finishFire = Executors.newFixedThreadPool(1);
        finishFire.execute(new ExecutorFinish(flowContext));
        finishFire.shutdown();
    }

    private void execFlow(final List<Task> starts, final FlowContext flowContext) {

        for (final Task task : starts) {


            flowContext.getExecutorService().execute( new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (task){
                            if(task.getUnPreparedParents().size() != 0 || !task.isPending()) return ;
                            task.setPending(false);
                        }

                        //todo resign task
                        int size = FlowEngine.getWorkers().size();
                        Worker worker = FlowEngine.getWorkers().get((int) (Math.random() * size));


                        task.execSingleTask(flowContext , worker);

                        for (Task child : task.getChildren()) {
                            child.removePreparedParents(task);
                            execFlow(task.getChildren(), flowContext);
                        }
                    } catch (Exception e) {
                        //todo
                    }

                    // remove exed node
                    activeTasks.remove(task);
                }
            });
        }
    }

    class Executor implements Runnable{

        private List<Task> starts ;
        private FlowContext flowContext;

        public Executor(List<Task> starts, FlowContext flowContext){
            this.starts = starts;
            this.flowContext = flowContext;
        }
        @Override
        public void run() {
            execFlow(starts,flowContext);
        }
    }

    class ExecutorFinish implements Runnable{

        private FlowContext flowContext;

        public ExecutorFinish( FlowContext flowContext){
            this.flowContext = flowContext;
        }

        @Override
        public void run() {

            try {
                while (true){

                    if(getActiveNodeSize() == 0)
                        break;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                //todo
            }

           flowContext.getExecutorService().shutdown();
        }
    }
}
