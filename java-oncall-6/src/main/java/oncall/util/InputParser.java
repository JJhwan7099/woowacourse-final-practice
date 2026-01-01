package oncall.util;

import oncall.model.Constants;
import oncall.model.StartMonthAndDay;
import oncall.model.WorkSequence;

public class InputParser {
    public static StartMonthAndDay parserMonthAndDayInput(String input) {
        String[] parts = input.split(",",-1);
        for(int i=0; i<parts.length; i++) {
            parts[i] = parts[i].trim();
            if(parts[i]==null || parts[i].isBlank())
                throw new IllegalArgumentException("[ERROR] 비상근무 배정월과 시작 요일을 정확히 입력해주세요.");
        }
        if(parts.length != 2 || !Constants.months.contains(Integer.parseInt(parts[0])) || !Constants.days.contains(parts[1]))
            throw new IllegalArgumentException("[ERROR] 비상근무 배정월과 시작 요일을 정확히 입력해주세요.");
        return new StartMonthAndDay(Integer.parseInt(parts[0]), parts[1]);
    }

    public static String[] parseWorkSequence(String input) {
        String[] parts = input.split(",",-1);
        for(int i=0; i<parts.length; i++) {
            parts[i] = parts[i].trim();
            if(parts[i].length() > 5) throw new IllegalArgumentException("[ERROR] 근무자의 이름은 5자 이하입니다.");
        }
        if(parts.length < 5 || parts.length > 35) throw new IllegalArgumentException("[ERROR] 근무자 수는 최소 5명 최대 35명입니다.");
        return parts;
    }
}
