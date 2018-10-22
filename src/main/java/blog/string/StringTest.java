package blog.string;

/**
 * String str1 = new String("a");//两个对象(无论是jdk1.6还是1.7） 一个堆中 一个常量区 返回堆中的
 * String str1 = "a";//一个对象 放在常量池中,返回的是常量池中的引用

 * String c = "a" + "b" + "c";  //三个对象，都在常量区中 操作会加到常量池中
 * String s3 = new String("1") + new String("1");//四个对象，一个常量区的1 一个堆中的11 两个堆中的匿名对象1
 * <p>
 * <p>
 * <p>
 * 参考：
 * <p>
 * https://www.cnblogs.com/ydpvictor/archive/2012/09/09/2677260.html
 * https://tech.meituan.com/in_depth_understanding_string_intern.html
 * https://www.jianshu.com/p/c14364f72b7e
 * Created by zhangshaolin on 2017/12/20.
 */
public class StringTest {
    public static void main(String[] args) {
        // test1();
        // test2();
    }



    public static void test1() {
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        System.out.println(str5.equals(str3));//true
        System.out.println(str5 == str3);//false
        System.out.println(str5.intern() == str3);//true
        System.out.println(str5.intern() == str4);//false
    }

    public static void test2() {

        String a = new String("ab");
        String b = new String("ab");
        String c = "ab";
        String d = "a" + "b";
        String e = "b";
        String f = "a" + e;

        System.out.println(b.intern() == a);//fasle
        System.out.println(b.intern() == c);//true
        System.out.println(b.intern() == d);//true  编译期d已确定(修改、赋值)为ab
        System.out.println(b.intern() == f);//false
        System.out.println(b.intern() == a.intern());//true


    }

    public static void test3() {
        // 编译期已确定
        String a = "abc";
        String b = "abc";
        String c = "a" + "b" + "c";
        String d = "a" + "bc";
        String e = "ab" + "c";

        System.out.println(a == b);//true
        System.out.println(a == c);//true
        System.out.println(a == d);//true
        System.out.println(a == e);//true
        System.out.println(c == d);//true
        System.out.println(c == e);//true
    }
}
