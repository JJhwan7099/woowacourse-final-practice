package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.dto.PurchaseLottoDTO;
import lotto.dto.WinnerNumbersInfoDTO;
import lotto.util.InputParser;
import lotto.util.Validator;

import java.util.List;

public class InputView {
    public PurchaseLottoDTO inputPurchaseAmount() {
        String input = Console.readLine();
        try{
            int purchaseAmount = Integer.parseInt(input);
            Validator.validatePurchaseAmount(purchaseAmount);
            return new PurchaseLottoDTO(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000원 단위 정수입니다.");
        }
    }

    public List<Integer> inputWinningNumbers() {
        String input = Console.readLine();
        try {
            List<Integer> winningNumbers = InputParser.parseInputWinningNumbers(input);
            return winningNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자로만 이루어져 있습니다.");
        }
    }

    public Integer inputBonusNumber() {
        String input = Console.readLine();
        try {
            Integer bonusNumber = Integer.parseInt(input);
            Validator.validateBonusNumber(bonusNumber);
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자입니다.");
        }
    }
}
