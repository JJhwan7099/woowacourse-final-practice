package attendance.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Attendance {
    private final Member member;
    private LocalDateTime datetime;
    private AttendanceStatus attendanceStatus;

    public Attendance(Member member, LocalDateTime datetime) {
        this.member = member;
        this.datetime = datetime;
        if (datetime.equals(LocalDateTime.of(datetime.toLocalDate(), LocalTime.of(0, 0)))) {
            this.attendanceStatus = AttendanceStatus.ABSENCE;
            return;
        }
        this.attendanceStatus = AttendanceStatus.of(datetime);
    }

    public Member getMember() {
        return member;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public AttendanceStatus getAttendanceStatus() {
        if (attendanceStatus == null) return AttendanceStatus.ABSENCE;
        return attendanceStatus;
    }

    public void update(Attendance attendance) {
        member.minusStatusCount(this.attendanceStatus);
        this.datetime = attendance.getDatetime();
        this.attendanceStatus = AttendanceStatus.of(datetime);
        member.updateAttendanceStatus(this.attendanceStatus);
    }
}
