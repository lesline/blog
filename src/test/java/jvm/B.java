package jvm;

/**
 * @author zhangshaolin
 * @create 2019/6/11
 */
public class B {
    public static void main(String[] args) {
        B test = new B();
        int a = 1;
        int b = 2;
        int c = test.swap(a, b);
        System.out.println(c);
        SimpleClass simpleClass=new SimpleClass();
        System.out.println(simpleClass);
    }

    public int swap(int a, int b) {
        return a + b;
    }

}