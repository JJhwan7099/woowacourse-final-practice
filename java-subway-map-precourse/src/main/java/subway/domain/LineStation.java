package subway.domain;

public class LineStation {
    private final Line line;
    private final Station station;
    private int order;

    public LineStation(Line line, Station station, int order) {
        this.line = line;
        this.station = station;
        this.order = order;
    }

    public Line getLine() {
        return line;
    }

    public Station getStation() {
        return station;
    }

    public int getOrder() {
        return order;
    }

    public void increaseOrder() {
        this.order++;
    }

    public void decreaseOrder() {
        this.order--;
    }
}
