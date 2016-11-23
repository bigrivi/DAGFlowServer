package discompute.flow.define;

import discompute.graph.Node;
import discompute.util.ExecuteStatus;

/**
 * Created by wyj on 2016/11/19.
 */
public class TaskDefine<T extends Node> extends Node<T> {

    public TaskDefine(String id ,String script){
        super(id);
        this.script = script;
    }

    private String flowId;//流程代码

    private ExecuteStatus status = ExecuteStatus.Nonactive;

    private String script;//流程参数

    private Integer maxParallel; //最大并行度

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public ExecuteStatus getStatus() {
        return status;
    }

    public void setStatus(ExecuteStatus status) {
        this.status = status;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Integer getMaxParallel() {
        return maxParallel;
    }

    public void setMaxParallel(Integer maxParallel) {
        this.maxParallel = maxParallel;
    }
}
