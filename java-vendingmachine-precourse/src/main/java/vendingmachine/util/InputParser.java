package vendingmachine.util;

import vendingmachine.dto.ItemInfoDTO;
import vendingmachine.global.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static int parseInputNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.NUMBER_FORMAT_ERROR.getMessage());
        }
    }

    public static List<ItemInfoDTO> parseItemInfos(String input) {
        try {
            List<ItemInfoDTO> infoDTOS = new ArrayList<>();
            String[] parts = input.split(";", -1);
            for (String part : parts) {
                part = part.replaceAll("\\[", "").replaceAll("]", "").trim();
                String[] token = part.split(",", -1);
                if (token.length != 3) throw new IllegalArgumentException(Error.INPUT_ERROR.getMessage());
                infoDTOS.add(new ItemInfoDTO(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2])));
            }
            return infoDTOS;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.NUMBER_FORMAT_ERROR.getMessage());
        }
    }
}
