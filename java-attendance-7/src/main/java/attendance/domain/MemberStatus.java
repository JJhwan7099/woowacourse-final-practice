package attendance.domain;

import java.util.EnumMap;

public enum MemberStatus {
    NORMAL("정상"),
    WARN("경고"),
    MEETING("면담"),
    EXPULSION("제적");

    private final String status;

    MemberStatus(String status) {
        this.status = status;
    }

    public static MemberStatus of(EnumMap<AttendanceStatus, Integer> attendanceCount) {
        int lateCount = attendanceCount.get(AttendanceStatus.LATE);
        int absenceCount = attendanceCount.get(AttendanceStatus.ABSENCE);
        absenceCount += lateCount / 3;
        if (absenceCount > 5) return EXPULSION;
        else if (absenceCount >= 3) return MEETING;
        else if (absenceCount >= 2) return WARN;
        return NORMAL;
    }

    public static MemberStatus of(int normalCount, int lateCount, int absenceCount) {
        absenceCount += lateCount / 3;
        if (absenceCount > 5) return EXPULSION;
        else if (absenceCount >= 3) return MEETING;
        else if (absenceCount >= 2) return WARN;
        return NORMAL;
    }

    public String getStatus() {
        return status;
    }
}
