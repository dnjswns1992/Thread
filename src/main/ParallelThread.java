package src.main;

public class ParallelThread {
    public ParallelThread() {
    }

    public static void main(String[] args) {
        Runnable parallelThread = () -> {
            for(int i = 0; i < 5; ++i) {
                sleep(1000);
                System.out.println();
            }

        };
        Thread thread = new Thread(parallelThread);
        thread.start();
        System.out.println("Hello Thread");
    }

    public static void sleep(int m) {
        try {
            Thread.sleep((long)m);
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }
}