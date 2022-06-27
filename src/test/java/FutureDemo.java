import java.util.Random; 
import java.util.concurrent.Callable; 
import java.util.concurrent.ExecutionException; 
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.util.concurrent.Future; 
import java.util.concurrent.TimeUnit; 
import java.util.concurrent.TimeoutException; 
 
public class FutureDemo { 
    public static void main(String[] args) { 
        ExecutorService executorService = Executors.newSingleThreadExecutor(); 
        Future<Integer> future = executorService.submit(new FutureTask()); 
        try { 
            Integer res = future.get(2000, TimeUnit.MILLISECONDS); 
            System.out.println("Future线程返回值：" + res); 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        } catch (ExecutionException e) { 
            e.printStackTrace(); 
        } catch (TimeoutException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    static class FutureTask implements Callable<Integer> { 
 
        @Override 
        public Integer call() throws Exception { 
            Thread.sleep(new Random().nextInt(3000)); 
            return new Random().nextInt(10); 
        } 
    } 
} 