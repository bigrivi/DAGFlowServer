package discompute.flow.define;

import discompute.graph.Graph;
import discompute.graph.Node;
import discompute.util.ExecuteStatus;

/**
 * Created by patrick on 2016/11/19.
 */
public class FlowDefine<T extends Node> extends Graph<T> {

    private String flowId; //流程代码
    private String flowName; //名称
    private ExecuteStatus status = ExecuteStatus.Nonactive;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public ExecuteStatus getStatus() {
        return status;
    }

    public void setStatus(ExecuteStatus status) {
        this.status = status;
    }
}
