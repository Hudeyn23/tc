public class Consumer extends Thread {
    private final Warehouse warehouse;
    private final int itemConsumeTime;
    private final String name;
    public Consumer(String name, Warehouse warehouse, int itemConsumeTime) {
        this.name = name;
        this.warehouse = warehouse;
        this.itemConsumeTime = itemConsumeTime;
    }

    @Override
    public void run() {
            try {
                while (true) {
                    Thread.sleep(itemConsumeTime);
                    Item item = warehouse.getItem();
                    Log.logInfo("Consumer consume " + name);
                }
            } catch (InterruptedException e) {
                Log.logInfo("Consumer of " + name + " has been stopped" );
            }
    }
}
