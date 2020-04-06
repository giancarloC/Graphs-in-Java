import java.util.*;

//main class to test methods
public class Main{

  static Graph createRandomUnweightedGraphIter(int n){
    Graph g = new Graph();

    //adds nodes to graph
    int i;
    for(i = 0; i < n; i++){
      g.addNode(Integer.toString(i));
    }

    List<Node> list = new ArrayList<Node>(g.getAllNodes());
    Random rand = new Random();
    Node check1, check2;

    //loops through nodes, O(nlogn)
    int k;
    for(i = 0; i < n-1; i++){
      check1 = list.get(i);

      for(k = i+1; k < n; k++){

        //50% attaching nodes
        if(rand.nextBoolean()){
          check2 = list.get(k);
          g.addUndirectedEdge(check1, check2);
        }
      }
    }

    return g;
  }

  static Graph createLinkedList(int n){
    Graph g = new Graph();

    //adds nodes to graph
    int i;
    for(i = 0; i < n; i++){
      g.addNode(Integer.toString(i));
    }

    //adds directed edges to create linked list
    List<Node> list = new ArrayList<Node>(g.getAllNodes());

    for(i = 0; i < list.size()-1; i++){
      Node node1 = list.get(i);
      Node node2 = list.get(i+1);
      g.addUndirectedEdge(node1, node2);
    }

    return g;
  }

  static ArrayList<Node> BFTRecLinkedList(){
    GraphSearch gs = new GraphSearch();
    Graph g = createLinkedList(100);
    return gs.BFTRec(g);
  }

  static ArrayList<Node> BFTIterLinkedList(){
    GraphSearch gs = new GraphSearch();
    Graph g = createLinkedList(10000);
    return gs.BFTIter(g);
  }

  static DirectedGraph createRandomDAGIter(final int n){
    DirectedGraph g = new DirectedGraph();

    int i;
    for(i = 0; i < n; i++){
      g.addNode(Integer.toString(i));
    }

    List<Node> list = new ArrayList<Node>(g.getAllNodes());
    Random rand = new Random();
    Node check1, check2;

    //loops through nodes, O(nlogn)
    int k;
    for(i = 0; i < n-1; i++){
      check1 = list.get(i);

      for(k = i+1; k < n; k++){

        //50% attaching nodes
        if(rand.nextBoolean()){
          check2 = list.get(k);
          g.addDirectedEdge(check1, check2);
        }
      }
    }

    return g;
  }

  static WeightedGraph createRandomCompleteWeightedGraph(final int n){
    WeightedGraph w = new WeightedGraph();

    int i;
    for(i = 0; i < n; i++){
      w.addNode(Integer.toString(i));
    }

    List<WNode> nodes = new ArrayList<WNode>(w.getAllNodes());
    int numNodes = nodes.size();
    int k, randNum;
    Random rand = new Random();
    WNode node1, node2;
    for(i = 0; i < numNodes; i++){
      node1 = nodes.get(i);

      //adds edge to every other node
      for(k = 0; k < numNodes; k++){
        if(i != k){
          node2 = nodes.get(k);
          randNum = rand.nextInt() & Integer.MAX_VALUE; //grabs random positive int
          w.addWeightedEdge(node1, node2, randNum);
        }
      }
    }

    return w;
  }

  static WeightedGraph createWeightedLinkedList(final int n){
    WeightedGraph w = new WeightedGraph();
    int sameWeight = 1;

    int i;
    for(i = 0; i < n; i++){
      w.addNode(Integer.toString(i));
    }

    List<WNode> nodes = new ArrayList<WNode>(w.getAllNodes());
    int numNodes = nodes.size();
    int k;
    WNode node1, node2;
    for(i = 0; i < numNodes-1; i++){
      node1 = nodes.get(i);
      node2 = nodes.get(i+1);
      w.addWeightedEdge(node1, node2, sameWeight);
    }

    return w;
  }

  static HashMap<WNode, Integer> dijkstras(final WNode start){
    HashMap<WNode, Integer> distances = new HashMap<>();
    HashSet<WNode> finalized = new HashSet<>();
    distances.put(start, 0); //initializes starting node at 0

    WNode curr = start;
    WNode node, check;
    int newDistance, currDistance, oldDistance;
    while(curr != null && distances.containsKey(curr)){
      finalized.add(curr);
      currDistance = distances.get(curr);

      //loops through neighbors
      for(Map.Entry entry: curr.edges.entrySet()){
        node = (WNode)entry.getKey();

        //skip if already finalized
        if(finalized.contains(node))
          continue;

        //adds distance if not updated
        newDistance = (Integer) entry.getValue() + currDistance;
        if(!distances.containsKey(node)){
          distances.put(node, newDistance);
        }

        //updates distance if new distance is smaller
        else{
          oldDistance = distances.get(node);
          if(newDistance < oldDistance)
            distances.replace(node, newDistance);
        }
      }

      //changes curr to one w/ lowest distance that is not finalized
      curr = null;
      for(Map.Entry entry: distances.entrySet()){
        check = (WNode) entry.getKey();
        if(finalized.contains(check))
          continue;
        else if(curr == null)
          curr = check;
        else if(distances.get(check) < distances.get(curr))
          curr = check;
      }


    }

    return distances;
  }

