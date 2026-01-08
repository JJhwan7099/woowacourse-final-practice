package attendance.dto;

import attendance.domain.MemberStatus;

import java.util.Comparator;
import java.util.List;

public record AttendanceInfosPerCrewDTO(
        List<AttendanceInfoDTO> attendanceInfos,
        int normalCount,
        int lateCount,
        int absenceCount,
        String memberStatus
) {
    public static AttendanceInfosPerCrewDTO from(List<AttendanceInfoDTO> attendanceInfos) {
        int normalCount = 0;
        int lateCount = 0;
        int absenceCount = 0;
        for (AttendanceInfoDTO attendanceInfoDTO : attendanceInfos) {
            if (attendanceInfoDTO.status().equals("출석")) normalCount++;
            else if (attendanceInfoDTO.status().equals("지각")) lateCount++;
            else if (attendanceInfoDTO.status().equals("결석")) absenceCount++;
        }
        return new AttendanceInfosPerCrewDTO(
                attendanceInfos.stream().sorted(Comparator.comparing(attendanceInfoDTO -> attendanceInfoDTO.localDateTime().getDayOfMonth())).toList(),
                normalCount,
                lateCount,
                absenceCount,
                MemberStatus.of(normalCount, lateCount, absenceCount).getStatus()
        );
    }
}
