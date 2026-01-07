package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderInfoDTO;
import christmas.util.InputParser;

import java.util.List;

public class InputView {

    public int inputDate() {
        return InputParser.parseDate(Console.readLine());
    }

    public List<OrderInfoDTO> inputOrders() {
        return InputParser.parseOrder(Console.readLine());
    }
}
