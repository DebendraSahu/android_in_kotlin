package task.week1;


/**
 * **Task 14:** Implement a simple thread in Java using the `Runnable` interface and the `Thread` class.
 */
public class W1t14 {
    public static final int MILLI_SEC_TO_SECONDS = 1000;

    public static void main(String[] args) throws InterruptedException {
        RunnerThread printer = new RunnerThread();
        Thread thread = new Thread(printer);
        thread.start();
        Thread.sleep(5 * MILLI_SEC_TO_SECONDS);
        thread.interrupt();
    }
}

class RunnerThread implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (i < 100) {
            System.out.println("iterating for " + i + "th time");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
            i++;
        }
    }
}


