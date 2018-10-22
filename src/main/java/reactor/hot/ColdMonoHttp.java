package reactor.hot;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @author zhangshaolin
 * @create 2018/6/6
 */
public class ColdMonoHttp {
    @Test
    public void codeTest() throws InterruptedException {
        //getNameByPhone方法执行两次
//        Mono<String> customerPhoneMono = Mono.fromSupplier(
//                () -> ColdMonoHttp.getNameByPhone("18611854542")
//        );

        //方法执行一次
        Mono<String> customerPhoneMono = Mono.fromFuture(
                CompletableFuture.supplyAsync(() -> ColdMonoHttp.getNameByPhone("18611854542"))
        );


        customerPhoneMono.subscribe(a -> System.out.println("-----订阅一:name=" + a));
        customerPhoneMono.subscribe(a -> System.out.println("-----订阅二:name=" + a));
        Thread.sleep(10000);
    }

    public static String getNameByPhone(String phone) {
        try {
            System.out.println("get name start:" + phone);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("get name end:" + phone);
        return "zhangsan";
    }
}