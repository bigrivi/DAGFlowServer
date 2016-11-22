package esflowengine.engine.task;

import org.junit.Test;

import esflowengine.model.FlowContext;
import esflowengine.model.FlowTask;

public class HttpTaskExecutorTest {

	@Test
	public void test() throws Exception {
		HttpTaskExecutor e = new HttpTaskExecutor();
		FlowTask task = new FlowTask();
		task.setScript("http://www.163.com");
		e.exec(task, new FlowContext());
	}

}
