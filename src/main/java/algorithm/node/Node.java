package algorithm.node;

/**
 * @author zhangshaolin
 * @create 2020/2/17
 */
public class Node {
    public int value;
    public Node next;

    public Node(int data) {
        this.value = data;
    }


    @Override
    public String toString() {

        StringBuffer st = new StringBuffer();
        Node tmp = this;
        while (tmp != null) {
            st.append(tmp.value).append("->");
            tmp = tmp.next;
        }

        return st.toString();

    }
}