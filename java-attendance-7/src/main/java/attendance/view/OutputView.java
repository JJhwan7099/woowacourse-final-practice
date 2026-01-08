package attendance.view;

import attendance.dto.AttendanceInfoDTO;
import attendance.dto.AttendanceInfosPerCrewDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class OutputView {
    public void printMainMenu(LocalDate localDate) {
        System.out.println();
        System.out.printf("오늘은 %02d월 %02d일 %s요일입니다. 기능을 선택해 주세요.%n", localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN));
        System.out.println("1. 출석 확인\n" +
                "2. 출석 수정\n" +
                "3. 크루별 출석 기록 확인\n" +
                "4. 제적 위험자 확인\n" +
                "Q. 종료");
    }

    public void printRequestNickname() {
        System.out.println();
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
    }

    public void printRequestDate() {
        System.out.println();
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
    }

    public void printAttendanceInfo(AttendanceInfoDTO attendanceInfoDTO) {
        LocalDateTime localDateTime = attendanceInfoDTO.localDateTime();
        String status = attendanceInfoDTO.status();
        System.out.println();
        System.out.printf("%02d월 %02d일 %s요일 %02d:%02d (%s)%n", localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN), localDateTime.getHour(), localDateTime.getMinute(), status);
    }

    public void printRequestArriveTime() {
        System.out.println();
        System.out.println("등교 시간을 입력해 주세요.");
    }

    public void printRequestTimeToUpdate() {
        System.out.println();
        System.out.println("언제로 변경하겠습니까?");
    }

    public void printUpdateResult(List<AttendanceInfoDTO> attendanceInfos) {
        LocalDateTime originDateTime = attendanceInfos.getFirst().localDateTime();
        String originStatus = attendanceInfos.getFirst().status();
        LocalDateTime newDateTime = attendanceInfos.getLast().localDateTime();
        String newStatus = attendanceInfos.getLast().status();
        System.out.println();
        System.out.printf("%02d월 %02d일 %s요일 %02d:%02d (%s) -> %02d:%02d (%s) 수정 완료!", originDateTime.getMonthValue(), originDateTime.getDayOfMonth(), originDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN), originDateTime.getHour(), originDateTime.getMinute(), originStatus, newDateTime.getHour(), newDateTime.getMinute(), newStatus);
    }

    public void printLogs(String name, AttendanceInfosPerCrewDTO attendanceInfosPerCrewDTO) {
        System.out.println();
        System.out.println("이번 달 " + name + "의 출석 기록입니다.");
        System.out.println();
        for (AttendanceInfoDTO attendanceInfoDTO : attendanceInfosPerCrewDTO.attendanceInfos()) {
            if (!attendanceInfoDTO.localDateTime().equals(LocalDateTime.of(attendanceInfoDTO.localDateTime().toLocalDate(), LocalTime.of(0, 0)))) {
                System.out.printf("%02d월 %02d일 %s요일 %02d:%02d (%s)%n", attendanceInfoDTO.localDateTime().getMonthValue(), attendanceInfoDTO.localDateTime().getDayOfMonth(), attendanceInfoDTO.localDateTime().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN), attendanceInfoDTO.localDateTime().getHour(), attendanceInfoDTO.localDateTime().getMinute(), attendanceInfoDTO.status());
                continue;
            }
            System.out.printf("%02d월 %02d일 %s요일 --:-- (%s)%n", attendanceInfoDTO.localDateTime().getMonthValue(), attendanceInfoDTO.localDateTime().getDayOfMonth(), attendanceInfoDTO.localDateTime().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN), attendanceInfoDTO.status());
        }
        System.out.println("\n출석: " + attendanceInfosPerCrewDTO.normalCount() + "회\n" +
                "지각: " + attendanceInfosPerCrewDTO.lateCount() + "회\n" +
                "결석: " + attendanceInfosPerCrewDTO.absenceCount() + "회");

        if (!attendanceInfosPerCrewDTO.memberStatus().equals("정상")) {
            System.out.println("\n" + attendanceInfosPerCrewDTO.memberStatus() + " 대상자입니다.");
        }
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println();
        System.out.println(e.getMessage());
    }
}
