package menu.view;

import camp.nextstep.edu.missionutils.Console;
import menu.util.InputParser;

import java.util.List;

public class InputView {
    public List<String> inputCoachNames() {
        String input = Console.readLine();
        return InputParser.parseCoachNames(input);
    }

    public List<String> inputIgnoreFoodsPerCoach() {
        String input = Console.readLine();
        return InputParser.parseIgnoreFoods(input);
    }
}
