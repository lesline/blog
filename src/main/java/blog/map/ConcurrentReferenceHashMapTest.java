package blog.map;

import org.junit.Test;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangshaolin
 * @create 2019/1/27
 */
public class ConcurrentReferenceHashMapTest {
    @Test
    public void test() throws InterruptedException {
        String key = new String("key");
        String value = new String("val");
        Map<String, String> map = new ConcurrentReferenceHashMap(8);
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(map);
    }
    @Test
    public void test2() throws InterruptedException {
        WeakHashMap  weakHashMap=new WeakHashMap();
        weakHashMap.put("aa","aa");
        System.out.println(weakHashMap.get("aa"));
    }



}