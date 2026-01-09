package vendingmachine.global;

public enum Error{
    MONEY_FORMAT_ERROR("상품 가격은 100원 이상이며 10원으로 나누어떨어져야 합니다."),
    NUMBER_FORMAT_ERROR("금액은 숫자여야 합니다."),
    INPUT_ERROR("입력값이 옳지 않습니다.");

    private final String message;
    private final String prefix = "[ERROR] ";
    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return prefix + this.message;
    }
}
