package discompute.test;

import discompute.flow.FlowContext;
import discompute.flow.model.Flow;
import discompute.flow.model.Task;
import discompute.graph.Edge;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyj on 2016/11/23.
 */
public class Tests {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        FlowContext flowContext = new FlowContext();
        Flow flow = new Flow();

        flow.addNode(new Task("abc","D:/files/a0.txt","writer"))
                .addNode(new Task("abcd","D:/files/","reader"))
                .addNode(new Task("111","D:/files/a2.txt","writer"))
                .addNode(new Task("222","D:/files/a3.txt","writer"))
                .addNode(new Task("333","D:/files/a4.txt","writer"))
                .addNode(new Task("444","D:/files/a5.txt","writer"))
                .addNode(new Task("555","D:/filess/a6.txt","writer"))
                .addNode(new Task("666","D:/filess/a7.txt","writer"))
                .addNode(new Task("777","D:/filess/a8.txt","writer"));

        flow.addEdge(new Edge("abc","abcd"))
                .addEdge(new Edge("111","abcd"))
                .addEdge(new Edge("222","abcd"))
                .addEdge(new Edge("333","abcd"))
                .addEdge(new Edge("444","abcd"));

        flow.init();

        flowContext.setFlow(flow);

        Map<String,String> map = new HashMap<>();
        map.put("abc","123");
        flowContext.setParams(map);

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                                 new File("F:/flow.txt")));
                oo.writeObject(flowContext);
                 System.out.println("flow对象序列化成功！");
                 oo.close();


        /////


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                                 new File("F:/flow.txt")));
                FlowContext flowContext1 = (FlowContext) ois.readObject();
                 System.out.println("flow对象反序列化成功！");

        System.out.println(flowContext1.getFlow().getNodes().get(0).getId());
        System.out.println(flowContext1.getFlow().getNodes().get(1).getId());
        System.out.println(flowContext1.getFlow().getNodes().get(2).getId());
        System.out.println(flowContext1.getFlow().getNodes().get(3).getId());

        System.out.println(flowContext1.getParams().get("abc"));



    }
}
