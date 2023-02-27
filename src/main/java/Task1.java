import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(
                () -> System.out.println("Time: " + (System.currentTimeMillis() - currentTime)),
                0,
                1,
                TimeUnit.SECONDS
        );
        //Thread.sleep(1000);
        executorService.scheduleAtFixedRate(
                () -> System.out.println("Пройшло 5 секунд"),
                1,
                5,
                TimeUnit.SECONDS
        );
    }

}

