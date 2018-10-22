package blog.string;

/**
 * new String("china") 每次执行都会在堆中建一个新的对象，但常量区就一个
 */
public class StringNew {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s1 = "china";
        String s2 = "china";
        String s3 = "china";

        String ss1 = new String("china");//
        String ss2 = new String("china");//
        String ss3 = new String("china");//

        System.out.println(s1 == s2);//true
        System.out.println(s1 == ss1);//false
        System.out.println(s1 == ss1.intern());//true
        System.out.println(ss1 == ss2);//false
        System.out.println(ss1.intern() == ss2.intern());//true
        System.out.println(ss1.intern() == ss2);//false


        //Integer型的在java8之前缓冲一定数值
        Integer a1 = 1;
        Integer a2 = new Integer(1);
        System.out.println(a1 == a2);// java6/7=true  java 8=false
    }

}
