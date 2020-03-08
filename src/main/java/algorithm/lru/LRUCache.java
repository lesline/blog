package algorithm.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshaolin
 * @create 2020/3/6
 */
public class LRUCache<K, V> {
    private DoulbeLinkNode doulbeLinkNode;
    private Map<K, Node<V>> keyMap;
    private Map<Node<V>, K> valueMap;
    private int capacity;


    public LRUCache(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("capacity can not less than 0");
        }
        doulbeLinkNode = new DoulbeLinkNode();
        keyMap = new HashMap<>();
        valueMap = new HashMap<>();
        this.capacity = capacity;
    }

    public void set(K key, V v) {
        Node<V> node = keyMap.get(key);
        if (node == null) {
            Node addNode = new Node(v);
            keyMap.put(key, addNode);
            valueMap.put(addNode, key);
            doulbeLinkNode.addNode(addNode);
            if (keyMap.size() == capacity + 1) {
                Node removeNode = doulbeLinkNode.removeHead();
                K removeKye = valueMap.get(removeNode);
                keyMap.remove(removeKye);
                valueMap.remove(removeNode);
            }
        } else {
            node.value = v;
            doulbeLinkNode.moveRecentUsedNodeToTail(node);
        }
    }


    public V get(K key) {
        Node<V> node = keyMap.get(key);
        if (node == null) {
            return null;
        }
        doulbeLinkNode.moveRecentUsedNodeToTail(node);
        return node.value;
    }
}