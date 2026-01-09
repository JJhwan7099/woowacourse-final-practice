package vendingmachine.service;

import vendingmachine.domain.*;
import vendingmachine.dto.ExchangeInfoDTO;
import vendingmachine.dto.ItemInfoDTO;
import vendingmachine.dto.MachineBalanceInfoDTO;

import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineService {
    private final VendingMachine vendingMachine;
    private final ItemRepository itemRepository;
    private final Balance insertBalance;

    public VendingMachineService(VendingMachine vendingMachine, ItemRepository itemRepository, Balance insertBalance) {
        this.vendingMachine = vendingMachine;
        this.itemRepository = itemRepository;
        this.insertBalance = insertBalance;
    }

    public MachineBalanceInfoDTO setBalance(int money) {
        while(money!=0) {
            Coin coin = Coin.getRandomCoin(money);
            vendingMachine.insertCoin(coin);
            money -= coin.getAmount();
        }
        return new MachineBalanceInfoDTO(vendingMachine.getCoins());
    }

    public void setInsertBalance(int insertMoney) {
        insertBalance.setBalance(insertMoney);
    }

    public boolean buyItem(String itemName) {
        if(!itemRepository.isExist(itemName)) return false;
        int itemPrice = itemRepository.findItemPriceByItemName(itemName);
        if(itemPrice > insertBalance.getBalance()) {
            throw new IllegalArgumentException("[ERROR] 투입 금액이 상품 금액보다 적습니다.");
        }
        insertBalance.minusBalance(itemPrice);
        itemRepository.removeItemByItemName(itemName);
        return true;
    }

    public void registerItems(List<ItemInfoDTO> itemInfos) {
        for(ItemInfoDTO itemInfoDTO : itemInfos) {
            for(int i=0; i<itemInfoDTO.count(); i++)
                itemRepository.add(new Item(itemInfoDTO.name(), itemInfoDTO.price()));
        }
    }

    public boolean isPurchaseAvailable() {
        return itemRepository.getItems().stream()
                .anyMatch(item -> item.getPrice() <= insertBalance.getBalance());
    }

    public ExchangeInfoDTO getExchanges() {
        Map<Coin, Integer> coins = vendingMachine.getCoins();
        EnumMap<Coin, Integer> exchangeCoins = new EnumMap<>(Coin.class);
        for(Coin coin : Coin.values()) {
            int coinCount = getCoinCount(coin, coins);
            exchangeCoins.put(coin, coinCount);
        }
        return ExchangeInfoDTO.from(exchangeCoins);
    }

    private int getCoinCount(Coin coin, Map<Coin, Integer> coins) {
        int cnt = 0;
        for(int i = 0; i< coins.get(coin); i++) {
            if(coin.getAmount() <= insertBalance.getBalance()) {
                insertBalance.minusBalance(coin.getAmount());
                cnt++;
            }
        }
        return cnt;
    }
}
