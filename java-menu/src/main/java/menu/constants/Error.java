package menu.constants;

public enum Error {
    CATEGORY_NOT_FOUND("존재하지 않는 카테고리 입니다."),
    COACH_NUMBER_ERROR("코치는 최소 2명 이상 입력해야 합니다."),
    COACH_NAME_LENGTH_INVALID("코치의 이름은 최소 2글자 최대 4글자입니다."),
    COACH_NOT_FOUND("존재하지 않는 코치입니다."),
    FOOD_NOT_FOUND("존재하지 않는 음식입니다."),
    COACH_ALREADY_EXIST("이미 존재하는 코치입니다."),
    COACH_NAME_DUPLICATED("코치의 이름은 모두 달라야합니다."),
    IGNORE_FOOD_COUNT_OVER("못 먹는 메뉴는 최대 2개입니다.");

    private final String message;
    private final String prefix = "[ERROR] ";

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return prefix + message;
    }
}
