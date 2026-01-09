package vendingmachine.view;

import vendingmachine.domain.Coin;
import vendingmachine.dto.ExchangeInfoDTO;
import vendingmachine.dto.MachineBalanceInfoDTO;

public class OutputView {
    public void printRequestHaveMoney() {
        System.out.println();
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    public void printBalanceInfo(MachineBalanceInfoDTO machineBalanceInfoDTO) {
        System.out.println();
        System.out.println("자판기가 보유한 동전");
        for(Coin coin : Coin.values()) {
            int coinCount = machineBalanceInfoDTO.coins().get(coin);
            System.out.println(coin.getAmount()+"원 - " + coinCount + "개");
        }
    }

    public void requestItemInfo() {
        System.out.println();
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    }

    public void printRequestInsertMoney() {
        System.out.println();
        System.out.println("투입 금액을 입력해 주세요.");
    }

    public void printInsertMoneyAmount(int insertMoney) {
        System.out.println();
        System.out.println("투입 금액: " + insertMoney + "원");
    }

    public void printRequestItemToBuy() {
        System.out.println();
        System.out.println("구매할 상품명을 입력해 주세요.");
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println();
        System.out.println(e.getMessage());
    }

    public void printExchangeInfos(ExchangeInfoDTO exchangeInfoDTO) {
        System.out.println("잔돈");
        for(ExchangeInfoDTO.CoinInfo coinInfo: exchangeInfoDTO.exchanges()) {
            System.out.println(coinInfo.amount()+"원 - " + coinInfo.count()+"개");
        }
    }
}
