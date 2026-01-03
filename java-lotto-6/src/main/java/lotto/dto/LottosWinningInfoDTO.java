package lotto.dto;

import lotto.domain.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record LottosWinningInfoDTO(
        List<LottoWinningInfo> lottoWinningInfos,
        double profitRate
) {
    public static LottosWinningInfoDTO from(Map<Rank, Integer> lottoResult, double profitRate) {
        List<LottoWinningInfo> lottoWinningInfos = new ArrayList<>();
        for(Rank rank: lottoResult.keySet()) {
            if(rank == Rank.NONE) continue;
            lottoWinningInfos.add(new LottoWinningInfo(
                            rank.getMatchedWinningNumbersCount(),
                            rank.getMatchedBonusNumber(),
                            rank.getPrizeAmount(),
                            lottoResult.get(rank)
                    )
            );
        }
        return new LottosWinningInfoDTO(lottoWinningInfos, profitRate);
    }

    public record LottoWinningInfo (
        int countMatchedWinningNumbers,
        boolean isBonusMatched,
        int prize,
        int count
    ) {}
}
