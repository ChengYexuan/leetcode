package tree;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;

public class Tree <T>{
    private T root;
    public T getRoot() {
        return root;
    }

    public static <T> T newTree(String[] levelOrder, Class<T> cls) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Queue<T> q = new LinkedList<>();
        T root = cls.newInstance();
        if (!levelOrder[0].equals("null")) {
            /*for (Field field: cls.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    System.out.println(field.getName()+":"+field.get(root));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }*/
            Field value = cls.getDeclaredField("val");
            value.setAccessible(true);
            value.set(root, Integer.parseInt(levelOrder[0]));
            q.offer(root);
        }
        for (int index = 1; index < levelOrder.length; index += 2) {
            T node = q.poll();

            Field value = cls.getDeclaredField("val");
            value.setAccessible(true);

            if (!levelOrder[index].equals("null")) {
                assert node != null;
                T left = cls.newInstance();

                value.set(left, Integer.parseInt(levelOrder[index]));

                Field leftNode = cls.getDeclaredField("left");
                leftNode.setAccessible(true);
                leftNode.set(node, left);

                q.offer(left);
            }
            if (!levelOrder[index + 1].equals("null")) {
                assert node != null;
                T right = cls.newInstance();

                value.set(right, Integer.parseInt(levelOrder[index + 1]));

                Field rightNode = cls.getDeclaredField("right");
                rightNode.setAccessible(true);
                rightNode.set(node, right);

                q.offer(right);
            }
        }
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
            this(0);
        }

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
