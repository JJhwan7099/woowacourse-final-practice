package attendance.dto;

import java.time.LocalDateTime;

public record AttendanceInfoDTO(
        LocalDateTime localDateTime,
        String status
) {
}
