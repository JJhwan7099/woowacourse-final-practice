package attendance.service;

import attendance.constants.Constants;
import attendance.constants.Error;
import attendance.domain.Attendance;
import attendance.domain.AttendanceRepository;
import attendance.domain.Member;
import attendance.domain.MemberRepository;
import attendance.dto.AttendanceInfoDTO;
import attendance.dto.AttendanceInfosPerCrewDTO;
import attendance.util.CSVReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AttendanceService {
    private final LocalDateTime localDateTime;
    private final MemberRepository memberRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(LocalDateTime localDateTime, MemberRepository memberRepository, AttendanceRepository attendanceRepository) {
        this.localDateTime = localDateTime;
        this.memberRepository = memberRepository;
        this.attendanceRepository = attendanceRepository;
        init();
    }

    public void init() {
        List<List<String>> lines = CSVReader.read();
        for (List<String> line : lines) {
            if (line.equals(lines.getFirst())) continue;
            String name = line.getFirst();
            LocalDateTime dateTime = LocalDateTime.parse(line.getLast(), Constants.formatter);
            if (!memberRepository.isAlreadyRegister(name)) {
                Member member = new Member(name);
                memberRepository.add(member);
                for (int i = 1; i <= localDateTime.getMonth().length(false); i++) {
                    if (Constants.coreTimesPerDay.get(LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), i).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN)) == null || Constants.holidays.contains(LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), i)))
                        continue;
                    attendanceRepository.add(new Attendance(member, LocalDateTime.of(LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), i), LocalTime.of(0, 0))));
//                    attendanceRepository.add(new Attendance(member,null));
                }
                attendanceRepository.getAttendanceByMemberNameAndDate(member.getName(), dateTime.toLocalDate()).update(new Attendance(member, dateTime));
            }
            Member member = memberRepository.getMemberByName(name);
            attendanceRepository.getAttendanceByMemberNameAndDate(member.getName(), dateTime.toLocalDate()).update(new Attendance(member, dateTime));
        }
    }


    public AttendanceInfoDTO checkAttendance(String name, LocalTime localTime) {
        Member member = memberRepository.getMemberByName(name);
        if (attendanceRepository.isAlreadyCheckAttendance(name, localDateTime.toLocalDate()))
            throw new IllegalArgumentException(Error.alreadyCheckAttendance);
        Attendance attendance = new Attendance(member, LocalDateTime.of(localDateTime.toLocalDate(), localTime));
        attendanceRepository.getAttendanceByMemberNameAndDate(member.getName(), localDateTime.toLocalDate()).update(attendance);
        return new AttendanceInfoDTO(attendance.getDatetime(), attendance.getAttendanceStatus().getStatus());
    }

    public List<AttendanceInfoDTO> updateAttendance(String nickname, int date, LocalTime localTime, LocalDateTime originLocalDateTime) {
        Member member = memberRepository.getMemberByName(nickname);
        Attendance attendance = attendanceRepository.getAttendanceByMemberNameAndDate(nickname, LocalDate.of(originLocalDateTime.getYear(), originLocalDateTime.getMonthValue(), date));
        AttendanceInfoDTO originalInfo = new AttendanceInfoDTO(attendance.getDatetime(), attendance.getAttendanceStatus().getStatus());
        Attendance newAttendance = new Attendance(member, LocalDateTime.of(LocalDate.of(originLocalDateTime.getYear(), originLocalDateTime.getMonthValue(), date), localTime));
        attendance.update(newAttendance);
        AttendanceInfoDTO newInfo = new AttendanceInfoDTO(attendance.getDatetime(), attendance.getAttendanceStatus().getStatus());
        return Arrays.asList(originalInfo, newInfo);
    }

    public AttendanceInfosPerCrewDTO getAttendanceLogsPerCrew(String name, LocalDateTime localDateTime) {
        return AttendanceInfosPerCrewDTO.from(attendanceRepository.getAttendancesByMemberNameAndMonth(name, localDateTime)
                .stream()
                .filter(attendance -> attendance.getDatetime().getDayOfMonth() < localDateTime.getDayOfMonth())
                .map(attendance -> new AttendanceInfoDTO(attendance.getDatetime(), attendance.getAttendanceStatus().getStatus()))
                .toList());
    }
}
