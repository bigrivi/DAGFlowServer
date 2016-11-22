package discompute.task.taskJob;
import discompute.task.TaskExecResult;
import discompute.task.TaskExecutor;
import discompute.flow.FlowContext;
import discompute.flow.model.Task;

public class NothingTaskExecutor implements TaskExecutor {



	@Override
	public TaskExecResult exec(Task task, FlowContext flowContext) {
		int i  = 0;
		while (i < 50){
			System.out.println("do no thing,taskId:"+task.getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}

		return null;
	}

}
