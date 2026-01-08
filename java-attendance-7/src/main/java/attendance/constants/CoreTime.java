package attendance.constants;

import java.time.LocalTime;

public record CoreTime(
        LocalTime start,
        LocalTime end
) {
}
