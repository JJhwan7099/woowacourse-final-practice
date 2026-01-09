package vendingmachine.dto;

import vendingmachine.domain.Coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ExchangeInfoDTO (
        List<CoinInfo> exchanges
) {
    public static ExchangeInfoDTO from(Map<Coin, Integer> coins) {
        List<CoinInfo> exchanges = new ArrayList<>();
        for(Coin coin : Coin.values()) {
            if(coins.get(coin) != 0) {
                exchanges.add(new CoinInfo(coin.getAmount(), coins.get(coin)));
            }
        }
        return new ExchangeInfoDTO(exchanges);
    }

    public record CoinInfo(
            int amount,
            int count
    ) {}
}
