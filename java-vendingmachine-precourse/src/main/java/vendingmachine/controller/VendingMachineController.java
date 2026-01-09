package vendingmachine.controller;

import vendingmachine.domain.Balance;
import vendingmachine.domain.ItemRepository;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.ExchangeInfoDTO;
import vendingmachine.dto.ItemInfoDTO;
import vendingmachine.dto.MachineBalanceInfoDTO;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;

public class VendingMachineController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final VendingMachine vendingMachine = new VendingMachine();
    private final ItemRepository itemRepository = new ItemRepository();
    private final Balance balance = new Balance();
    private final VendingMachineService vendingMachineService = new VendingMachineService(vendingMachine, itemRepository, balance);

    public void run() {
        getMachineBalance();
        getItemInfos();
        getInsertBalance();
        getPurchaseItem();
        getExchange();
    }

    private void getExchange() {
        ExchangeInfoDTO exchangeInfoDTO = vendingMachineService.getExchanges();
        outputView.printExchangeInfos(exchangeInfoDTO);
    }

    private void getPurchaseItem() {
        while(true) {
            try {
                outputView.printInsertMoneyAmount(balance.getBalance());
                if (!vendingMachineService.isPurchaseAvailable()) break;
                outputView.printRequestItemToBuy();
                String itemName = inputView.inputItemNameToBuy();
                if (!vendingMachineService.buyItem(itemName)) break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void getInsertBalance() {
        while(true) {
            try {
                outputView.printRequestInsertMoney();
                int insertMoney = inputView.inputMoney();
                vendingMachineService.setInsertBalance(insertMoney);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void getItemInfos() {
        while(true) {
            try{
                outputView.requestItemInfo();
                List<ItemInfoDTO> itemInfos = inputView.inputItemInfos();
                vendingMachineService.registerItems(itemInfos);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void getMachineBalance() {
        while(true) {
            try {
                outputView.printRequestHaveMoney();
                int inputBalance = inputView.inputMoney();
                MachineBalanceInfoDTO machineBalanceInfoDTO = vendingMachineService.setBalance(inputBalance);
                outputView.printBalanceInfo(machineBalanceInfoDTO);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
