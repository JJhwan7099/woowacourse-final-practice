package racingcar.util;

public class InputParser {
    public static String[] parseInput(String input) {
        String[] tokens = input.split(",",-1);
        for(int i=0; i< tokens.length; i++) {
            tokens[i] = tokens[i].trim();
            if(tokens[i].length() > 5 || tokens[i].isBlank()) {
                throw new IllegalArgumentException("[ERROR] 자동차의 이름은 5자이하 입니다.");
            }
        }
        return tokens;
    }
}
