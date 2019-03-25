package blog.string;

import org.junit.Test;

/**
 * @author zhangshaolin
 * @create 2019/3/21
 */
public class TestString {

    @Test
    public void test1() {
        String str2 = new String("str")+new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str2==str1);//true
    }

    @Test
    public void test2() {
        String str1 = "str01";
        String str2 = new String("str")+new String("01");
        str2.intern();
        System.out.println(str2 == str1);//false
    }
}