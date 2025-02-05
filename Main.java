import java.util.Random;

class NumberPrinter extends Thread {
    private int threadNumber;
    private static final int MAX_COUNT = 50;
    private static final Random rand = new Random();

    public NumberPrinter(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= MAX_COUNT; i++) {
            System.out.println("thread #" + threadNumber + ": " + i);
            try {
                // Adding a random sleep time between 10ms to 100ms
                Thread.sleep(rand.nextInt(91) + 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MultiThreadingExample {
    public static void main(String[] args) {
        // Creating three threads
        NumberPrinter t1 = new NumberPrinter(1);
        NumberPrinter t2 = new NumberPrinter(2);
        NumberPrinter t3 = new NumberPrinter(3);

        // Starting the threads
        t1.start();
        t2.start();
        t3.start();

        /*
         * Observations before adding Thread.sleep():
         * - Each thread runs independently and prints numbers quickly.
         * - The order of execution is unpredictable because the OS manages thread scheduling.
         * - Sometimes one thread prints many numbers in a row before switching.
         *
         * Observations after adding Thread.sleep():
         * - Threads now pause briefly after printing each number.
         * - The numbers from different threads are more interleaved due to the random sleep.
         * - No single thread dominates execution, making output more evenly mixed.
         *
         * Explanation:
         * - Java uses preemptive multithreading, meaning the OS schedules when each thread runs.
         * - Without Thread.sleep(), threads may run longer before switching.
         * - With Thread.sleep(), the OS gets more chances to switch between threads, resulting in more interleaved output.
         */
    }
}
