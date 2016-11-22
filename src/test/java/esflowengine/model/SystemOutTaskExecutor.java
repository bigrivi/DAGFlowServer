package esflowengine.model;

import esflowengine.engine.TaskExecResult;
import esflowengine.engine.TaskExecutor;

public class SystemOutTaskExecutor implements TaskExecutor {

	@Override
	public TaskExecResult exec(FlowTask task, FlowContext flowContext) throws InterruptedException {
		System.out.println(task.getTaskId());
		Thread.sleep(1000 * 3);
		return null;
	}

}
