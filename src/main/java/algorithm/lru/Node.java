package algorithm.lru;

/**
 * @author zhangshaolin
 * @create 2020/3/1
 */
public class Node<T> {

    Node last;
    Node next;
    T value;

    public Node(T value) {
        this.value = value;
    }

}