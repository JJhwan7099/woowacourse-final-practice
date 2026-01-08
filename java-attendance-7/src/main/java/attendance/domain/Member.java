package attendance.domain;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Member {
    private final String name;
    private final EnumMap<AttendanceStatus, Integer> statusCount = new EnumMap<>(AttendanceStatus.class);
    private final SortedMap<LocalDateTime, AttendanceStatus> attendanceLogs = new TreeMap<>();

    public Member(String name) {
        this.name = name;
        for (AttendanceStatus attendanceStatus : AttendanceStatus.values()) {
            statusCount.put(attendanceStatus, 0);
        }
    }

    public String getName() {
        return name;
    }

    public MemberStatus getMemberStatus() {
        return MemberStatus.of(statusCount);
    }

    public void updateAttendanceStatus(AttendanceStatus attendanceStatus) {
        statusCount.put(attendanceStatus, statusCount.get(attendanceStatus) + 1);
    }

    public void minusStatusCount(AttendanceStatus attendanceStatus) {
        statusCount.put(attendanceStatus, statusCount.get(attendanceStatus) - 1);
    }
}
