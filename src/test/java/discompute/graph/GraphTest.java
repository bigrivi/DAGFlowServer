package discompute.graph;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by patrick on 2016/11/19.
 */
public class GraphTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void init() throws Exception {
        Graph<Node> graph = new Graph<Node>();
        boolean tes =  graph.addNode(new Node("abc")).addNode(new Node("def")).addNode(new Node("add")).addNode(new Node("ddf"))
                .addEdge(new Edge("abc","def")).addEdge(new Edge("abc","add")).init();
        System.out.println(tes);

        Graph<Node> graph1 = new Graph<Node>();
        boolean test =  graph1.addNode(new Node("abc")).addNode(new Node("def")).addNode(new Node("add")).addNode(new Node("ddf"))
                .addEdge(new Edge("abc","def")).addEdge(new Edge("def","add")).addEdge(new Edge("add","abc")).init();
        System.out.println(test);

        Graph<Node> graph2 = new Graph<Node>();
        boolean tests =  graph2.addNode(new Node("abc")).addNode(new Node("def")).addNode(new Node("add")).addNode(new Node("ddf")).addNode(new Node("abc"))
                .addEdge(new Edge("abc","def")).addEdge(new Edge("abc","add")).addEdge(new Edge("abc","add")).init();

        for (Node node : graph2.getNodes()){
            System.out.println(node.getId());
        }

        for (Edge edge : graph2.getEdges()){
            System.out.println(edge.getStartNode() + "->" + edge.getEndNode());
        }

        for (Node node : graph2.getStartNodes()){
            System.out.println(node.getId());
        }
    }

}