package subway.dto;

import subway.domain.Station;

import java.util.List;

public class LineMapDTO {
    private String lineName;
    private List<Station> stations;

    public LineMapDTO(String lineName, List<Station> stations) {
        this.lineName = lineName;
        this.stations = stations;
    }

    public String getLineName() {
        return lineName;
    }

    public List<Station> getStations() {
        return stations;
    }
}
