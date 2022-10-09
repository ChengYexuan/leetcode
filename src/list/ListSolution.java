package list;

public class ListSolution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        // 从 head 开始
        while(node.next != null){
            // 如果刚好比前一个节点大
            if(node.next.val > node.val){
                node = node.next;
                continue;
            }
            // 找到小于当前节点的最后一个节点
            ListNode target = node.next;
            ListNode temp = dummy;
            while(temp.next.val < target.val){
                temp = temp.next;
            }
            node.next = target.next;
            target.next = temp.next;
            temp.next = target;
        }
        return dummy.next;
    }

    public ListNode buildList(int[] ints) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        for (int i : ints
             ) {
            ListNode node = new ListNode(i);
            pre.next = node;
            pre = pre.next;
        }
        return dummy.next;
    }
}
