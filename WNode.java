import java.util.*;

//class for nodes with weighted edges
class WNode{
  int nodeVal;
  HashMap<WNode, Integer> edges;

  public WNode(int nodeVal){
    this.nodeVal = nodeVal;
    edges = new HashMap<>();
  }
}
