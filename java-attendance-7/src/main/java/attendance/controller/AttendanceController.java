package attendance.controller;

import attendance.domain.AttendanceRepository;
import attendance.domain.MemberRepository;
import attendance.dto.AttendanceInfoDTO;
import attendance.dto.AttendanceInfosPerCrewDTO;
import attendance.service.AttendanceService;
import attendance.util.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AttendanceController {

    private final LocalDateTime localDateTime = DateTimes.now();
    //    private final LocalDateTime localDateTime = LocalDate.of(2024,12,14).atStartOfDay();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final MemberRepository memberRepository = new MemberRepository();
    private final Validator validator = new Validator(memberRepository);
    private final AttendanceRepository attendanceRepository = new AttendanceRepository();
    private final AttendanceService attendanceService = new AttendanceService(localDateTime, memberRepository, attendanceRepository);

    public void run() {
        try {
            mainmenu(localDateTime);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            throw e;
        }
    }

    private void mainmenu(LocalDateTime localDateTime) {
        while (true) {
            outputView.printMainMenu(localDateTime.toLocalDate());
            String input = inputView.inputRequestMainMenu();
            if (input.equals("1")) checkAttendance();
            else if (input.equals("2")) updateAttendance();
            else if (input.equals("3")) getAttendanceLogsPerCrew();
            else if (input.equals("4")) getExplusionRiskCrew();
            else if (input.equals("Q")) break;
        }
    }

    private void checkAttendance() {
        validator.isNotWorking(localDateTime);
        String name = requestNickname();
        validator.isExistMember(name);
        outputView.printRequestArriveTime();
        LocalTime localTime = inputView.requestArriveTime();
        AttendanceInfoDTO attendanceInfoDTO = attendanceService.checkAttendance(name, localTime);
        outputView.printAttendanceInfo(attendanceInfoDTO);
    }

    private int requestDate() {
        outputView.printRequestDate();
        return inputView.inputDate(localDateTime);
    }

    private String requestNickname() {
        outputView.printRequestNickname();
        return inputView.inputNickname();
    }

    private void updateAttendance() {
        String nickname = requestNickname();
        int date = requestDate();
        outputView.printRequestTimeToUpdate();
        LocalTime localTime = inputView.requestArriveTime();
        List<AttendanceInfoDTO> attendanceInfos = attendanceService.updateAttendance(nickname, date, localTime, localDateTime);
        outputView.printUpdateResult(attendanceInfos);
    }

    private void getAttendanceLogsPerCrew() {
        String name = requestNickname();
        AttendanceInfosPerCrewDTO attendanceInfosPerCrewDTO = attendanceService.getAttendanceLogsPerCrew(name, localDateTime);
        outputView.printLogs(name, attendanceInfosPerCrewDTO);
    }

    private void getExplusionRiskCrew() {

    }
}
