package racingcar.controller;

import racingcar.dto.RoundResult;
import racingcar.repository.CarRepository;
import racingcar.service.RacingCarService;
import racingcar.util.InputParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingCarController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CarRepository carRepository = new CarRepository();
    private final RacingCarService racingCarService = new RacingCarService(carRepository);

    public void run() {
        String[] parsedCarNames;
        parsedCarNames = inputAndGetParsedCarNames();
        int round = inputRound();
        racingCarService.saveCars(parsedCarNames);
        List<RoundResult> roundResults = racingCarService.race(round);
        outputView.roundResultOutput(roundResults);
        outputView.winnersOutput(racingCarService.getWinners());
    }

    private int inputRound() {
        int round;
        while(true) {
            try {
                round = inputView.roundInput();
                break;
            } catch (Exception e) {
                outputView.errorOutput(e);
            }
        }
        return round;
    }

    private String[] inputAndGetParsedCarNames() {
        String input;
        while(true) {
            try {
                input = inputView.carNamesInput();
                return InputParser.parseInput(input);
            } catch (IllegalArgumentException e) {
                outputView.errorOutput(e);
            }
        }
    }

    private boolean isTestEnvironment() {
        return System.getProperty("test") != null;
    }
}
