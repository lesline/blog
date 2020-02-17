package blog.string;

/**
 * @author zhangshaolin
 * @create 2019/6/7
 */
public class A {
    public static final String A; // 常量A
    public static final String B;    // 常量B

    static {
        A = "ab";
        B = "cd";
    }

    public static void main(String[] args) {
// 将两个常量用+连接对s进行初始化
        String s = A + B;
        String t = "abcd";
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        } else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
    }
}