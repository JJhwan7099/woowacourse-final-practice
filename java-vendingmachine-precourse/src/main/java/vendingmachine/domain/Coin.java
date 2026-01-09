package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.List;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현
    public static Coin of(int amount) {
        for(Coin coin: Coin.values()) {
            if(coin.getAmount() == amount) return coin;
        }
        throw new IllegalArgumentException("[ERROR] 자판기에는 500,100,50,10원 단위로만 저장됩니다.");
    }

    public int getAmount() {
        return this.amount;
    }

    public static Coin getRandomCoin(int amount) {
        List<Integer> coins = new java.util.ArrayList<>(Arrays.asList(Coin.values())
                .stream()
                .map(coin -> coin.getAmount())
                .toList());
        for(int i=0; i<coins.size(); i++) {
            if(coins.get(i) > amount) {
                coins.remove(i);
                i--;
            }
        }
        return Coin.of(Randoms.pickNumberInList(coins));
    }
}
