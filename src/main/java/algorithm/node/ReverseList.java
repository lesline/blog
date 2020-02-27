package algorithm.node;

/**
 * @author zhangshaolin
 * @create 2020/2/17
 */
public class ReverseList {

    /**
     * 链表反转
     *
     * @param head
     */

    public static Node revers(Node head) {
        Node end = head;
        while (end.next != null) {
            Node tmp = end.next;
            end.next = tmp.next;
            tmp.next = head;
            head = tmp;
        }
        return head;
    }

    /**
     * 判断是否有环
     *
     * @param head
     * @return
     */
    public static Boolean checkCircle(Node head) {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node quick = head.next;

        while (slow != null && quick != null && quick.next != null) {
            if (slow == quick || slow == quick.next) {
                return true;
            }
            slow = slow.next;

            quick = quick.next.next;

        }
        return false;


    }

    /**
     * 已知一个单链表求倒数第 N 个节点
     *
     * @param head
     * @return
     */
    public static Boolean backK(Node head) {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node quick = head.next;

        while (slow != null && quick != null && quick.next != null) {
            if (slow == quick || slow == quick.next) {
                return true;
            }
            slow = slow.next;

            quick = quick.next.next;

        }
        return false;
    }


    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null || k == 0) {
            return head;
        }
        int len = 0;
        ListNode h = head;
        while (h != null) {
            h = h.next;
            len++;
        }
        k = k % len;
        if (k == 0) {
            return head;
        }
        ListNode quick = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            quick = quick.next;
        }
        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next;
        }
        h = slow.next;
        slow.next = null;
        quick.next = head;
        return h;
    }

    public static void main(String[] args) {
        Node node1 = getNode();
        System.out.println(node1);
        System.out.println(revers(node1));
    }


    private static Node getNode() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        return node1;
    }
}