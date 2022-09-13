import graph.Graph;
import graph.GraphSolution;
import main.LFUCache;
import main.LRUCache;
import main.Solution;
import tree.Node;
import tree.Tree;
import tree.TreeNode;
import tree.TreeSolution;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {



    public static void main(String[] args){

        // Array related test
        Solution s = new Solution();
/*        List<Integer> list = Arrays.asList(4, 1, 2, 3, 5, 7, 4, 2, 10);
        Collections.sort(list);
        String s1 = "bbbab";
        String s2 = "eat";
        System.out.println(s.longestPalindromeSubseq(s1));*/
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(s.longestIncreasingPath(matrix));;





        // LRU Cache test or LFU Cache test
/*        Scanner sc = new Scanner(System.in);
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(sc.nextLine());
        List<String> funcName = new ArrayList<>();
        while(m.find()){
            funcName.add(m.group().replace("\"", ""));
        }
        p = Pattern.compile("\\[(.*?)]");
        m = p.matcher(sc.nextLine());
        List<String> para = new ArrayList<>();
        while(m.find()){
            para.add(m.group().replace("[", "").replace("]", ""));
        }
        LFUCache lfu = new LFUCache();
        Class<?> clz = lfu.getClass();
        System.out.println();
        Constructor<?> constructor = clz.getConstructor(int.class);
        Object obj = constructor.newInstance(Integer.parseInt(para.get(0)));
        for(int i=1; i<funcName.size(); i++){
            if(funcName.get(i).equals("put")){
                String[] nums = para.get(i).split(",");
                int num1 = Integer.parseInt(nums[0]);
                int num2 = Integer.parseInt(nums[1]);
                System.out.println(clz.getMethod("put", int.class, int.class).invoke(obj, num1, num2));
            } else{
                int num = Integer.parseInt(para.get(i));
                System.out.println(clz.getMethod("get", int.class).invoke(obj, num));;
            }
        }*/

        // Graph related test
/*        int[][] connects = new int[][]{{2, 3}, {1, 3}, {1, 2}};
        Graph gh = new Graph(connects);
        System.out.println(gh);
        gh.cloneGraph(gh.start);*/

        // Tree related test
/*        TreeSolution s = new TreeSolution();
        String[] tree = {"1", "2", "3", "4", "5", "6", "7"};
        TreeNode t = s.buildTree3(new int[]{1,2}, new int[]{2,1});
        System.out.println(t);*/

        // hashcode and equal test
/*        Integer q = 2;
        Integer p = 2;
        String a = q.hashCode() == p.hashCode() ? "equal": "not equal";
        String b = q==p ? "equal": "not equal";
        String c = q.equals(p) ? "equal": "not equal";
        String d = System.identityHashCode(q) == System.identityHashCode(p) ? "equal": "not equal";
        System.out.println(a+'\n'+b+'\n'+c+'\n'+d);;*/

        superEggDrop(3,25);
    }
    public static int superEggDrop(int k, int n) {
        int[][] dp = new int[k+1][n+1];
        for(int i = 1; i <= n; i++){
            dp[1][i] = i;
        }
        for(int i = 2; i <= k; i++){
            for(int j = 0; j <= n; j++){
                if(j <= Math.pow(2, i-1)){
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                dp[i][j] = dp[i-1][j];
                for(int s = 1; s <= j; s++){
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][s-1], dp[i][j-s])+1);
                }
            }
        }
        return dp[k][n];
    }


}
