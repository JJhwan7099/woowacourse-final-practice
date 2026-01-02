package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LineStationRepository {
    private static final List<LineStation> lineStations = new ArrayList<>();

    public static List<LineStation> lineStations() {
        return Collections.unmodifiableList(lineStations);
    }

    public static void addLineStation(LineStation lineStation) {
        lineStations.add(lineStation);
    }

    public static boolean deleteLineStationByLineName(String lineName) {
        return lineStations.removeIf(lineStation -> Objects.equals(lineStation.getLine().getName(), lineName));
    }

    public static boolean deleteLineStationByStationAndLine(Line line, Station station) {
        int findStationOrder = findSequenceByStationAndLine(line, station);
        if(findStationOrder == -1) return false;
        for(int i=0; i<lineStations.size(); i++) {
            LineStation lineStation = lineStations.get(i);
            if(line.equals(lineStation.getLine()) && lineStation.getOrder() > findStationOrder) {
                lineStations.get(i).decreaseOrder();
            }
        }
        return lineStations.removeIf(
                lineStation -> line.equals(lineStation.getLine()) && station.equals(lineStation.getStation())
        );
    }

    public static boolean isAllocatedByStationName(String stationNameInput) {
        for(LineStation lineStation: lineStations) {
            if(lineStation.getStation().getName().equals(stationNameInput))
                return true;
        }
        return false;
    }

    public static void addStationToLineStation(Line line, Station station, int sequence) {
        for(int i=0; i<lineStations.size(); i++) {
            if(lineStations.get(i).getLine().equals(line) && lineStations.get(i).getOrder() >= sequence) {
                lineStations.get(i).increaseOrder();
            }
        }
        addLineStation(new LineStation(line, station, sequence));
    }

    public static int findSequenceByStationAndLine(Line line, Station station) {
        for(LineStation lineStation: lineStations) {
            if(lineStation.getLine().equals(line) && lineStation.getStation().equals(station)) {
                return lineStation.getOrder();
            }
        }
        return -1;
    }

    public static List<LineStation> findLineStationsByLine(Line line) {
        return lineStations.stream()
                .filter(lineStation -> lineStation.getLine().equals(line))
                .collect(Collectors.toList());
    }

    public static boolean isAlreadyInLineByStation(Line line, Station station) {
        return lineStations.stream()
                .filter(lineStation -> lineStation.getLine().getName().equals(line.getName()))
                .anyMatch(lineStation -> lineStation.getStation().getName().equals(station.getName()));
    }
}
