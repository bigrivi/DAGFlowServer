package esflowengine.engine.task;

import esflowengine.model.FlowContext;
import esflowengine.model.FlowTask;
import org.junit.Test;

public class JavaScriptTaskExecutorTest {

	JavaScriptTaskExecutor executor = new JavaScriptTaskExecutor();
	@Test
	public void test() throws Exception {
		FlowTask task = new FlowTask();
		task.setScript("print('hello by javascript')");
		executor.exec(task, new FlowContext());
	}

}
