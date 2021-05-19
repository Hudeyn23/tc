public class StationsInfo {
    private final int distance;
    private final int startLoadTrack;
    private final int distLoadTrack;
    private final int trackFromStartToDist;
    private final int trackFromDistToStart;

    public int getDistance() {
        return distance;
    }

    public int getStartLoadTrack() {
        return startLoadTrack;
    }

    public int getDistLoadTrack() {
        return distLoadTrack;
    }

    public int getTrackFromStartToDist() {
        return trackFromStartToDist;
    }

    public int getTrackFromDistToStart() {
        return trackFromDistToStart;
    }

    public StationsInfo(int distance, int startLoadTrack, int distLoadTrack, int trackFromStartToDist, int trackFromDistToStart) {
        this.distance = distance;
        this.startLoadTrack = startLoadTrack;
        this.distLoadTrack = distLoadTrack;
        this.trackFromStartToDist = trackFromStartToDist;
        this.trackFromDistToStart = trackFromDistToStart;
    }
}
