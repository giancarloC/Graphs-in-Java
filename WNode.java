import java.util.*;

//class for nodes with weighted edges
class WNode{
  String nodeVal;
  HashMap<WNode, Integer> edges;

  public WNode(String nodeVal){
    this.nodeVal = nodeVal;
    edges = new HashMap<>();
  }
}
