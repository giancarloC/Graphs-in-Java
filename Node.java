import java.util.*;

//represents a position in a graph
class Node{
  int nodeVal;
  HashSet<Node> edges;

  public Node(int nodeVal){
    this.nodeVal = nodeVal;
    edges = new HashSet<Node>();
  }
}
