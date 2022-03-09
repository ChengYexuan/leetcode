package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution{
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        if(root==null)
            return result;
        l.add(root.val);
        result.add(l);
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode mid = q.poll();
            List<Integer> row = new ArrayList<>();
            if(mid.left!=null){
                row.add(mid.left.val);
                q.offer(mid.left);
            }
            if(mid.right!=null){
                row.add(mid.right.val);
                q.offer(mid.right);
            }
            if(row.size()>0)
                result.add(row);
        }
        return result;
    }
}
