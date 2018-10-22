package blog.string;

/**
 * 方法名、java等字符串在代码执行前已在常量池中存在
 * 所以s2.intern() 返回的是常量池中的数据
 * <p>
 * <p>
 * s1.intern() 返回的是常量池中的引用，引用的对象是堆中对象，所以=s1
 * <p>
 * <p>
 * Created by zhangshaolin on 2017/12/26.
 */
public class StringInternJava {
    public static void main(String[] args) {
        testA();
    }


    public static void testA() {
        String s1 = new String("a") + new String("b");
        String s11 = "a" +"b"; //存在这个前后值不一样

//        System.out.println(s11=="ab");

        System.out.println(s1.intern() == "ab");//true
        System.out.println(s1.intern() == s1);//true
        System.out.println(s1 == "ab");//true

        String s2 = new String("ja") + new String("va");
        System.out.println(s2.intern() == "java");//true
        System.out.println(s2.intern() == s2);//false
        System.out.println(s2 == "java");//false
    }

    public static void testB() {

        String s2 = new String("java");
        String s3 = new StringBuilder().append("ja").append("va").toString();

        System.out.println(s2.intern() == s2);//false
        System.out.println(s2.intern() == s3);//false
    }


    public static void test1() {
        String s1 = new StringBuilder().append("aa").append("bb").toString();
        System.out.println(s1.intern() == s1); //true
        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);//false
    }

    public static void test3() {
        String s1 = new String("aa") + new String("bb");
        System.out.println(s1.intern() == s1); //true
    }

    public static void test2() {
        String s1 = new String("aabb");
        System.out.println(s1.intern() == s1);//false
        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);//false
    }


}
