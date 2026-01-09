package menu.domain;

public enum Day {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금");

    private final String value;
    Day(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
