public class Consumer extends Thread {
    private final Warehouse warehouse;
    private final int itemConsumeTime;

    public Consumer(String name, Warehouse warehouse, int itemConsumeTime) {
        this.warehouse = warehouse;
        this.itemConsumeTime = itemConsumeTime;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(itemConsumeTime);
                Item item = warehouse.getItem();
                Log.logInfo("Consumer consume "+itemConsumeTime);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
