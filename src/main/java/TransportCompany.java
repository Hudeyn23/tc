import java.util.HashMap;
import java.util.Map;

public class TransportCompany {
    public void startCompany(String configName) {
        Config config = new Config();
        try {
            config.parseJson();
            Map<String, Warehouse> startWarehouseMap = new HashMap<>();
            Map<String, Warehouse> endWarehouseMap = new HashMap<>();
            for (String item : config.getListOfItems()) {
                Warehouse warehouse = new Warehouse(config.getStartCapacity(item));
                Warehouse endWarehouse = new Warehouse(config.getDistCapacity(item));
                startWarehouseMap.put(item, warehouse);
                endWarehouseMap.put(item, endWarehouse);
                for (int i = 0; i < config.getFactoryCount(item); i++) {
                    Factory factory = new Factory(item, warehouse, config.getTimeToCreateItem(item));
                    factory.start();
                }
                for (int i = 0; i < config.getConsumerCount(item); i++) {
                    Consumer consumer = new Consumer(item, endWarehouse, config.getTimeToConsume(item));
                }
            }
            Station start = new Station(config.getStartLoadTrack(), startWarehouseMap);
            Station end = new Station(config.getDistLoadTrack(), endWarehouseMap);
            Railway fromStartToEnd = new Railway(config.getTrackFromStartToDist(), config.getDistance());
            Railway fromEndToStart = new Railway(config.getTrackFromDistToStart(), config.getDistance());
            RailwaySystem system = new RailwaySystem(start, end, fromStartToEnd, fromEndToStart);
            Depot depo = new Depot(config, system);
            depo.start();
            for (String trainName : config.getListOfTrains()) {
                depo.addNewOrder(trainName);
            }
        } catch (ConfigException e) {

        }
    }
}
