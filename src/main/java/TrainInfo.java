import java.util.HashMap;
import java.util.Map;

public class TrainInfo {
    private final String name;
    private final int speed;
    private final Map<String, Integer> itemCapacity;
    private final int timeToCreate;
    private final int amortizationTime;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Map<String, Integer> getCapacity() {
        return itemCapacity;
    }

    public int getTimeToCreate() {
        return timeToCreate;
    }

    public int getAmortizationTime() {
        return amortizationTime;
    }

    public TrainInfo(String name, int speed, Map<String, Integer> itemCapacity, int timeToCreate, int amortizationTime) {
        this.name = name;
        this.speed = speed;
        this.timeToCreate = timeToCreate;
        this.amortizationTime = amortizationTime;
        this.itemCapacity = new HashMap<>(itemCapacity);
    }
}
