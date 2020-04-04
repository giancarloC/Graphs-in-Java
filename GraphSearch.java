import java.util.*;

//class to perform DFS and BFT on a graph
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
    Node check;

    while(s.size() > 0){
      check = s.peek();
      s.pop();

      if(!visited.contains(check)){
        visited.add(check);
        ret.add(check);
        if(check == end){
          return ret;
        }

        for(Node node: check.edges){
          if(!visited.contains(node))
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
