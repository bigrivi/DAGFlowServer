package discompute.flow;



import discompute.task.TaskExecutor;
import discompute.task.taskJob.NothingTaskExecutor;
import discompute.flow.model.Flow;
import discompute.flow.model.Task;
import discompute.task.taskJob.ReadAndWriteExecutor;
import discompute.task.taskJob.WriteTaskExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wyj on 2016/11/19.
 */
public class FlowEngine{

    private static Map<String,TaskExecutor> executorMap = new HashMap();

    static {
        registerTaskExecutor("nothing",new NothingTaskExecutor());
        registerTaskExecutor("writer",new WriteTaskExecutor());
        registerTaskExecutor("reader",new ReadAndWriteExecutor());
    }

    private static void registerTaskExecutor (String expr, TaskExecutor taskExecutor){
        executorMap.put(expr, taskExecutor);
    }

    public static  synchronized TaskExecutor getTaskExecutor(String expr){
        return executorMap.get(expr);
    }

    public void fireFlow(Flow flow){
        FlowContext flowContext = new FlowContext();
        flowContext.setFlow(flow);
        execFlow(flow,flowContext);

    }

    public void execFlow(Flow flow, FlowContext flowContext){
        List<Task> starts = flow.getStartNodes();
        execFlow(starts,flowContext);
    }

    public void execFlow(List<Task> starts,  FlowContext flowContext){

        flowContext.execFlow(starts);
        flowContext.shutdown();
    }
}
