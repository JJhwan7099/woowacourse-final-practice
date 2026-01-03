package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.*;
import lotto.dto.LottosDTO;
import lotto.dto.LottosWinningInfoDTO;
import lotto.dto.PurchaseLottoDTO;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private final LottoRepository lottoRepository;
    private final LottoResult lottoResult;

    public LottoService(LottoRepository lottoRepository, LottoResult lottoResult) {
        this.lottoRepository = lottoRepository;
        this.lottoResult = lottoResult;
    }

    public LottosDTO generateLottos(PurchaseLottoDTO purchaseLottoDTO) {
        int purchaseAmount = purchaseLottoDTO.purchaseAmount();
        for(int i=0; i<purchaseAmount/1000; i++) {
            lottoRepository.addLotto(new Lotto(generateLottoNumbers()));
        }
        return LottosDTO.from(lottoRepository.getLottos());
    }

    private List<Integer> generateLottoNumbers() {
        return Randoms
                .pickUniqueNumbersInRange(1,45,6)
                .stream().sorted().toList();
    }

    public LottosWinningInfoDTO calculateLottos(WinningNumbersAndBonusNumber winningNumbersAndBonusNumber, PurchaseLottoDTO purchaseLottoDTO) {
        List<Lotto> lottos = lottoRepository.getLottos();
        for(Lotto lotto : lottos) {
            Rank rank = getRank(winningNumbersAndBonusNumber, lotto);
            lottoResult.increaseRankCount(rank);
        }
        double profitRate = calculateProfitRate(lottoResult.getResults(), purchaseLottoDTO.purchaseAmount());
        return LottosWinningInfoDTO.from(lottoResult.getResults(), profitRate);
    }

    private double calculateProfitRate(Map<Rank, Integer> lottoResult, int purchaseAmount) {
        int totalPrizeAmount = 0;
        for(Rank rank : lottoResult.keySet()) {
            if(rank == Rank.NONE) continue;
            totalPrizeAmount += rank.getPrizeAmount() * lottoResult.get(rank);
        }
        return Math.round(((double) totalPrizeAmount/(double) purchaseAmount) * 100.0 * 10.0) / 10.0;
    }

    private static Rank getRank(WinningNumbersAndBonusNumber winningNumbersAndBonusNumber, Lotto lotto) {
        Integer matchedWinningNumbersCount = winningNumbersAndBonusNumber.getMatchedWinningNumbersCount(lotto);
        Boolean isMatchedBonusNumber = winningNumbersAndBonusNumber.getIsMatchedBonusNumber(lotto);
        return Rank.getRank(matchedWinningNumbersCount, isMatchedBonusNumber);
    }
}
