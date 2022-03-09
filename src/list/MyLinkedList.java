package list;

import list.ListNode;

public class MyLinkedList {
    ListNode head;

    MyLinkedList(){}
    public MyLinkedList(ListNode head){this.head=head;}

    public ListNode middleNode() {
        ListNode march = head;
        while(march.next!=null){
            head = head.next;
            if(march.next.next==null){
                break;
            }
            march = march.next.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd(int n) {
        ListNode march = head, target = head;
        for(int i=0; i<n; i++){
            if(march.next==null){
                return head.next;
            }
            march = march.next;
        }
        while(march.next!=null){
            target = target.next;
            march = march.next;
        }
        target.next = target.next.next;
        return head;
    }
}
