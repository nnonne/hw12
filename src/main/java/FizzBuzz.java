import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    private final int n;
    public static volatile AtomicInteger currentValue = new AtomicInteger(1);
    public BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    public synchronized void add(String element){
        queue.add(element);
    }
    public FizzBuzz(int n) {
        this.n = n;
    }
    private static boolean checkFizz(AtomicInteger number){
        return number.get() % 3 == 0 && number.get() % 5 != 0;
    }
    private static boolean checkBuzz(AtomicInteger number){
        return number.get() % 5 == 0 && number.get() % 3 != 0;
    }
    private static boolean checkFizzBuzz(AtomicInteger number){
        return number.get() % 3 == 0 && number.get() % 5 == 0;
    }
    private static boolean checkNumber(AtomicInteger number){
        return number.get() % 3 != 0 && number.get() % 5 != 0;
    }
    public synchronized void fizz() {
        while (currentValue.get() <= n) {
            if (checkFizz(currentValue)) {
                add("fizz");
                currentValue.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void buzz() {
        while (currentValue.get() <= n) {
            if (checkBuzz(currentValue)) {
                add("buzz");
                currentValue.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void fizzbuzz () {
        while (currentValue.get() <= n) {
            if (checkFizzBuzz(currentValue)) {
                add("fizzbuzz");
                currentValue.incrementAndGet();
                notifyAll();

            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void number() {
        while (currentValue.get() <= n) {
            if (checkNumber(currentValue)) {
                add(String.valueOf(currentValue));
                currentValue.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    public void printFizzBuzz() {
        while (true) {
            while (!queue.isEmpty()) {
                System.out.print(queue.poll() + " ");
            }
        }
    }
}
