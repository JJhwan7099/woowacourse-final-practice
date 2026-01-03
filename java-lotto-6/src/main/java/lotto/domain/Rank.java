package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    NONE(null, null, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5,false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private Integer matchedWinningNumbersCount;
    private Boolean isMatchedBonusNumber;
    private Integer prizeAmount;

    Rank(Integer matchedWinningNumbersCount, Boolean isMatchedBonusNumber, Integer prizeAmount) {
        this.matchedWinningNumbersCount = matchedWinningNumbersCount;
        this.isMatchedBonusNumber = isMatchedBonusNumber;
        this.prizeAmount = prizeAmount;
    }

    public static Rank getRank(
            Integer matchedWinningNumbersCount,
            Boolean isMatchedBonusNumber
    ) {
        if(matchedWinningNumbersCount == 6) return FIRST;
        if(matchedWinningNumbersCount == 5 && isMatchedBonusNumber) return SECOND;
        if(matchedWinningNumbersCount == 5 && !isMatchedBonusNumber) return THIRD;
        if(matchedWinningNumbersCount == 4) return FOURTH;
        if(matchedWinningNumbersCount == 3) return FIFTH;
        return NONE;
    }

    public static List<Rank> getKeys() {
        return new ArrayList<>(Arrays.asList(FIFTH, FOURTH, THIRD, SECOND, FIRST));
    }

    public Integer getMatchedWinningNumbersCount() {
        return matchedWinningNumbersCount;
    }

    public Boolean getMatchedBonusNumber() {
        return isMatchedBonusNumber;
    }

    public Integer getPrizeAmount() {
        return prizeAmount;
    }
}
