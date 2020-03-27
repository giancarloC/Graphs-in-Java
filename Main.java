import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

//represents a position in the maze
class Node{
  int nodeVal;
  HashSet<Node> edges;

  public Node(int nodeVal){
    this.nodeVal = nodeVal;
    edges = new HashSet<Node>();
  }
}

//graph class to represent a maze
class Graph{
  HashSet<Node> setNodes;
  ArrayList<Node> listNodes;

  public Graph(){
    setNodes = new HashSet<Node>();
    listNodes = new ArrayList<Node>();
  }

  void addNode(final int nodeVal){
    Node add = new Node(nodeVal);
    setNodes.add(add);
    listNodes.add(add);
  }

  void addMadeNode(final Node node){
    setNodes.add(node);
    listNodes.add(node);
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

  ArrayList<Node> getArrayNodes(){
    return listNodes;
  }
}


class GraphSearch{

  static boolean DFSRecHelper(HashSet<Node> visited, ArrayList<Node> ret, final Node check, final Node goal){
    visited.add(check);
    ret.add(check);

    if(check == goal){
      return true;
    }

    //loops through edges and checks
    boolean end = false;
    for(Node node: check.edges){
      //ends search once goal node is found
      if(end){
        return true;
      }
      if(!visited.contains(node)){
        end = DFSRecHelper(visited, ret, node, goal);
      }
    }

    return false;
  }

  ArrayList<Node> DFSRec(final Node start, final Node end){
    HashSet<Node> visited = new HashSet<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();

    boolean check = DFSRecHelper(visited, ret, start, end);
    if(check)
      return ret;
    return null;
  }

  ArrayList<Node> DFSIter(final Node start, final Node end){
    HashSet<Node> visited = new HashSet<Node>();
    Stack<Node> s = new Stack<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();

    s.push(start);
    visited.add(start);
    Node check;

    while(s.size() > 0){
      check = s.peek();
      s.pop();
      ret.add(check);
      if(check == end){
        return ret;
      }

      for(Node node: check.edges){
        if(!visited.contains(node)){
          visited.add(node);
          s.push(node);
        }
      }
    }
    return null;
  }

  static void BFTRecHelper(HashSet<Node> visited, Queue<Node> q, ArrayList<Node> ret){
    if(q.size() == 0)
      return;

    Node node = q.remove();
    ret.add(node);

    for(Node edge: node.edges){
      if(!visited.contains(edge)){
        visited.add(edge);
        q.add(edge);
      }
    }

    BFTRecHelper(visited, q, ret);
  }

  ArrayList<Node> BFTRec(final Graph graph){
    HashSet<Node> visited = new HashSet<Node>();
    Queue<Node> q = new LinkedList<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();

    //checks every node, including un-attached ones
    Node curr;
    for(Node node: graph.setNodes){
      if(!visited.contains(node)){
        visited.add(node);
        q.add(node);
        BFTRecHelper(visited, q, ret);
      }
    }

    return ret;
  }


  ArrayList<Node> BFTIter(final Graph graph){
    HashSet<Node> visited = new HashSet<Node>();
    Queue<Node> q = new LinkedList<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();

    Node curr;
    for(Node node: graph.setNodes){
      if(!visited.contains(node)){
        visited.add(node);
        q.add(node);
        while(q.size() > 0){
          curr = q.remove();
          ret.add(curr);

          //checks edges
          for(Node edge: curr.edges){
            if(!visited.contains(edge)){
              visited.add(edge);
              q.add(edge);
            }
          }
        }
      }
    }

    return ret;
  }

}

//main class to test methods
public class Main{

  static Graph createRandomUnweightedGraphIter(int n){
    Graph g = new Graph();

    //adds nodes to graph
    int i;
    for(i = 0; i < n; i++){
      g.addNode(i);
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


      /*
      Node node = list.get(i);

      //adds random amount of edges to random nodes for each node
      numNodes = rand.nextInt(n-i); // 0 to n-i-1
      for(k = 0; k < numNodes; k++){

        //ensures no node connects to themselves
        randNode = rand.nextInt(n-i-1) + i + 1; // i+1 to n-1
        g.addUndirectedEdge(node, list.get(randNode));
      }

    }*/

    return g;
  }

  static Graph createLinkedList(int n){
    Graph g = new Graph();

    //adds nodes to graph
    int i;
    for(i = 0; i < n; i++){
      g.addNode(i);
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

  static ArrayList<Node> BFTRecLinkedList(final Graph graph){
    GraphSearch gs = new GraphSearch();
    return gs.BFTRec(graph);
  }

  static ArrayList<Node> BFTIterLinkedList(final Graph graph){
    GraphSearch gs = new GraphSearch();
    return gs.BFTIter(graph);
  }

  //--------------------------------------------------------------------------
  //MAIN METHOD
  //--------------------------------------------------------------------------

  public static void main(String[] args){
    Graph g = createLinkedList(100);
    ArrayList<Node> traverse1 = BFTRecLinkedList(g);
    ArrayList<Node> traverse2 = BFTIterLinkedList(g);
  }

}


















/*end of file*/
