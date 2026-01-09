package vendingmachine.util;

import vendingmachine.global.Error;

public class Validator {

    public static void itemMoneyValidate(int price) {
        if(price % 10 != 0 || price < 100) throw new IllegalArgumentException(Error.MONEY_FORMAT_ERROR.getMessage());
    }
}
