package attendance.domain;

import attendance.constants.Constants;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public enum AttendanceStatus {
    NORMAL("출석"),
    LATE("지각"),
    ABSENCE("결석");

    private final String status;

    AttendanceStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public static AttendanceStatus of(LocalDateTime dateTime) {
        String day = dateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        int time = dateTime.getHour() * 60 + dateTime.getMinute();
        if (!Constants.coreTimesPerDay.containsKey(day) || Constants.holidays.contains(dateTime.toLocalDate()))
            throw new IllegalArgumentException(String.format("[ERROR] %02d월 %02d일 %s요일은 등교일이 아닙니다.", dateTime.getMonthValue(), dateTime.getDayOfMonth(), day));
        int coreTime = Constants.coreTimesPerDay.get(day).start().getHour() * 60 + Constants.coreTimesPerDay.get(day).start().getMinute();
        if (time - coreTime > 30) return ABSENCE;
        else if (time - coreTime > 5) return LATE;
        return NORMAL;
    }
}
