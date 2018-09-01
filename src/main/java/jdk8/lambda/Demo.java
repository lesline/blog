package jdk8.lambda;

/**
 * lambda表达式的this
 */
public class Demo {
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

        // lambda实现
        new Thread(() -> {
            System.out.println("这里的this指向当前的ThisDemo类:" + this.name);
        }).start();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.test();
    }
}