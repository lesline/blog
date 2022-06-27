import java.util.concurrent.Semaphore;

public class Print {
    public static void main(String[] args) {
        Semaphore semaphoreA = new Semaphore(0);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(1);
        new Thread(() -> {
            while (true) {
                try {
                    semaphoreC.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("aaa");
                semaphoreA.release();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    semaphoreA.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("bbb");
                semaphoreB.release();
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    semaphoreB.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ccc");
                semaphoreC.release();
            }
        }).start();

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
