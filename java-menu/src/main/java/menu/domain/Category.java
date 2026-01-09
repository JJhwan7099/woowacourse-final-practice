package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.constants.Error;

public enum Category {
    KOREA("한식" ,2),
    JAPAN("일식", 1),
    CHINA("중식", 3),
    ASIAN("아시안", 4),
    WESTERN("양식", 5);

    private final String type;
    private final int num;
    Category(String type, int num) {
        this.type = type;
        this.num = num;
    }

    public static Category of(String type) {
        for(Category category : Category.values()) {
            if(category.getType().equals(type)) return category;
        }
        throw new IllegalArgumentException(Error.CATEGORY_NOT_FOUND.getMessage());
    }

    public static Category of(int num) {
        for(Category category : Category.values()) {
            if(category.getNum() == num) return category;
        }
        throw new IllegalArgumentException(Error.CATEGORY_NOT_FOUND.getMessage());
    }

    public String getType() {
        return type;
    }

    public int getNum() {
        return num;
    }

    public static Category getRandomCategory() {
        return Category.of(Randoms.pickNumberInRange(1,5));
    }
}
