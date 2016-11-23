package discompute.task.taskJob;

import discompute.flow.FlowContext;
import discompute.flow.model.Task;
import discompute.task.TaskExecResult;
import discompute.task.TaskExecutor;

import java.io.*;

/**
 * Created by wyj on 2016/11/19.
 */
public class ReadAndWriteExecutor implements TaskExecutor {
    @Override
    public TaskExecResult exec(Task task, FlowContext flowContext) throws Exception {

        System.out.println(task.getId() + "start");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(task.getScript()+ "end.txt")));

//        int i  = 0;

        File file = new File(task.getScript());

        File[] files = file.listFiles();

        for (File tmp : files){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tmp));

            String line = bufferedReader.readLine();

            while (line != null){

                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = bufferedReader.readLine();
            }

            bufferedReader.close();

        }

        bufferedWriter.close();


        System.out.println(task.getId() + "stop");
        return null;
    }
}
