import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task2 {
    public static void main(String[] args) {
        int n = 17;
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(fizzBuzz::fizz);
        service.submit(fizzBuzz::buzz);
        service.submit(fizzBuzz::fizzbuzz);
        service.submit(fizzBuzz::number);
        BlockingQueue<String> queue = fizzBuzz.getQueue();
        while(true){
            while (!queue.isEmpty()) {
                System.out.print(queue.poll() + " ");
            }
        }
    }
}
