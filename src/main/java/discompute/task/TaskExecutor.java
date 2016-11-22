package discompute.task;

import discompute.flow.FlowContext;
import discompute.flow.model.Task;

public interface TaskExecutor {

	/**
	 * 任务执行器
	 * @param task 当前任务
	 * @param flowContext 任务上下文
	 * @return 执行结果，允许返回null
	 * @throws Exception 
	 */
	TaskExecResult exec(Task task, FlowContext flowContext) throws Exception;
	
}
