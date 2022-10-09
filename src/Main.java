import list.ListNode;
import list.ListSolution;
import solution.Solution;
import test.ChildClass;
import test.SuperClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        // Array related test
        Solution s = new Solution();
        List<Integer> list = Arrays.asList(4, 1, 2, 3, 5, 7, 4, 2, 10);
        Collections.sort(list);
        String s1 = "bbbab";
        String s2 = "eat";
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(s.findKthLargest(new int[]{-3,-2,3,1,2,4,5,5,6}, 7));

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
        String a = q.hashCode() == p.hashCode() ? "equal" : "not equal";
        String b = q == p ? "equal" : "not equal";
        String c = q.equals(p) ? "equal" : "not equal";
        String d = System.identityHashCode(q) == System.identityHashCode(p) ? "equal" : "not equal";
        System.out.println(a + '\n' + b + '\n' + c + '\n' + d);*/

        // constructor of hierarchy
//        ChildClass child = new ChildClass();

        // List related test
/*        ListSolution s = new ListSolution();
        ListNode head = s.buildList(new int[]{4, 2, 1, 3});
        s.insertionSortList(head);*/
    }

}
