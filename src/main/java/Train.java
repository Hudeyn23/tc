import java.util.*;

public class Train extends Thread {
    private boolean shutdown;
    private String name;
    private int speed;
    private int amortizationTime;
    private Config config;
    Map<String, List<Item>> items;
    RailwaySystem railwaySystem;
    Depot depo;

    public Train(String name, int speed, int amortizationTime, Config config, RailwaySystem railwaySystem, Depot depo) {
        shutdown = false;
        this.name = name;
        this.speed = speed;
        this.amortizationTime = amortizationTime;
        this.config = config;
        this.railwaySystem = railwaySystem;
        items = new HashMap<>();
        this.depo = depo;
    }


    private void load(Station station) throws ConfigException, InterruptedException {
        station.startLoad(this);
        for (Map.Entry<String, Integer> entry : config.getCapacityMap(name).entrySet()) {
            String item = entry.getKey();
            Warehouse warehouse = station.getWarehouse(item);
            for (int i = 0; i < entry.getValue(); i++) {
                Thread.sleep(config.getTimeToLoad(item));
                items.computeIfAbsent(item, (x) -> new ArrayList<>()).add(warehouse.getItem());
            }
        }
        station.endLoad(this);
    }


    private void unload(Station station) throws InterruptedException, ConfigException {
        station.startLoad(this);
        for (Map.Entry<String, Integer> entry : config.getCapacityMap(name).entrySet()) {
            String item = entry.getKey();
            Warehouse warehouse = station.getWarehouse(item);
            for (int i = 0; i < entry.getValue(); i++) {
                Thread.sleep(config.getTimeToUnload(item));
                warehouse.addItem(items.get(item).get(0));
            }
        }
        station.endLoad(this);
    }

    @Override
    public void run() {
        long startTime = Calendar.getInstance().getTimeInMillis() / 1000;
        try {
            while ((Calendar.getInstance().getTimeInMillis() / 1000 - startTime) < amortizationTime) {
                load(railwaySystem.getDepart());
                railwaySystem.getRoadToDest().getRailway();
                Log.logInfo("Train  " + name + "start moving");
                int dist = 0;
                while (dist < railwaySystem.getRoadToDest().getDistance()) {
                    dist += speed;
                    Thread.sleep(1000);
                }
                Log.logInfo("Train  " + name + "arrive");
                railwaySystem.getRoadToDest().freeRailway();
                unload(railwaySystem.getArrive());
                railwaySystem.getRoadBack().getRailway();
                Log.logInfo("Train  " + name + "start moving back");
                dist = 0;
                while (dist < railwaySystem.getRoadBack().getDistance()) {
                    dist += speed;
                    Thread.sleep(1000);
                }
                railwaySystem.getRoadBack().freeRailway();
                Log.logInfo("Train  " + name + "arrive back");
            }
                depo.addNewOrder(name);
        } catch (InterruptedException e) {
            Log.logInfo("Train " + name + "is stopped");
        } catch (ConfigException e) {
            Log.logError("Can`t get config data for train" + name);
        }
    }
}
