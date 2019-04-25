package leetCode;

import java.util.List;
import java.util.Stack;

public class Demo2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rs = new ListNode(0);
        ListNode head = rs;
        ListNode before = null;
        int nextAdd = 0;
        do {
            int temp = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            head.val = temp % 10 + nextAdd;
            head.next = new ListNode(0);
            nextAdd = temp / 10;
            before = head;
            head = head.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        } while (l1 != null || l2 != null);
        before.next = null;
        return rs;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode h1 = l1;
        l1.next = new ListNode(4);
        l1 = l1.next;
        l1.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        ListNode h2 = l2;
        l2.next = new ListNode(6);
        l2 = l2.next;
        l2.next = new ListNode(4);
        ListNode rs = new Demo2().addTwoNumbers(h1, h2);
        return;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
