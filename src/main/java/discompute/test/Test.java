package discompute.test;

import discompute.flow.FlowEngine;
import discompute.flow.Worker;
import discompute.flow.model.Flow;
import discompute.flow.model.Task;
import discompute.graph.Edge;

/**
 * Created by wyj on 2016/11/19.
 */
public class Test {

    public static void main(String args[]){
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

        FlowEngine flowEngine = new FlowEngine();
        FlowEngine.addWorkers(new Worker("127.0.0.1", 8888));
        FlowEngine.addWorkers(new Worker("127.0.0.1", 9888));
        flowEngine.fireFlow(flow);

    }
}
