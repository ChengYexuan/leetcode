package graph;

import java.util.*;

public class Graph {
    public Node start;

    public Graph(int[][] adj) {
        Map<Integer, Node> graphMap = new HashMap<>();
        for (int i = 1; i <= adj.length; i++) {
            graphMap.put(i, new Node(i));
        }
        for(int i = 0; i<adj.length; i++){
            for(int j=0; j<adj[0].length; j++){
                graphMap.get(i+1).neighbors.add(graphMap.get(adj[i][j]));
            }
        }
        start = graphMap.get(1);
    }

    public Node cloneGraph(Node start) {
        Stack<Node> s = new Stack<>();
        Map<Integer, ArrayList<Integer>> connects = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        // 遍历图并记录下所有节点的连接关系
        if(start != null){
            s.push(start);
            visited.add(start.val);
            while(!s.isEmpty()){
                Node node = s.pop();
                if(node.neighbors.isEmpty()){
                    return new Node(1);
                }
                for(Node n : node.neighbors){
                    if(connects.containsKey(n.val)){
                        ArrayList<Integer> l = connects.get(n.val);
                        l.add(node.val);
                    } else {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(node.val);
                        connects.put(n.val, temp);
                    }
                    if(!visited.contains(n.val)){
                        s.push(n);
                        visited.add(n.val);
                    }
                }
            }
        } else {
            return start;
        }
        Map<Integer, Node> graph = new HashMap<>();
        for(Integer num : connects.keySet()){
            Node node;
            if(!graph.containsKey(num)){
                node = new Node(num);
                graph.put(num, node);
            } else {
                node = graph.get(num);
            }
            for(Integer item : connects.get(num)){
                if(graph.containsKey(item)){
                    Node n = graph.get(item);
                    n.neighbors.add(node);
                } else {
                    Node temp = new Node(item);
                    graph.put(item, temp);
                    temp.neighbors.add(node);
                }
            }
        }
        return graph.get(1);
    }

}
