package discompute.flow;

import com.google.common.collect.Lists;
import discompute.flow.model.Flow;
import discompute.flow.model.Task;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wyj on 2016/11/19.
 */
public class FlowContext implements Serializable {


    private transient ExecutorService executorService = Executors.newFixedThreadPool(3);
    private Flow flow; // 流程
    private Task currentTask;
    private Map<String, String> params = new HashMap();
    private List<Worker> workers = Lists.newArrayList();


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


    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public FlowContext deepClone() throws Exception {
        // write a dag object into an object output stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        //read a dag object from an object input stream
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (FlowContext) objectInputStream.readObject();
    }
}
