import java.util.*;

//class w/ weighted edges
class WeightedGraph{
  HashSet<WNode> setNodes;

  public WeightedGraph(){
    setNodes = new HashSet<WNode>();
  }

  void addNode(final int nodeVal){
    WNode add = new WNode(nodeVal);
    setNodes.add(add);
  }

  void addWeightedEdge(final WNode first, final WNode second, final int edgeWeight){
    first.edges.put(second, edgeWeight);
  }

  void removeDirectedEdge(final WNode first, final WNode second){
    first.edges.remove(second);
  }

  HashSet<WNode> getAllNodes(){
    return setNodes;
  }

}
