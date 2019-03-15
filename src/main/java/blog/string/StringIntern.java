package blog.string;

import org.junit.Test;

/**
 * intern 定义：
 * 如果常量池中存在当前字符串, 就会直接返回当前字符串. 如果常量池中没有此字符串, 会将此字符串放入常量池中后, 再返回
 * <p>
 * java6 常量池在永久代
 * java7+java8  常量池在堆中 常量池中可以存储堆中的引用
 * Created by zhangshaolin on 2017/12/20.
 */
public class StringIntern {

    @Test
    public void test() {
        String s = new String("1");
        String s2 = "1";
        System.out.println(s == s2); //false

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        System.out.println(s3 == s4);//false
    }

    @Test
    public void test1() {
        String s = new String("1");
        String s2 = "1";
        System.out.println(s == s2);//false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);//false
    }

    @Test
    public void test2() {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);//false

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);//false
    }


    @Test
    public void test3() {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);//false

        String s3 = "1" + "1";
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);//true
    }

    @Test
    public void test4() {
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
}
