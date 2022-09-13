package tree;

public class Node extends TreeNode{
    int val;
    Node left;
    Node right;
    Node next;

    Node(){
        super();
    }

    Node(int _val){
        super(_val);
        next = null;
    }

    Node(int val, Node left, Node right){
        super(val, left, right);
        next = null;
    }
}
