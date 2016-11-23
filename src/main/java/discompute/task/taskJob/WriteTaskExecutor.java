package discompute.task.taskJob;

import discompute.flow.FlowContext;
import discompute.flow.model.Task;
import discompute.task.TaskExecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyj on 2016/11/19.
 */
public class WriteTaskExecutor implements TaskExecutor {
    @Override
    public Map<String,String> exec(Task task, FlowContext flowContext) throws Exception {
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
        return new HashMap<String,String>();
    }
}
