package baseball.view;

import baseball.model.GameResult;

public class OutputView {
    public void printStartMessage() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public void printRoundResult(GameResult gameResult) {
        if(gameResult.ball() != 0) System.out.print(gameResult.ball() + "볼 ");
        if(gameResult.strike() != 0) System.out.print(gameResult.strike() + "스트라이크");
        if(gameResult.ball() == 0 && gameResult.strike() == 0) System.out.print("낫싱");
        System.out.println();
    }

    public void printWinMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    public void printEndMessage() {
        System.out.println("게임을 종료합니다.");
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
