package baseball.service;

import baseball.model.GameNumber;
import baseball.model.GameResult;

public class BaseballService {
    private final GameNumber gameNumber;

    public BaseballService(GameNumber gameNumber) {
        this.gameNumber = gameNumber;
    }

    public GameResult startRound(String input) {
        return gameNumber.getResult(input);
    }

    public boolean isWin(GameResult gameResult) {
        if(gameResult.strike() == 3) return true;
        return false;
    }

    public void generateNumber() {
        gameNumber.generate();
    }
}
