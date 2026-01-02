package subway.service;

import subway.domain.*;
import subway.dto.LineMapDTO;

import java.util.*;
import java.util.stream.Collectors;

public class SubwayService {

    private final List<String> baseStations = new ArrayList<>(
            Arrays.asList("교대역", "강남역", "역삼역","남부터미널역", "양재역", "양재시민의숲역", "매봉역")
    );

    private final List<String> baseLines = new ArrayList<>(
            Arrays.asList("2호선", "3호선", "신분당선")
    );

    public void init() {
        for(String baseStation: baseStations) {
            StationRepository.addStation(new Station(baseStation));
        }
        for(String baseLine: baseLines) {
            LineRepository.addLine(new Line(baseLine));
        }
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("2호선"), StationRepository.getStationByName("교대역"), 1));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("2호선"), StationRepository.getStationByName("강남역"), 2));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("2호선"), StationRepository.getStationByName("역삼역"), 3));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("3호선"), StationRepository.getStationByName("교대역"), 1));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("3호선"), StationRepository.getStationByName("남부터미널역"), 2));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("3호선"), StationRepository.getStationByName("양재역"), 3));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("3호선"), StationRepository.getStationByName("매봉역"), 4));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("신분당선"), StationRepository.getStationByName("강남역"), 1));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("신분당선"), StationRepository.getStationByName("양재역"), 2));
        LineStationRepository.addLineStation(
                new LineStation(LineRepository.getLineByName("신분당선"), StationRepository.getStationByName("양재시민의숲역"), 3));
    }

    public void registerStation(String stationNameInput) {
        if(StationRepository.getStationByName(stationNameInput) != null) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 역입니다.");
        }
        StationRepository.addStation(new Station(stationNameInput));
    }

    public List<Station> findAllStations() {
        return StationRepository.stations();
    }

    public boolean deleteStation(String stationNameInput) {
        if(LineStationRepository.isAllocatedByStationName(stationNameInput)) {
            throw new IllegalArgumentException("[ERROR] 노선에 등록된 역은 삭제할 수 없습니다.");
        }
        return StationRepository.deleteStation(stationNameInput);
    }

    public void registerLine(String lineNameInput, String startStationNameInput, String endStationNameInput) {
        if(LineRepository.getLineByName(lineNameInput) != null) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 노선입니다.");
        }
        Station startStation = StationRepository.getStationByName(startStationNameInput);
        Station endStation = StationRepository.getStationByName(endStationNameInput);
        if(startStation == null || endStation == null)
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 역은 등록할 수 없습니다.");
        LineRepository.addLine(new Line(lineNameInput));
        LineStationRepository.addLineStation(new LineStation(LineRepository.getLineByName(lineNameInput), startStation, 1));
        LineStationRepository.addLineStation(new LineStation(LineRepository.getLineByName(lineNameInput), endStation, 2));
    }

    public boolean deleteLine(String lineNameInput) {
        //Line 삭제 및 Line이 포함된 LineStation 삭제
        return LineStationRepository.deleteLineStationByLineName(lineNameInput) || LineRepository.deleteLineByName(lineNameInput);
    }

    public List<Line> findAllLines() {
        return LineRepository.lines();
    }

    public void registerLineStation(String inputLine, String inputStation, int inputSequence) {
        Line line = LineRepository.getLineByName(inputLine);
        if(line == null) throw new IllegalArgumentException("[ERROR] 존재하지 않는 노선입니다.");
        Station station = StationRepository.getStationByName(inputStation);
        if(station == null) throw new IllegalArgumentException("[ERROR] 존재하지 않는 역입니다.");
        if(LineStationRepository.isAlreadyInLineByStation(line, station))
            throw new IllegalArgumentException("[ERROR] 해당 노선에 이미 존재하는 역입니다.");
        LineStationRepository.addStationToLineStation(line, station, inputSequence);
    }

    public boolean deleteLineStation(String inputLineName, String inputStationName) {
        Line line = LineRepository.getLineByName(inputLineName);
        if(line == null) throw new IllegalArgumentException("[ERROR] 존재하지 않는 노선입니다.");
        Station station = StationRepository.getStationByName(inputStationName);
        if(station == null) throw new IllegalArgumentException("[ERROR] 존재하지 않는 역입니다.");
        return LineStationRepository.deleteLineStationByStationAndLine(line, station);
    }

    public List<LineMapDTO> getLineMap() {
        List<LineMapDTO> lineMap = new ArrayList<>();
        List<Line> lines = new ArrayList<>(LineRepository.lines());
        lines.sort(Comparator.comparingInt(line -> {
            String name = line.getName();
            if (Character.isDigit(name.charAt(0))) {
                return Integer.parseInt(name.replaceAll("[^0-9]", ""));
            }
            return Integer.MAX_VALUE;
        }));
        for(Line line: lines) {
            List<LineStation> lineStations = LineStationRepository.findLineStationsByLine(line);
            lineStations.sort(Comparator.comparing(LineStation::getOrder));
            List<Station> stations = lineStations.stream()
                    .map(lineStation -> new Station(lineStation.getStation().getName()))
                    .collect(Collectors.toList());
            lineMap.add(new LineMapDTO(line.getName(), stations));
        }
        return lineMap;
    }
}
