package face;

import java.util.HashMap;
import java.util.Map;

public class LruCacheTest {
    //lru
    public static void main(String[] args) {
        LruCache<Integer> cache = new LruCache<>(5);
        cache.put("1", new Integer(1));
        cache.put("2", new Integer(2));
        cache.put("3", new Integer(3));
        cache.put("4", new Integer(4));
        cache.put("5", new Integer(5));
        cache.put("6", new Integer(6));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        cache.put("7", new Integer(7));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("7"));

    }

}

class LruCache<T> {
    DoubleLinkNode doubleNode;
    Map<Node, String> valueMap;
    Map<String, Node> keyMap;
    int capacity;


    public LruCache(int length) {
        this.capacity = length;
        doubleNode = new DoubleLinkNode();
        valueMap = new HashMap<>();
        keyMap = new HashMap<>();
    }

    public void put(String key, T t) {
        Node node = keyMap.get(key);
        if (node == null) {
            Node nodeNew = new Node(t);
            keyMap.put(key, nodeNew);
            valueMap.put(nodeNew, key);
            doubleNode.addHead(nodeNew);
            if (keyMap.size() == capacity + 1) {
                Node deleteNode = doubleNode.removeTail();
                String keyDelete = valueMap.get(deleteNode);
                keyMap.remove(keyDelete);
                valueMap.remove(deleteNode);
            }
        } else {
            node.value = t;
            doubleNode.moveToHead(node);
        }

    }

    public T get(String key) {
        Node<T> node = keyMap.get(key);
        if (node != null) {
            doubleNode.moveToHead(node);
            return node.value;
        }
        return null;
    }
}

class Node<T> {
    T value;
    Node pre;
    Node next;

    public Node(T value) {
        this.value = value;
    }
}

class DoubleLinkNode {
    Node head;
    Node tail;

    public void addHead(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
    }

    public Node removeTail() {
        Node result = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node pre = tail.pre;
            pre.next = null;
            tail = pre;
        }
        return result;
    }

    public void moveToHead(Node node) {
        if (head == node) {
            return;
        }
        if (node == tail) {
            tail=node.pre;
            tail.next=null;
        } else {
            node.pre.next = node.next;
            node.next.pre= node.pre;
        }
        node.next = head;
        head.pre = node;
        head = node;
    }

}
