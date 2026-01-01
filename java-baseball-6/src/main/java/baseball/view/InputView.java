package baseball.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputNumber() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();
        if(input.length() != 3) throw new IllegalArgumentException("[ERROR] 입력 숫자는 3자리 숫자입니다.");
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
        return input;
    }

    public String inputMenu() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return Console.readLine();
    }
}
