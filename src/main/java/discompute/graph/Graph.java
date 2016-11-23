package discompute.graph;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyj on 2016/11/19.
 */
public class Graph<T extends Node> implements Serializable {

    private List<T> nodes = Lists.newArrayList();

    private List<Edge> edges = Lists.newArrayList();

    public Graph(){
        super();
    }


    public List<T> getNodes() {
        return nodes;
    }

    public void setNodes(List<T> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void init(String expr){
        //todo
    }

    public boolean init(){
        for (T node : nodes){
            node.setGraph(this);
        }

        for (Edge edge : edges){
            T start = getNodeFromId(edge.getStartNode());
            T end = getNodeFromId(edge.getEndNode());

            if(start != null && end != null){
                start.addChild(end);
                end.addParent(start);
            }
        }

        //checkDAG
        return checkDAG();
    }

    //根据string id 获得 node
    public T getNodeFromId(String id){
        for (T node : nodes){
            if(node.getId().equals(id)){
                return node;
            }
        }
        return null;
    }

    // 检查有向无环图
    public boolean checkDAG(){
        try {
            for (T node : nodes) {
                checkNode(node, node);
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    private void checkNode(Node<T> node, Node<T> checkNode) throws Exception {
        for ( Node tmp : checkNode.getParents()){
            if (tmp == node || tmp.equals(node)){
                throw new Exception("found cycle on node");
            }
            checkNode(node, tmp);
        }
    }

    public Graph addNode(T node){
        if(!nodes.contains(node)){
            nodes.add(node);
        }
        return this;
    }

    public Graph addEdge(Edge edge){
        if(!edges.contains(edge)){
            edges.add(edge);
        }
        return this;
    }

    public List<T> getStartNodes(){
        List<T> list = Lists.newArrayList();
        for (T node : nodes){
            if(node.getParents().size() == 0) {
                list.add(node);
            }
        }
        return list;
    }
}
