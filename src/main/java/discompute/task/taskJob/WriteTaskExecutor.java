package discompute.task.taskJob;

import discompute.flow.FlowContext;
import discompute.flow.model.Task;
import discompute.task.TaskExecResult;
import discompute.task.TaskExecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by patrick on 2016/11/19.
 */
public class WriteTaskExecutor implements TaskExecutor {
    @Override
    public TaskExecResult exec(Task task, FlowContext flowContext) throws Exception {
        System.out.println(task.getId() + "start");
        int i  = 0;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(task.getScript())));
        while (i < 9){

            bufferedWriter.write(new String (task.getId() + (int)(Math.random()*10)));

            bufferedWriter.newLine();

            Thread.sleep(1000);

            i++;
        }
        bufferedWriter.close();

        System.out.println(task.getId() + "stop");
        return null;
    }
}
