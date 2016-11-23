package discompute.flow.model;

import com.google.common.collect.Lists;
import discompute.flow.FlowEngine;
import discompute.flow.Worker;
import discompute.service.FlowClient;
import discompute.task.TaskExecutor;
import discompute.flow.FlowContext;
import discompute.flow.define.TaskDefine;

import java.util.List;


/**
 * Created by wyj on 2016/11/19.
 */
public class Task extends TaskDefine<Task>{

    private TaskExecutor taskExecutor;

    private List<Task> unPreparedParents = Lists.newArrayList();

    private boolean isPending = true;

    public Task(String id, String script, String type){

        super(id,script);

        taskExecutor = FlowEngine.getTaskExecutor(type);

    }

    //resign task
    public void execSingleTask(FlowContext flowContext, Worker worker) throws Exception {

        FlowContext tmp = flowContext.deepClone();
        tmp.setCurrentTask(this);
        FlowClient.instance().call(tmp,worker);

    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public List<Task> getUnPreparedParents() {
        return unPreparedParents;
    }

    public void setUnPreparedParents(List<Task> unPrepareParents) {
        this.unPreparedParents = unPrepareParents;
    }

    public synchronized void removePreparedParents(Task task) {
        unPreparedParents.remove(task);
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }
}
