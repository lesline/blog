package algorithm.node;

/**
 * @author zhangshaolin
 * @create 2020/2/17
 */
public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int data) {
        this.value = data;
    }


    @Override
    public String toString() {

        StringBuffer st = new StringBuffer();
        ListNode tmp = this;
        while (tmp != null) {
            st.append(tmp.value).append("->");
            tmp = tmp.next;
        }

        return st.toString();

    }
}