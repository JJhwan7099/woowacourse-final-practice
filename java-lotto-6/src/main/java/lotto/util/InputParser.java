package lotto.util;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static List<Integer> parseInputWinningNumbers(String input) {
        String[] parts = input.split(",",-1);
        List<Integer> winningNumbers = new ArrayList<>();
        for(String part: parts) {
            winningNumbers.add(Integer.parseInt(part.trim()));
        }
        Validator.validateWinningNumbers(winningNumbers);
        return winningNumbers;
    }
}
