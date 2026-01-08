package attendance.constants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Constants {
    public static final Map<String, CoreTime> coreTimesPerDay = Map.of(
            "월", new CoreTime(LocalTime.of(13, 0), LocalTime.of(18, 0)),
            "화", new CoreTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
            "수", new CoreTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
            "목", new CoreTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
            "금", new CoreTime(LocalTime.of(10, 0), LocalTime.of(18, 0))
    );

    public static final Set<LocalDate> holidays = Set.of(LocalDate.of(2024, 12, 25));

    public static final List<String> mainMenuSelections = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "Q"));
    //    public static final String timeRegex = "\\d{2}:d{2}";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}