package jdk8.lambda;

/**
 * lambda表达式的this
 */
public class LambdaDemo {
    private String name = "Demo";
    public void test() {
        // lambda实现
        new Thread(() -> {
            System.out.println("这里的this指向当前的ThisDemo类:" + this.name);
        }).start();
    }
}