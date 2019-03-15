package blog.reference.weak;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Main class
 *
 * @author BrightLoong
 * @date 2018/5/24
 */
public class Client2 {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();
        WeakReference<Integer> weakString = new WeakReference<>(new Integer(1), referenceQueue);
        System.out.println(weakString.get());//1

        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(weakString.get());//null
        System.out.println("-----");
        System.out.println(referenceQueue.poll().get());//null

    }
}
