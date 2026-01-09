package vendingmachine.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class VendingMachine {
    private final EnumMap<Coin, Integer> coins = new EnumMap<>(Coin.class);

    public VendingMachine() {
        for(Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
    }

    public void insertCoin(Coin coin) {
        coins.put(coin, coins.get(coin) + 1);
    }

    public Map<Coin, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
