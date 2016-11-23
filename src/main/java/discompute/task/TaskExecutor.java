package discompute.task;

import discompute.flow.FlowContext;
import discompute.flow.model.Task;

import java.io.Serializable;
import java.util.Map;

public interface TaskExecutor extends Serializable{

	Map<String, String> exec(Task task, FlowContext flowContext) throws Exception;
	
}
