package lotto.domain;

import java.util.List;

public class WinningNumbersAndBonusNumber {
    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public WinningNumbersAndBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validate(winningNumbers, bonusNumber);
    }

    public Integer getMatchedWinningNumbersCount(Lotto lotto) {
        Integer matchedWinningNumbersCount = 0;
        for(Integer number : lotto.getNumbers()) {
            if(winningNumbers.contains(number)) matchedWinningNumbersCount++;
        }
        return  matchedWinningNumbersCount;
    }

    public Boolean getIsMatchedBonusNumber(Lotto lotto) {
        if(lotto.getNumbers().contains(bonusNumber)) return true;
        return false;
    }

    private void validate(List<Integer> winningNumbers, Integer bonusNumber) {
        if(winningNumbers.contains(bonusNumber))
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호에 없는 숫자여야 합니다.");
    }
}
