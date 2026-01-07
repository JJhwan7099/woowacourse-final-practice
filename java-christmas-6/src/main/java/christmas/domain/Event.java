package christmas.domain;

public enum Event {
    DDAY("크리스마스 디데이 할인"),
    WEEK("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIFT("증정 이벤트");

    private final String title;

    Event(String title) {
        this.title = title;
    }

    public static int getDiscountPrice(Event event, int date, int mainMenuCount, int dessertCount) {
        if(event == Event.DDAY) {
            if(date > 25) return 0;
            return 1000 + (date-1) * 100;
        }
        else if(event == Event.WEEK)
            return 2023 * dessertCount;
        else if(event == Event.WEEKEND)
            return 2023 * mainMenuCount;
        else if(event == Event.SPECIAL)
            return 1000;
        else if(event == Event.GIFT)
            return 25000;
        return 0;
    }

    public String getTitle() {
        return title;
    }
}
