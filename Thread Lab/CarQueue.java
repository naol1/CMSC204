import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class CarQueue {
    private final Queue<Integer> directionQueue;
    private final Random random;

    public CarQueue() {
        directionQueue = new LinkedList<>();
        random = new Random();
        // Initialize the queue with 5 or 6 random directions
        for (int i = 0; i < 6; i++) {
            directionQueue.add(random.nextInt(4));
        }
    }

    // Adds random directions to the queue using a thread
    public void addToQueue() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (directionQueue) {
                            directionQueue.add(random.nextInt(4)); // Add random direction (0, 1, 2, 3)
                        }
                        Thread.sleep(1000); // Sleep for 1 second
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    // Retrieves and removes a direction from the queue
    public Integer deleteQueue() {
        synchronized (directionQueue) {
            return directionQueue.poll(); // Return and remove the head of the queue
        }
    }
}
