package subway.controller;

import subway.domain.*;
import subway.dto.LineMapDTO;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SubwayController {
    private final InputView inputView;
    private final OutputView outputView = new OutputView();
    private final LineRepository lineRepository = new LineRepository();
    private final StationRepository stationRepository = new StationRepository();
    private final LineStationRepository lineStationRepository = new LineStationRepository();
    private final SubwayService subwayService = new SubwayService();

    public SubwayController(Scanner sc) {
        this.inputView = new InputView(sc);
    }

    public void run() {
        subwayService.init();
        start();
    }

    private void start() {
        while(true) {
            try {
                outputView.printMainView();
                String input = inputMain();
                if (input.equals("1")) chooseStationMenu();
                if (input.equals("2")) chooseLineMenu();
                if (input.equals("3")) chooseLineStationMenu();
                if (input.equals("4")) getLineMap();
                if (input.equals("Q")) break;
            } catch(IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void getLineMap() {
        List<LineMapDTO> lineMap = subwayService.getLineMap();
        outputView.printLineMap(lineMap);
    }

    private void chooseLineStationMenu() {
        while(true) {
            try {
                String input = inputLineStationMenu();
                if (input.equals("1")) registerLineStation();
                if (input.equals("2")) deleteLineStation();
                if (input.equals("B")) break;
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void registerLineStation() {
        outputView.inputLineOfRegisterLineStation();
        String inputLine = inputView.inputLineName();
        outputView.inputStationOfRegisterLineStation();
        String inputStation = inputView.inputStationName();
        outputView.inputSequenceOfRegisterLineStation();
        int inputSequence = inputView.inputStationSequence();
        subwayService.registerLineStation(inputLine, inputStation, inputSequence);
    }

    private void deleteLineStation() {
        outputView.inputLineOfDeleteLineStation();
        String inputLineName = inputView.inputLineName();
        outputView.inputStationOfDeleteLineStation();
        String inputStationName = inputView.inputStationName();
        if(subwayService.deleteLineStation(inputLineName, inputStationName)) {
            outputView.printDeleteLineStationSuccessMessage();
        }
    }

    private String inputLineStationMenu() {
        outputView.printLineStationMenu();
        while(true) {
            try {
                outputView.printChooseFunctionMessage();
                String input = inputView.inputLineStationMenu();
                return input;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void chooseLineMenu() {
        while(true) {
            try {
                String input = inputLineMenu();
                if (input.equals("1")) registerLine();
                if (input.equals("2")) deleteLine();
                if (input.equals("3")) findLine();
                if (input.equals("B")) break;
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void registerLine() {
        outputView.printRegisterLine();
        String lineNameInput = inputView.inputLineName();
        outputView.printStartStationOfRegisterLine();
        String startStationNameInput = inputView.inputStationName();
        outputView.printEndStationOfRegisterLine();
        String endStationNameInput = inputView.inputStationName();
        subwayService.registerLine(lineNameInput, startStationNameInput, endStationNameInput);
    }

    private void deleteLine() {
        outputView.printDeleteLine();
        String lineNameInput = inputView.inputLineName();
        if(subwayService.deleteLine(lineNameInput)) {
            outputView.deleteLineSuccessMessage();
        }
    }

    private void findLine() {
        List<Line> lines = subwayService.findAllLines();
        outputView.printLineList(lines);
    }

    private String inputLineMenu() {
        outputView.printLineMenu();
        while(true) {
            try {
                outputView.printChooseFunctionMessage();
                String input = inputView.inputLineMenu();
                return input;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void chooseStationMenu() {
        while(true) {
            try {
                String input = inputStationMenu();
                if (input.equals("1")) registerStation();
                if (input.equals("2")) deleteStation();
                if (input.equals("3")) findStation();
                if (input.equals("B")) break;
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void registerStation() {
        outputView.printRegisterStation();
        String stationNameInput = inputView.inputStationName();
        subwayService.registerStation(stationNameInput);
        outputView.printRegisterStationSuccessMessage();
    }

    private void findStation() {
        List<Station> stations = subwayService.findAllStations();
        outputView.printStationList(stations);
    }

    private void deleteStation() {
        outputView.printDeleteStation();
        String stationNameInput = inputView.inputStationName();
        if(subwayService.deleteStation(stationNameInput)) {
            outputView.printDeleteStationSuccessMessage();
        }
    }

    private String inputStationMenu() {
        outputView.printStationMenu();
        while(true) {
            try {
                outputView.printChooseFunctionMessage();
                String input = inputView.inputStationMenu();
                return input;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public String inputMain() {
        while(true) {
            try {
                outputView.printChooseFunctionMessage();
                String input = inputView.inputMain();
                return input;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
