package discompute.test;

import discompute.flow.FlowEngine;
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
        flowEngine.fireFlow(flow);
//        System.exit(0);

//        Flow flow = new Flow();
//
//        flow.addNode(new Task("abc","test","nothing")).addNode(new Task("abcd","test","nothing"))
//                .addNode(new Task("111","test","nothing"))
//                .addNode(new Task("222","test","nothing"));
////                .addNode(new Task("333","test","nothing"))
////                .addNode(new Task("444","test","nothing"))
////                .addNode(new Task("555","test","nothing"))
////                .addNode(new Task("666","test","nothing"))
////                .addNode(new Task("777","test","nothing"));
//
//        flow.addEdge(new Edge("abc","abcd"))
//                .addEdge(new Edge("111","abcd"))
//                .addEdge(new Edge("222","abcd"));
////                .addEdge(new Edge("333","abcd"))
////                .addEdge(new Edge("444","abcd"));
//
//        flow.init();
//
//        FlowEngine flowEngine = new FlowEngine();
//        flowEngine.fireFlow(flow);
    }
}
