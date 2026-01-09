package menu.util;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static List<String> parseCoachNames(String input) {
        List<String> names = new ArrayList<>();
        String[] parts = input.split(",",-1);
        for(String part : parts) {
            names.add(part.trim());
        }
        Validator.validateCoachNamesInput(names);
        return names;
    }

    public static List<String> parseIgnoreFoods(String input) {
        List<String> foods = new ArrayList<>();
        String[] parts = input.split(",",-1);
        for(String part : parts) {
            if(!part.trim().isBlank()) foods.add(part.trim());
        }
        Validator.validateIgnoreFoodsPerCoachInput(foods);
        return foods;
    }
}
