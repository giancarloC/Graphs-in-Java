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

  WeightedGraph createRandomCompleteWeightedGraph(final int n){
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

  WeightedGraph createWeightedLinkedList(final int n){
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
    for(i = 0; i < numNodes; i++){
      node1 = nodes.get(i);

      //adds edge to every other node
      for(k = 0; k < numNodes; k++){
        if(i != k){
          node2 = nodes.get(k);
          w.addWeightedEdge(node1, node2, sameWeight);
        }
      }
    }

    return w;
  }

  //--------------------------------------------------------------------------
  //MAIN METHOD
  //--------------------------------------------------------------------------

  public static void main(String[] args){
    /*
    BFTRecLinkedList();
    BFTIterLinkedList();

    TopSort t = new TopSort();
    ArrayList<Node> r1 = t.Kahns(createRandomDAGIter(1000));
    ArrayList<Node> r2 = t.mDFS(createRandomDAGIter(1000));
    */

  }

}


















/*end of file*/
