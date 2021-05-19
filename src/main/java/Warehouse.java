import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Warehouse {
    private final int capacity;
    private BlockingQueue<Item> items;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        items = new ArrayBlockingQueue<>(capacity);
    }

    public void addItem(Item item) throws InterruptedException {
        items.put(item);
        Log.logInfo("Add  " + item.getName() + " to warehouse");
    }

    public Item getItem() throws InterruptedException {
        Item item = items.take();
        Log.logInfo("Get  " + item.getName() + " from warehouse");
        return item;
    }

}
