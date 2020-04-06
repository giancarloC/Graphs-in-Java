import java.util.*;

public class GridNode{
  private int x, y;
  String nodeVal;
  HashSet<GridNode> neighbors;

  public GridNode(int x, int y, String nodeVal){
    this.x = x;
    this.y = y;
    this.nodeVal = nodeVal;
    neighbors = new HashSet<>();
  }

  int getX(){
    return x;
  }

  int getY(){
    return y;
  }

  void addNeighbor(GridNode node){
    neighbors.add(node);
  }

  void removeNeighbor(GridNode node){
    neighbors.remove(node);
  }

}
