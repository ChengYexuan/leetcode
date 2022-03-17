import graph.Graph;
import tree.TreeSolution;
import tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] connects = new int[][]{{2, 3}, {1, 3}, {1, 2}};
        Graph gh = new Graph(connects);
        System.out.println(gh);
        gh.cloneGraph(gh.start);
//        TreeSolution s = new TreeSolution();
//        String[] tree = {"1", "2", "3", "4", "null", "null", "5"};
//        Tree t = new Tree(tree);
//        System.out.println(s.levelOrder(t.getRoot()));
//        GraphSolution gh = new GraphSolution();
//        int[] a = {10, 15, 20};
//        int[][] a = gh.create(6,6);
//        int[][] a = {{1,0,1},{0,0,0},{1,0,1}};
//        System.out.println(gh.maxDistance(a));


/*        Integer q = 2;
        Integer p = 2;
        String a = q.hashCode() == p.hashCode() ? "equal": "not equal";
        String b = q==p ? "equal": "not equal";
        String c = q.equals(p) ? "equal": "not equal";
        String d = System.identityHashCode(q) == System.identityHashCode(p) ? "equal": "not equal";
        System.out.println(a+'\n'+b+'\n'+c+'\n'+d);;*/
    }
}
