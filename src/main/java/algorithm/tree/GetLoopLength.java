package algorithm.tree;


public class GetLoopLength {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a3;
        System.out.println(getLoopLength(a1));

    }

    public static int getLoopLength(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int len = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //输出环长
            if (fast == slow) {
                slow = slow.next;
                fast = fast.next.next;
                len++;
                while (fast != slow) {
                    len++;
                    slow = slow.next;
                    fast = fast.next.next;
                }
                return len;
            }
        }
        return len;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}