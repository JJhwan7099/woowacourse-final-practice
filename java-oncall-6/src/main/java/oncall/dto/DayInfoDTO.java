package oncall.dto;

public record DayInfoDTO(
        int month,
        int date,
        String day,
        boolean isHoliday,
        String workerName
) {
}
