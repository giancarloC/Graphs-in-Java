import java.util.*;

//generic graph class (undirected edges)
class Graph{
  HashSet<Node> setNodes;

  public Graph(){
    setNodes = new HashSet<Node>();
  }

  void addNode(final int nodeVal){
    Node add = new Node(nodeVal);
    setNodes.add(add);
  }

  void addUndirectedEdge(final Node first, final Node second){
    first.edges.add(second);
    second.edges.add(first);
  }

  void removeUndirectedEdge(final Node first, final Node second){
    first.edges.remove(first);
    second.edges.remove(first);
  }

  HashSet<Node> getAllNodes(){
    return setNodes;
  }
}
