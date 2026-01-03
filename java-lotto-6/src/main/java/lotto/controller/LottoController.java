package lotto.controller;

import lotto.domain.LottoRepository;
import lotto.domain.LottoResult;
import lotto.domain.WinningNumbersAndBonusNumber;
import lotto.dto.LottosDTO;
import lotto.dto.LottosWinningInfoDTO;
import lotto.dto.PurchaseLottoDTO;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoRepository lottoRepository = new LottoRepository();
    private final LottoResult lottoResult = new LottoResult();
    private final LottoService lottoService = new LottoService(lottoRepository, lottoResult);

    public void run() {
        PurchaseLottoDTO purchaseLottoDTO = getPurchaseAmount();
        generateLottosAndPrintLottosInfo(purchaseLottoDTO);
        WinningNumbersAndBonusNumber winningNumbersAndBonusNumber = getWinningNumbersAndBonusNumber();
        LottosWinningInfoDTO lottosWinningInfoDTO = lottoService.calculateLottos(winningNumbersAndBonusNumber, purchaseLottoDTO);
        outputView.printResult(lottosWinningInfoDTO);
    }

    private WinningNumbersAndBonusNumber getWinningNumbersAndBonusNumber() {
        while(true) {
            try {
                List<Integer> winningNumbers = getWinningNumbers();
                Integer bonusNumber = getBonusNumber();
                return new WinningNumbersAndBonusNumber(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private Integer getBonusNumber() {
        while(true) {
            try {
                outputView.printRequestBonusNumber();
                Integer bonusNumber = inputView.inputBonusNumber();
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private List<Integer> getWinningNumbers() {
        while(true) {
            try {
                outputView.printRequestWinningNumbers();
                List<Integer> winningNumbers = inputView.inputWinningNumbers();
                return winningNumbers;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void generateLottosAndPrintLottosInfo(PurchaseLottoDTO purchaseLottoDTO) {
        LottosDTO lottosDTO = lottoService.generateLottos(purchaseLottoDTO);
        outputView.printPurchasedLottosInfo(lottosDTO);
    }

    private PurchaseLottoDTO getPurchaseAmount() {
        while(true) {
            try {
                outputView.printRequestPurchaseAmount();
                return inputView.inputPurchaseAmount();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
