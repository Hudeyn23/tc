import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    private HashMap<String, TrainInfo> trainInfo;
    private HashMap<String, ItemInfo> itemInfo;
    private StationsInfo stationsInfo;

    public Config() {
        trainInfo = new HashMap<>();
        itemInfo = new HashMap<>();
    }

    public int getDistance() {
        return stationsInfo.getDistance();
    }

    public int getStartLoadTrack() {
        return stationsInfo.getStartLoadTrack();
    }

    public int getDistLoadTrack() {
        return stationsInfo.getDistLoadTrack();
    }

    public int getTrackFromStartToDist() {
        return stationsInfo.getTrackFromStartToDist();
    }

    public int getTrackFromDistToStart() {
        return stationsInfo.getTrackFromDistToStart();
    }

    public List<String> getListOfItems() {
        return new ArrayList<>(itemInfo.keySet());
    }

    public List<String> getListOfTrains() {
        return new ArrayList<>(trainInfo.keySet());
    }

    public Map<String,Integer> getCapacityMap(String name) throws ConfigException {
        if (trainInfo.get(name) != null) {
            return trainInfo.get(name).getCapacity();
        }
        throw new ConfigException("unknown train name");
    }


    public int getTrainsNum() {
        return trainInfo.size();
    }

    public int getTrainSpeed(String name) throws ConfigException {
        if (trainInfo.get(name) != null) {
            return trainInfo.get(name).getSpeed();
        }
        throw new ConfigException("unknown train name");
    }

    public int getTrainAmortizationTime(String name) throws ConfigException {
        if (trainInfo.get(name) != null) {
            return trainInfo.get(name).getAmortizationTime();
        }
        throw new ConfigException("unknown train name");
    }

    public int getTrainCreateTime(String name) throws ConfigException {
        if (trainInfo.get(name) != null) {
            return trainInfo.get(name).getTimeToCreate();
        }
        throw new ConfigException("unknown train name");
    }

    public int getItemLoadingTime(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getTimeToLoad();
        }
        throw new ConfigException("unknown item name");
    }

    public int getItemUnloadingTime(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getTimeToUnload();
        }
        throw new ConfigException("unknown item name");
    }


    public int getFactoryCount(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getFactoryCount();
        }
        throw new ConfigException("unknown item name");
    }

    public int getConsumerCount(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getFactoryCount();
        }
        throw new ConfigException("unknown item name");
    }

    public int getStartCapacity(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getStartWarehouseCapacity();
        }
        throw new ConfigException("unknown item name");
    }

    public int getDistCapacity(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getDistWarehouseCapacity();
        }
        throw new ConfigException("unknown item name");
    }

    public int getTimeToCreateItem(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getTimeToCreate();
        }
        throw new ConfigException("unknown item name");
    }

    public int getTimeToConsume(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getTimeToConsume();
        }
        throw new ConfigException("unknown item name");
    }

    public int getTimeToLoad(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getTimeToLoad();
        }
        throw new ConfigException("unknown item name");
    }

    public int getTimeToUnload(String name) throws ConfigException {
        if (itemInfo.get(name) != null) {
            return itemInfo.get(name).getTimeToUnload();
        }
        throw new ConfigException("unknown item name");
    }


    public void parseJson() {
        InputStream s = getClass().getResourceAsStream("/config.txt");
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(new InputStreamReader(s));
            JSONArray items = (JSONArray) obj.get("items");
            JSONObject stations = (JSONObject) obj.get("stations");
            JSONArray trains = (JSONArray) obj.get("trains");
            for (Object value : items) {
                JSONObject item = (JSONObject) value;
                String name = (String) item.get("name");
                int factoryCount = ((Long) item.get("factoryCount")).intValue();
                int startCapacity = ((Long) item.get("startCapacity")).intValue();
                int distCapacity = ((Long) item.get("distCapacity")).intValue();
                int timeToCreate = ((Long) item.get("timeToCreate")).intValue();
                int timeToConsume = ((Long) item.get("timeToConsume")).intValue();
                int timeToLoad = ((Long) item.get("timeToLoad")).intValue();
                int timeToUnload = ((Long) item.get("timeToUnload")).intValue();
                ItemInfo info = new ItemInfo(name, factoryCount, startCapacity, distCapacity, timeToCreate, timeToConsume, timeToLoad, timeToUnload);
                itemInfo.put(name, info);
            }

            for (Object o : trains) {
                Map<String, Integer> capacityMap = new HashMap<>();
                JSONObject train = (JSONObject) o;
                String name = (String) train.get("name");
                JSONObject capacity = (JSONObject) train.get("capacity");
                for (String itemName : itemInfo.keySet()) {
                    int itemCapacity = ((Long) capacity.get(itemName)).intValue();
                    capacityMap.put(itemName, itemCapacity);
                }
                int speed = ((Long) train.get("speed")).intValue();
                int timeToCreate = ((Long) train.get("timeToCreate")).intValue();
                int amortizationTime = ((Long) train.get("amortizationTime")).intValue();
                TrainInfo info = new TrainInfo(name, speed, capacityMap, timeToCreate, amortizationTime);
                trainInfo.put(name, info);
            }
            int distance = ((Long) stations.get("dist")).intValue();
            int startLoadTrack = ((Long) stations.get("startLoadTrack")).intValue();
            int distLoadTrack = ((Long) stations.get("distLoadTrack")).intValue();
            int trackFromStartToDist = ((Long) stations.get("trackFromStartToDist")).intValue();
            int trackFromDistToStart = ((Long) stations.get("trackFromDistToStart")).intValue();
            stationsInfo = new StationsInfo(distance, startLoadTrack, distLoadTrack, trackFromStartToDist, trackFromDistToStart);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}

