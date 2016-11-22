package discompute.graph;

import java.io.Serializable;

/**
 * Created by patrick on 2016/11/19.
 */
public class Edge implements Serializable{

    private String startNode;

    private String endNode;

    public Edge(){

    }

    public Edge(String startNode, String endNode){
        super();
        this.startNode = startNode;
        this.endNode = endNode;
    }


    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    @Override
    public boolean equals(Object obj){
        if(this.getClass() == obj.getClass()) {
            Edge edge = (Edge) obj;
            if(edge.getEndNode() != null && edge.getEndNode() == endNode && edge.getStartNode() != null && edge.startNode == startNode){
                return true;
            }
        }
        return false;
    }
}
