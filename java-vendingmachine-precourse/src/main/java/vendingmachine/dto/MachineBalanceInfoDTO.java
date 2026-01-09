package vendingmachine.dto;

import vendingmachine.domain.Coin;

import java.util.Map;

public record MachineBalanceInfoDTO (
        Map<Coin, Integer> coins
) {
}
