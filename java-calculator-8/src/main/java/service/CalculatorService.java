package service;

import model.ParsedString;
import util.InputParser;

import java.util.List;

public class CalculatorService {

    private final InputParser inputParser;

    public CalculatorService(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public void parse(String input) {
        inputParser.extractCustomDelimetersAndNumbers(input);
    }

    public int calculate() {
        List<Integer> numbers = inputParser.getParsedString().getNumbers();
        int result = 0;
        for(int number : numbers) {
            result += number;
        }
        return result;
    }
}
