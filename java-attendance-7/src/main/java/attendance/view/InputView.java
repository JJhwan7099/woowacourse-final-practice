package attendance.view;

import attendance.constants.Constants;
import attendance.constants.Error;
import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class InputView {

    public String inputRequestMainMenu() {
        String input = Console.readLine();
        if (!Constants.mainMenuSelections.contains(input))
            throw new IllegalArgumentException(Error.inputFormatError);
        return input;
    }

    public String inputNickname() {
        return Console.readLine();
    }

    public int inputDate(LocalDateTime localDateTime) {
        String input = Console.readLine();
        try {
            int date = Integer.parseInt(input);
            if (date < 1 || date > Month.of(localDateTime.getMonthValue()).length(false))
                throw new IllegalArgumentException(Error.inputFormatError);
            if (Constants.holidays.contains(LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), date)) || null == Constants.coreTimesPerDay.get(LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), date).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN)))
                throw new IllegalArgumentException(String.format("[ERROR] %02d월 %02d일 %s요일은 등교일이 아닙니다.", localDateTime.getMonthValue(), date, LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), date).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN)));
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.inputFormatError);
        }
    }

    public LocalTime requestArriveTime() {
        String input = Console.readLine();
        String[] parts = input.split(":", -1);
        if (parts.length != 2 || Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[0]) > 23 || Integer.parseInt(parts[1]) < 0 || Integer.parseInt(parts[1]) > 59)
            throw new IllegalArgumentException(Error.inputFormatError);
        return LocalTime.of(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
    }
}
