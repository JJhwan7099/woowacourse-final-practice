package lotto.view;

import lotto.dto.LottosDTO;
import lotto.dto.LottosWinningInfoDTO;

import java.text.DecimalFormat;

public class OutputView {

    private final DecimalFormat formatter = new DecimalFormat("###,###");

    public void printRequestPurchaseAmount() {
        System.out.println();
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println();
        System.out.println(e.getMessage());
    }

    public void printPurchasedLottosInfo(LottosDTO lottosDTO) {
        System.out.println();
        System.out.println(lottosDTO.lottoInfos().size() + "개를 구매했습니다.");
        for(LottosDTO.LottoInfo lottoInfo : lottosDTO.lottoInfos()) {
            System.out.println(lottoInfo.lottoNumbers());
        }
    }

    public void printRequestWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printRequestBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printResult(LottosWinningInfoDTO lottosWinningInfoDTO) {
        System.out.println();
        System.out.println("당첨 통계\n---");
        for(LottosWinningInfoDTO.LottoWinningInfo lottoWinningInfo : lottosWinningInfoDTO.lottoWinningInfos()) {
            System.out.print(lottoWinningInfo.countMatchedWinningNumbers() + "개 일치");
            if(lottoWinningInfo.isBonusMatched()) System.out.print(", 보너스 볼 일치");
            System.out.println(" ("+formatter.format(lottoWinningInfo.prize())+"원) - " + lottoWinningInfo.count() + "개");
        }
        System.out.println("총 수익률은 " + lottosWinningInfoDTO.profitRate() + "%입니다.");
    }
}
