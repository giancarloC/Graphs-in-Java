import java.util.*;

//Topological sort for a DAG
class TopSort{
  //Khan's algorithm
  ArrayList<Node> Kahns(final DirectedGraph graph){
    List<Node> list = new ArrayList<Node>(graph.getAllNodes());
    int numNodes = list.size();
    HashMap<Node, Integer> degree = new HashMap<>(numNodes);

    //populates hash map w/ in-degree values of 0
    Node node;
    int i;
    for(i = 0; i < numNodes; i++){
      node = list.get(i);
      degree.put(node, 0);
    }

    //adds in-degree number
    int newDegree;
    for(i = 0; i < numNodes; i++){
      node = list.get(i);
      for(Node edge: node.edges){
        //adds 1 to in-degree
        newDegree = degree.get(edge) + 1;
        degree.replace(edge, newDegree);
      }
    }

    //does sort
    Queue<Node> q = new LinkedList<Node>();
    ArrayList<Node> ret = new ArrayList<Node>();
    while(ret.size() != numNodes){

      //adds in-degree 0 nodes to queue
      for(i = 0; i < list.size(); i++){
        node = list.get(i);
        if(degree.get(node) == 0)
          q.add(node);
      }

      //decreases in-degree of edges
      while(q.size() > 0){
        node = q.remove();
        ret.add(node);

        for(Node edge: node.edges){
          newDegree = degree.get(edge) - 1;
          degree.replace(edge, newDegree);
        }
      }
    }


    return ret;
  }

  static private void DFSRecHelper(HashSet<Node> visited, Stack<Node> topSort, final Node node){
    visited.add(node);

    //loops through edges and checks
    for(Node each: node.edges){
      if(!visited.contains(each)){
        DFSRecHelper(visited, topSort, each);
      }
    }

    //pushes once all edges have been checks
    topSort.push(node);
  }

  ArrayList<Node> mDFS(final DirectedGraph graph){
    HashSet<Node> visited = new HashSet<Node>();
    Stack<Node> topSort = new Stack<Node>();

    //checks each node, adds to stack
    HashSet<Node> nodes = graph.getAllNodes();
    for(Node each: nodes){
      if(!visited.contains(each)){
        DFSRecHelper(visited, topSort, each);
      }
    }

    //pops stack and adds to top sort
    ArrayList<Node> ret = new ArrayList<Node>();
    while(topSort.size() > 0){
      ret.add(topSort.peek());
      topSort.pop();
    }

    return ret;
  }

}
