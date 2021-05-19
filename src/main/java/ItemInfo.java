public class ItemInfo {
    private final String name;
    private final int factoryCount;
    private final int startWarehouseCapacity;
    private final int distWarehouseCapacity;
    private final int timeToCreate;
    private final int timeToConsume;
    private final int timeToLoad;
    private final int timeToUnload;

    public String getName() {
        return name;
    }

    public int getFactoryCount() {
        return factoryCount;
    }

    public int getStartWarehouseCapacity() {
        return startWarehouseCapacity;
    }

    public int getDistWarehouseCapacity() {
        return distWarehouseCapacity;
    }

    public int getTimeToCreate() {
        return timeToCreate;
    }

    public int getTimeToConsume() {
        return timeToConsume;
    }

    public int getTimeToLoad() {
        return timeToLoad;
    }

    public int getTimeToUnload() {
        return timeToUnload;
    }

    public ItemInfo(String name, int factoryCount, int startWarehouseCapacity, int distWarehouseCapacity, int timeToCreate, int timeToConsume, int timeToLoad, int timeToUnload) {
        this.name = name;
        this.factoryCount = factoryCount;
        this.startWarehouseCapacity = startWarehouseCapacity;
        this.distWarehouseCapacity = distWarehouseCapacity;
        this.timeToCreate = timeToCreate;
        this.timeToConsume = timeToConsume;
        this.timeToLoad = timeToLoad;
        this.timeToUnload = timeToUnload;
    }
}
