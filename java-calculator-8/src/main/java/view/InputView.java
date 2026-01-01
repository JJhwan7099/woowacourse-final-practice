package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String mainInput() {
        String input = Console.readLine();
        if(input == null || input.isEmpty())
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        return input;
    }
}
