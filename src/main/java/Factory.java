public class Factory extends Thread {
    private final Warehouse warehouse;
    private final String itemName;
    private final int itemCreateTime;

    public Factory(String name, Warehouse warehouse, int itemCreateTime) {
        this.warehouse = warehouse;
        this.itemName = name;
        this.itemCreateTime = itemCreateTime;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(itemCreateTime);
                Item item = new Item(itemName);
                Log.logInfo("Factory create " + itemName);
                warehouse.addItem(item);
            } catch (InterruptedException e) {
                Log.logInfo("Factory of "+ itemName + " has been stopped");
                return;
            }
        }
        Log.logInfo("Factory of "+ itemName + " has been stopped");
    }
}
