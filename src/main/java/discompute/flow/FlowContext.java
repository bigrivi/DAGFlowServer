package discompute.flow;

import discompute.flow.model.Flow;
import discompute.flow.model.Task;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wyj on 2016/11/19.
 */
public class FlowContext implements Serializable {


    private transient ExecutorService executorService;
    private Flow flow; // 流程


    public FlowContext(){
        executorService = Executors.newFixedThreadPool(3);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Flow getFlow() {
        return flow;
    }

    public void execFlow(List<Task> starts){
        getFlow().startFlow(starts, this);
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public void shutdown(){
        getFlow().finishFlow(this);
    }


}
