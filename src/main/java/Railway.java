public class Railway {
    private final int capacity;
    private volatile int busyCount;
    private final int distance;

    public int getDistance() {
        return distance;
    }

    public Railway(int capacity, int distance) {
        this.capacity = capacity;
        this.distance = distance;
        busyCount = 0;
    }

    public synchronized void getRailway() throws InterruptedException {
        while (busyCount >= capacity) {
            wait();
        }
        busyCount++;
    }

    public synchronized void freeRailway() throws InterruptedException {
        if (busyCount <= 0) {
            //...
        }
        busyCount--;
        notifyAll();
    }
}
