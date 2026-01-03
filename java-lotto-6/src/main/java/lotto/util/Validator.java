package lotto.util;

import java.util.Collections;
import java.util.List;

public class Validator {
    public static void validatePurchaseAmount(int purchaseAmount) {
        if(purchaseAmount < 1000 || purchaseAmount % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000원 단위 정수입니다.");
    }

    public static void validateWinningNumbers(List<Integer> winningNumbers) {
        if(winningNumbers.size() != 6)
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 총 6개입니다.");
        for(Integer number: winningNumbers) {
            if(number < 1 || number > 45)
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45 범위의 숫자입니다.");
            int sameNumberCount = Collections.frequency(winningNumbers, number);
            if(sameNumberCount > 1) throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복이 불가능 합니다.");
        }
    }

    public static void validateBonusNumber(Integer bonusNumber) {
        if(bonusNumber < 1 || bonusNumber > 45)
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 범위의 숫자입니다.");
    }
}
