import java.util.*;

//represents a position in a graph
class Node{
  String nodeVal;
  HashSet<Node> edges;

  public Node(String nodeVal){
    this.nodeVal = nodeVal;
    edges = new HashSet<Node>();
  }
}
