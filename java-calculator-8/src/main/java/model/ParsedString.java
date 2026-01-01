package model;

import java.util.ArrayList;
import java.util.List;

public class ParsedString {
    List<String> delimeters = new ArrayList<>();

    public List<Integer> getNumbers() {
        return numbers;
    }

    List<Integer> numbers = new ArrayList<>();

    public ParsedString() {
        delimeters.add(",");
    }

    public void addDelimeter(String delimeter) {
        delimeters.add(delimeter);
    }

    public void addNumber(int number) {
        numbers.add(number);
    }

    public boolean isDelimeter(char c) {
        if(delimeters.contains(c + ""))
            return true;
        return false;
    }
}
