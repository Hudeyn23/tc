import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private String name;
    private final static AtomicInteger counter = new AtomicInteger();
    private final int id;

    public Item(String name) {
        this.name = name;
        id = counter.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
