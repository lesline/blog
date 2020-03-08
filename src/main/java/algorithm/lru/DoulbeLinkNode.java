package algorithm.lru;

/**
 * @author zhangshaolin
 * @create 2020/3/1
 */
public class DoulbeLinkNode<T> {
    Node<T> head;
    Node<T> tail;

    public DoulbeLinkNode() {
        this.head = null;
        this.tail = null;
    }

    public void addNode(Node<T> node) {
        if (head == null) {
            tail = node;
            head = node;
        } else {
            this.tail.next = node;
            node.last = this.tail;
            this.tail = node;
        }
    }

    public void moveRecentUsedNodeToTail(Node<T> node) {
        if (node == tail) {
            return;
        }
        if (node == head) {
            head = head.next;
        } else {
            node.last.next = node.next;
            node.next.last = node.last;
        }
        node.last = tail;
        node.next = null;
        tail.last = node;
        tail = node;
    }

    public Node<T> removeHead() {
        Node res = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.last = null;
            res.next = null;
        }
        return res;
    }

}