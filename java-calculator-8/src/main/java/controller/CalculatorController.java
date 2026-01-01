package controller;

import service.CalculatorService;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class CalculatorController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final InputParser inputParser = new InputParser();
    private final CalculatorService calculatorService = new CalculatorService(inputParser);

    public void run() {
        String input = inputView.mainInput();
        calculatorService.parse(input);
        outputView.resultOutput(calculatorService.calculate());
    }
}
