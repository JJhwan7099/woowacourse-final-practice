package attendance.util;

import attendance.constants.Constants;
import attendance.domain.MemberRepository;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Validator {

    private final MemberRepository memberRepository;

    public Validator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void isNotWorking(LocalDateTime localDateTime) {
        if (Constants.holidays.contains(localDateTime.toLocalDate()) || Constants.coreTimesPerDay.get(localDateTime.toLocalDate().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN)) == null)
            throw new IllegalArgumentException(String.format("[ERROR] %02d월 %02d일 %s요일은 등교일이 아닙니다.", localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), localDateTime.toLocalDate().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN)));
    }

    public void isExistMember(String name) {
        memberRepository.getMemberByName(name);
    }
}
