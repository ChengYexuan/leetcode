package tree;

import java.util.*;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        if (root == null)
            return result;
        l.add(root.val);
        result.add(l);
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode mid = q.poll();
            List<Integer> row = new ArrayList<>();
            if (mid.left != null) {
                row.add(mid.left.val);
                q.offer(mid.left);
            }
            if (mid.right != null) {
                row.add(mid.right.val);
                q.offer(mid.right);
            }
            if (row.size() > 0)
                result.add(row);
        }
        return result;
    }

    // 逆波兰表达式主要有以下两个优点：
    //
    // 去掉括号后表达式无歧义，不需要阔话也可以依据次序计算出正确结果。
    // 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    s.push(s.pop() + s.pop());
                    break;
                case "-":
                    s.push(-s.pop() + s.pop());
                    break;
                case "*":
                    s.push(s.pop() * s.pop());
                    break;
                case "/":
                    s.push(1 / s.pop() * s.pop());
                    break;
                default:
                    s.push(Integer.parseInt(token));
                    break;
            }
        }
        return s.peek();
    }
}
