package oncall.model;

public class MonthAndDate {
    private int month;
    private int date;

    public MonthAndDate(int month, int date) {
        this.month = month;
        this.date = date;
    }

    public static MonthAndDate from(int month, int date) {
        return new MonthAndDate(month, date);
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }
}
