package baseball.controller;

import baseball.model.GameNumber;
import baseball.model.GameResult;
import baseball.service.BaseballService;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
//    private final GameNumber gameNumber = new GameNumber();
//    private BaseballService baseballService = new BaseballService(gameNumber);

    public void run() {
        outputView.printStartMessage();
        while(true) {
            gameStart();
            String menuInput = inputView.inputMenu();
            if(menuInput.equals("2")) {
                outputView.printEndMessage();
                break;
            }
        }
    }

    private void gameStart() {
        GameNumber gameNumber = new GameNumber();        // 🔥 여기서 생성
        BaseballService baseballService = new BaseballService(gameNumber);

        baseballService.generateNumber();
        while (true) {
            String input;
            input = inputView.inputNumber();
            GameResult gameResult = baseballService.startRound(input);
            outputView.printRoundResult(gameResult);
            if(baseballService.isWin(gameResult)) {
                outputView.printWinMessage();
                break;
            }
        }
    }
}
