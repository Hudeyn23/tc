import java.util.Queue;
import java.util.concurrent.*;

public class Depot {
    private final Config config;
    private ScheduledExecutorService executor;
    private final RailwaySystem system;
    private Queue<Train> trains;

    public Depot(Config config, RailwaySystem system) {
        this.config = config;
        executor = Executors.newScheduledThreadPool(config.getTrainsNum());
        this.system = system;
        trains = new ConcurrentLinkedQueue<>();
    }

    public void addNewOrder(String name) {
        Log.logInfo("Add order train  " + name + " to depot");
        Log.logInfo("Start creating train + name ");
        try {
            int createTime = config.getTrainCreateTime(name);
            int speed = config.getTrainSpeed(name);
            int amortizationTime = config.getTrainAmortizationTime(name);
            Depot depot = this;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    Train train = new Train(name, speed, amortizationTime, config, system, depot);
                    trains.add(train);
                    Log.logInfo("Train " + name + " was created");
                    train.start();
                }
            }, createTime, TimeUnit.SECONDS);
        } catch (ConfigException e) {

        }
    }
}





