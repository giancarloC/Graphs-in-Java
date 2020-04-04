import java.util.*;

//class w/ directed edges
class DirectedGraph{
  HashSet<Node> setNodes;

  public DirectedGraph(){
    setNodes = new HashSet<Node>();
  }

  void addNode(final String nodeVal){
    Node add = new Node(nodeVal);
    setNodes.add(add);
  }

  void addDirectedEdge(final Node first, final Node second){
    first.edges.add(second);
  }

  void removeDirectedEdge(final Node first, final Node second){
    first.edges.remove(first);
  }

  HashSet<Node> getAllNodes(){
    return setNodes;
  }
}
