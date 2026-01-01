package util;

import model.ParsedString;

import static java.lang.Character.isDigit;

public class InputParser {

    private final ParsedString parsedString = new ParsedString();

    public ParsedString getParsedString() {
        return parsedString;
    }

    public boolean hasCustomDelimeters(String input) {
        if(input.contains("//") && input.contains("\\n"))
            return true;
        return false;
    }

    public void extractCustomDelimetersAndNumbers(String input) {
        if(hasCustomDelimeters(input)) {
            extractCustomDelimeters(input);
            input = input.split("\\\\n",-1)[1];
        }
        extractNumbers(input);
    }

    public void extractCustomDelimeters(String input) {
        String[] delimeters = input.split("//",-1)[1].split("\\\\n",-1)[0].split("");
        for(String delimeter : delimeters) {
            parsedString.addDelimeter(delimeter);
        }
    }

    public void extractNumbers(String input) {
        String num = "";
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if(isDigit(c) || c == '-') num += c;
            else {
                if(parsedString.isDelimeter(c)) {
                    parsedString.addNumber(Integer.parseInt(num));
                    num = "";
                }
                else throw new IllegalArgumentException("[ERROR] 계산부에는 구분자와, 숫자만 있어야 합니다.");
            }
        }
        if(!num.isBlank()) {
            parsedString.addNumber(Integer.parseInt(num));
        }
    }
}
