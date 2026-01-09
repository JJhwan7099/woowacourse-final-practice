package menu.util;

import menu.constants.Error;

import java.util.List;

public class Validator {
    public static void validateCoachNamesInput(List<String> names) {
        if(names.size() < 2) throw new IllegalArgumentException(Error.COACH_NUMBER_ERROR.getMessage());
        for(String name : names) {
            if (name.length() < 2 || name.length() > 4)
                throw new IllegalArgumentException(Error.COACH_NAME_LENGTH_INVALID.getMessage());
            if (names.stream().filter(n -> n.equals(name)).count() > 1)
                throw new IllegalArgumentException(Error.COACH_NAME_DUPLICATED.getMessage());
        }
    }

    public static void validateIgnoreFoodsPerCoachInput(List<String> foods) {
        if(foods.size() > 2) throw new IllegalArgumentException(Error.IGNORE_FOOD_COUNT_OVER.getMessage());
    }
}
