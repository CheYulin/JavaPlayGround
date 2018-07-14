package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalPlayGround {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>();
    static ExecutorService[] executorServices;

    static {
        executorServices = new ExecutorService[20];
        for (int i = 0; i < executorServices.length; i++) {
            executorServices[i] = Executors.newSingleThreadExecutor();
        }
    }

    private static class PrintThread extends Thread {
        public void run() {
            integerThreadLocal.set(atomicInteger.getAndIncrement());
            System.out.printf("my thread id: %d\n", integerThreadLocal.get());
        }
    }

    public static void testSimpleThreadLocal() {
        // 1st: create handles
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new PrintThread();
        }

        // 2nd: start
        for (Thread thread : threads) {
            thread.start();
        }

        // 3rd: join
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < executorServices.length * 2; i++) {
            executorServices[i % executorServices.length].submit(new Runnable() {
                public void run() {
                    if (integerThreadLocal.get() == null) {
                        integerThreadLocal.set(atomicInteger.getAndIncrement());
                        System.out.printf("my thread id: %d\n", integerThreadLocal.get());
                    } else {
                        System.out.printf("second time, my thread id: %d\n", integerThreadLocal.get());
                    }
                }
            });
        }

        for (ExecutorService executorService : executorServices) {
            executorService.shutdown();
        }

        for (ExecutorService executorService : executorServices) {
            try {
                while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.out.println("Not Close");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}