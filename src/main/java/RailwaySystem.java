public class RailwaySystem {
    private final Station depart;
    private final Station arrive;
    private final Railway roadToDest;
    private final Railway roadBack;


    public Station getDepart() {
        return depart;
    }

    public Station getArrive() {
        return arrive;
    }

    public Railway getRoadToDest() {
        return roadToDest;
    }

    public RailwaySystem(Station depart, Station arrive, Railway roadToDest, Railway roadBack) {
        this.depart = depart;
        this.arrive = arrive;
        this.roadToDest = roadToDest;
        this.roadBack = roadBack;
    }

    public Railway getRoadBack() {
        return roadBack;
    }


}
