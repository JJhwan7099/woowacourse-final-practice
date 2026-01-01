package oncall.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartMonthAndDay{
    private final int startMonth;
    private final String startDay;
    private int dayOfMonth;
    private List<MonthAndDate> holidays;

    private List<String> days = new ArrayList<>(Arrays.asList("월","화","수","목","금","토","일"));

    public StartMonthAndDay(int startMonth, String startDay) {
        this.startMonth = startMonth;
        this.startDay = startDay;
        dayOfMonth = Month.of(startMonth).length(false);
        setHolidays();
    }

    private void setHolidays() {
        holidays = new ArrayList<>(Arrays.asList(
                MonthAndDate.from(1,1),
                MonthAndDate.from(3,1),
                MonthAndDate.from(5,5),
                MonthAndDate.from(6,6),
                MonthAndDate.from(8,15),
                MonthAndDate.from(10,3),
                MonthAndDate.from(10,9),
                MonthAndDate.from(12,25)
            )
        );
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public boolean isHoliday(int date) {
        int dayIndex = (date + days.indexOf(startDay)-1) % 7;
        if(holidays.contains(MonthAndDate.from(startMonth,date)) || dayIndex == 5 || dayIndex == 6)
            return true;
        return false;
    }

    public boolean isHolidayButWeekDay(int date) {
        if(holidays.contains(MonthAndDate.from(startMonth,date)))
            return true;
        return false;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public String getDay(int date) {
        int dayIndex = (date + days.indexOf(startDay)-1) % 7;
        return days.get(dayIndex);
    }
}
