import java.util.HashSet;
import java.util.Set;

class Node{
  String nodeVal;
  HashSet<Node> edges;

  Node(String nodeVal){
    this.nodeVal = nodeVal;
  }
}

//graph class to represent a maze
class Graph{
  HashSet<Node> nodes;

  void addNode(final String nodeVal){
    nodes.add(new Node(nodeVal));
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
    return nodes;
  }
}

//main class to test methods
public class Main{

}
