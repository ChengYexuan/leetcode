package tree;

//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
//    @Getter
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    Tree() {
        this.root = null;
    }

//    Tree(int[] suffix){
//        Stack s = new Stack();
//        for(int node: suffix){
//            if(node)
//            s.push()
//        }
//    }

    public Tree(String[] levelOrder) {
        Queue<TreeNode> q = new LinkedList<>();
        if (!levelOrder[0].equals("null")) {
            TreeNode root = new TreeNode(Integer.parseInt(levelOrder[0]));
            q.offer(root);
            this.root = root;
        }
        for (int index = 1; index < levelOrder.length; index += 2) {
            TreeNode node = q.poll();
            if (!levelOrder[index].equals("null")) {
                assert node != null;
                node.left = new TreeNode(Integer.parseInt(levelOrder[index]));
                q.offer(node.left);
            }
            if (!levelOrder[index + 1].equals("null")) {
                assert node != null;
                node.right = new TreeNode(Integer.parseInt(levelOrder[index + 1]));
                q.offer(node.right);
            }
        }
    }
}
