package discompute.graph;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyj on 2016/11/19.
 */
public class Node <T extends Node>implements Serializable{

    private String id;

    private String value;

    private Graph<T> graph;

    /**
     * 出度
     */
    private List<T> children = Lists.newArrayList();

    /**
     * 入度
     */
    private List<T> parents = Lists.newArrayList();

    public Node(){

    }

    public Node(String id){
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<T> getChildren() {
        return children;
    }

    public List<T> getParents() {
        return parents;
    }

    public Graph<T> getGraph() {
        return graph;
    }

    public void setGraph(Graph<T> graph) {
        this.graph = graph;
    }

    public Node addChild(T child){
        if(!children.contains(child)) {
            children.add(child);
        }
        return this;
    }

    public Node addParent(T parent){
        if(!parents.contains(parent)) {
            parents.add(parent);
        }
        return this;
    }

    @Override
    public boolean equals(Object obj){
        if(this.getClass() == obj.getClass()){
            Node node = (Node) obj;
            if(node.getId() == this.getId()){
                return true;
            }
        }
        return false;
    }
}
