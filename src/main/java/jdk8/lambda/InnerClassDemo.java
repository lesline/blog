package jdk8.lambda;

/**
 * lambda表达式的this
 */
public class InnerClassDemo {
    private String name = "Demo";
    public void test() {
        // 匿名类实现
        new Thread(new Runnable() {
            private String name = "Runnable";
            @Override
            public void run() {
                System.out.println("这里的this指向匿名类:" + this.name);
            }
        }).start();
    }
}