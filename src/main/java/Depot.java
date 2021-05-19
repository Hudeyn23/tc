import java.util.Queue;
import java.util.concurrent.*;

public class Depot extends Thread {
    private final Config config;
    private ScheduledExecutorService executor;
    private final RailwaySystem system;
    private final BlockingQueue<String> orderQueue;
    private Queue<Train> trains;

    public Depot(Config config, RailwaySystem system) {
        this.config = config;
        orderQueue = new ArrayBlockingQueue<>(config.getTrainsNum());
        executor = Executors.newScheduledThreadPool(config.getTrainsNum());
        this.system = system;
        trains = new ConcurrentLinkedQueue<>();
    }

    public void addNewOrder(String name) {
        Log.logInfo("Add order train  " + name + " to depot");
        orderQueue.offer(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String name = orderQueue.take();
                Log.logInfo("Start creating train + name ");
                int createTime = config.getTrainCreateTime(name);
                int speed = config.getTrainSpeed(name);
                int amortizationTime = config.getTrainAmortizationTime(name);
                Depot depot = this;
                executor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        Train train = new Train(name, speed, amortizationTime, config, system, depot);
                        trains.add(train);
                        train.start();
                    }
                }, createTime, TimeUnit.SECONDS);

            } catch (ConfigException e) {

            } catch (InterruptedException e) {
                trains.forEach((Thread::interrupt));
            }
        }
    }
}





