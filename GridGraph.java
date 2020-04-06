import java.util.*;

public class GridGraph{
  HashSet<GridNode> nodes;

  public GridGraph(){
    nodes = new HashSet<>();
  }

  private boolean isNeighbor(GridNode first, GridNode second){
    int x1 = first.getX();
    int y1 = first.getY();
    int x2 = second.getX();
    int y2 = second.getY();

    double distance = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
    if(distance == 1)
      return true;
    return false;
  }

  void addGridNode(final int x, final int y, final String nodeVal){
    GridNode node = new GridNode(x, y, nodeVal);
    nodes.add(node);
  }

  void addUndirectedEdge(final GridNode first, final GridNode second){
    if(isNeighbor(first, second)){
      first.addNeighbor(second);
      second.addNeighbor(first);
    }
  }

  void removeUndirectedEdge(final GridNode first, final GridNode second){
    first.removeNeighbor(second);
    second.removeNeighbor(first);
  }

  HashSet<GridNode> getAllNodes(){
    return nodes;
  }
}


















/*end of file*/
