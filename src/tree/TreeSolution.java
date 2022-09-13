package tree;

import java.util.*;

public class TreeSolution {
    /**
     * 以数组的形式输出层序遍历结果
     *
     * @param root 二叉树根节点
     * @return 二叉树按照层序得到的二维数组
     */
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

    /**
     * 逆波兰表达式的优点：去掉括号后表达式无歧义，适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
     *
     * @param tokens 后缀表达式
     * @return calculation of expression
     */
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

    // 存储 inorder 或 postorder 中值到索引的映射，避免 for 循环寻找
    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    /**
     * 用 preorder 和 inorder 构造二叉树
     *
     * @param preorder 前序遍历数组
     * @param inorder  中序遍历数组
     * @return 二叉树根节点
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build1(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    /**
     * pre+in -> tree的递归子函数
     *
     * @param preorder 前序遍历数组为 preorder[preStart..preEnd]
     * @param preStart preorder的数组头
     * @param preEnd   preorder的数组尾
     * @param inorder  中序遍历数组为 inorder[inStart..inEnd]
     * @param inStart  inorder的数组头
     * @param inEnd    inorder的数组尾
     * @return 二叉树根节点
     */
    TreeNode build1(int[] preorder, int preStart, int preEnd,
                    int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = valToIndex.get(rootVal);

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build1(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);

        root.right = build1(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }

    /**
     * 用 inorder 和 postorder 构造二叉树
     *
     * @param inorder   中序遍历数组
     * @param postorder 后序遍历数组
     * @return 二叉树根节点
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build2(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    /**
     * in+post -> tree的递归子函数
     *
     * @param inorder   中序遍历数组为 inorder[inStart..inEnd]
     * @param inStart   inorder的数组头
     * @param inEnd     inorder的数组尾
     * @param postorder 后序遍历数组为 postorder[postStart..postEnd]
     * @param postStart postorder的数组头
     * @param postEnd   postorder的数组尾
     * @return 二叉树根节点
     */
    TreeNode build2(int[] inorder, int inStart, int inEnd,
                    int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd) {
            return null;
        }

        int rootVal = postorder[postEnd];
        // rootVal 在中序遍历数组中的索引
        int index = valToIndex.get(rootVal);

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build2(inorder, inStart, index - 1,
                postorder, postStart, postStart + leftSize - 1);

        root.right = build2(inorder, index + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);
        return root;
    }

    /**
     * 用 preorder 和 postorder 构造二叉树
     *
     * @param preorder  前序遍历数组
     * @param postorder 后序遍历数组
     * @return 二叉树根节点
     */
    public TreeNode buildTree3(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i], i);
        }
        return build3(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    /**
     * pre+post -> tree的递归子函数
     *
     * @param preorder  前序遍历数组为 preorder[preStart..preEnd]
     * @param preStart  preorder的数组头
     * @param preEnd    preorder的数组尾
     * @param postorder 后序遍历数组为 postorder[postStart..postEnd]
     * @param postStart postorder的数组头
     * @param postEnd   postorder的数组尾
     * @return 二叉树根节点
     */
    TreeNode build3(int[] preorder, int preStart, int preEnd,
                    int[] postorder, int postStart, int postEnd) {

        if (preStart > preEnd) {
            return null;
        }
        if(preStart == preEnd){
            return new TreeNode(preorder[preStart]);
        }
        int rootVal = preorder[preStart];
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);

        int secondVal = preorder[preStart + 1];
        int secondIndex = valToIndex.get(secondVal);
        int secondSize = secondIndex - postStart + 1;
        // 递归构造左子树
        root.left = build3(preorder, preStart + 1, preStart + secondSize,
                postorder, postStart, secondIndex);
        // 递归构造右子树
        root.right = build3(preorder, preStart + secondSize + 1, preEnd,
                postorder, secondIndex + 1, postEnd - 1);
        return root;
    }
}
