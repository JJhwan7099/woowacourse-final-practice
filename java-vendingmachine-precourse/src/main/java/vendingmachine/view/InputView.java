package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.ItemInfoDTO;
import vendingmachine.util.InputParser;
import vendingmachine.util.Validator;

import java.util.List;

public class InputView {
    public int inputMoney() {
        String input = Console.readLine();
        int money = InputParser.parseInputNumber(input);
        Validator.itemMoneyValidate(money);
        return money;
    }

    public List<ItemInfoDTO> inputItemInfos() {
        String input = Console.readLine();
        return InputParser.parseItemInfos(input);
    }

    public String inputItemNameToBuy() {
        return Console.readLine();
    }
}
