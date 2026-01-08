package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository {
    private final List<Attendance> attendances = new ArrayList<>();

    public void add(Attendance attendance) {
        attendances.add(attendance);
        attendance.getMember().updateAttendanceStatus(attendance.getAttendanceStatus());
    }

    public List<Attendance> getAttendancesByMemberNameAndMonth(String name, LocalDateTime dateTime) {
        return attendances.stream()
                .filter(attendance -> attendance.getMember().getName().equals(name))
                .filter(attendance -> attendance.getDatetime().getMonth().equals(dateTime.getMonth()))
                .toList();
    }

    public Attendance getAttendanceByMemberNameAndDate(String name, LocalDate localDate) {
        return attendances.stream()
                .filter(attendance -> attendance.getDatetime() != null)
                .filter(attendance -> attendance.getMember().getName().equals(name))
                .filter(attendance -> attendance.getDatetime().toLocalDate().equals(localDate))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("[ERROR] 아직 수정할 수 없습니다.")
                );
    }

    public boolean isAlreadyCheckAttendance(String name, LocalDate localDate) {
        return attendances.stream()
                .filter(attendance -> attendance.getMember().getName().equals(name))
                .anyMatch(attendance -> attendance.getDatetime().toLocalDate().equals(localDate) && !attendance.getDatetime().toLocalTime().equals(LocalTime.of(0, 0)));
    }
}
