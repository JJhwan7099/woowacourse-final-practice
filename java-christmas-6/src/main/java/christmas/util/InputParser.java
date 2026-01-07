package christmas.util;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static int parseDate(String input) {
        try {
            int date = Integer.parseInt(input);
            if(date < 1 || date > 31) throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static List<OrderInfoDTO> parseOrder(String input) {
        List<OrderInfoDTO> orderInfos = new ArrayList<>();
        try {
            String[] parts = input.split(",",-1);
            for (String part : parts) {
                String[] order = part.split("-",-1);
                if(order.length != 2) throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                orderInfos.add(new OrderInfoDTO(order[0].trim(), Integer.parseInt(order[1].trim())));
            }
            return orderInfos;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