  static GridGraph createRandomGridGraph(int n){
    GridGraph g = new GridGraph();

    int i, j;
    for(i = 0; i < n+1; i++){
      for(j = 0; j < n+1; j++){
        g.addGridNode(i, j, Integer.toString(i) + Integer.toString(j));
      }
    }

    Random rand = new Random();
    GridNode node1, node2;
    List<GridNode> nodes = new ArrayList<>(g.getAllNodes());
    for(i = 0; i < nodes.size(); i++){
      for(j = i+1; j < nodes.size(); j++){
        if(rand.nextBoolean()){
          //if not neighbors or the same node, the function won't do anything
          g.addUndirectedEdge(nodes.get(i), nodes.get(j));
        }
      }
    }

    return g;
  }

  static int manhattan(final GridNode source, final GridNode dest){
    int x1 = source.getX();
    int y1 = source.getY();
    int x2 = dest.getX();
    int y2 = dest.getY();

    int xDiff = x2-x1;
    if(xDiff < 0)
      xDiff = xDiff * -1;
    int yDiff = y2-y1;
    if(yDiff < 0)
      yDiff = yDiff * -1;

    return xDiff + yDiff;
  }

  static ArrayList<GridNode> astar(final GridNode sourceNode, final GridNode destNode){
    if(sourceNode == null || destNode == null){
      return null;
    }

    HashMap<GridNode, Integer> distances = new HashMap<>();
    HashMap<GridNode, GridNode> parents = new HashMap<>();
    HashSet<GridNode> finalized = new HashSet<>();

    distances.put(sourceNode, 0); //initializes starting node at 0
    parents.put(sourceNode, null);
    GridNode curr = sourceNode;
    GridNode check, parent;
    int h; //heuristic int
    int currDist, newDist, oldDist, hDist;

    //does astar
    while(curr != null){

      if(curr == destNode)
        break;

      finalized.add(curr);
      currDist = distances.get(curr);

      //loops through neighbors
      for(GridNode neighbor: curr.neighbors){
        //skip if already finalized
        if(finalized.contains(neighbor))
          continue;

        //adds distance if not updated
        newDist = 1 + currDist;
        if(!distances.containsKey(neighbor)){
          distances.put(neighbor, newDist);
          parents.put(neighbor, curr);
        }

        //updates distance if new distance is smaller
        else{
          oldDist = distances.get(neighbor);
          if(newDist < oldDist){
            distances.replace(neighbor, newDist);
            parents.replace(neighbor, curr);
          }
        }
      }

      //changes curr to one w/ lowest distance + heuristic that is not finalized
      curr = null;
      for(Map.Entry entry: distances.entrySet()){
        check = (GridNode) entry.getKey();
        hDist = manhattan(check, destNode) + distances.get(check);

        if(finalized.contains(check))
          continue;
        else if(curr == null)
          curr = check;
        else if(hDist < (distances.get(curr) + manhattan(curr, destNode)))
          curr = check;
      }
    }

    //destNode was not found
    if(curr == null)
      return null;

    //puts parents in array
    ArrayList<GridNode> backwards = new ArrayList<>();
    while(curr != sourceNode){
      backwards.add(curr);
      curr = parents.get(curr);
    }
    backwards.add(sourceNode);

    //reverses array
    int i;
    int size = backwards.size();
    ArrayList<GridNode> ret = new ArrayList<>();
    for(i = 0; i < size; i++){
      ret.add(i, backwards.get(size-i-1));
    }

    return ret;
  }

  //--------------------------------------------------------------------------
  //MAIN METHOD
  //--------------------------------------------------------------------------

  public static void main(String[] args){
    //performes BFTRec and BFTIter on linked lists
    BFTRecLinkedList();
    BFTIterLinkedList();

    //does topSort on random DAGs
    TopSort t = new TopSort();
    ArrayList<Node> r1 = t.Kahns(createRandomDAGIter(1000));
    ArrayList<Node> r2 = t.mDFS(createRandomDAGIter(1000));

    //random grid graph
    GridGraph g = createRandomGridGraph(100);
    HashSet<GridNode> h = g.getAllNodes();
    GridNode sourceNode = null;
    GridNode destNode = null;
    for(GridNode node: h){
      if(node.getX() == 0 && node.getY() == 0)
        sourceNode = node;
      else if(node.getX() == 100 && node.getY() == 100)
        destNode = node;
    }
    ArrayList<GridNode> get = astar(sourceNode, destNode);
    if(get == null)
      System.out.println("No solution exists.");
    else{
      int i;
      for(i = 0; i < get.size(); i++){
        GridNode sample = get.get(i);
        System.out.println(sample.getX() + " " + sample.getY());
      }
    }

  }

}


















/*end of file*/
