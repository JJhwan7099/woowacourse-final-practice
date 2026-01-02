package subway.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    private final List<String> mainInputFields = new ArrayList<>(Arrays.asList("1","2","3","4","Q"));
    private final List<String> stationInputFields = new ArrayList<>(Arrays.asList("1", "2", "3", "B"));
    private final List<String> lineInputFields = new ArrayList<>(Arrays.asList("1", "2", "3", "B"));
    private final List<String> lineStationInputFields = new ArrayList<>(Arrays.asList("1", "2", "B"));

    public InputView(Scanner sc) {
        this.sc = sc;
    }

    public String inputMain() {
        String input = sc.nextLine();
        if(!mainInputFields.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다.");
        }
        return input;
    }

    public String inputStationMenu() {
        String input = sc.nextLine();
        if(!stationInputFields.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다.");
        }
        return input;
    }

    public String inputLineMenu() {
        String input = sc.nextLine();
        if(!lineInputFields.contains(input)){
            throw new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다.");
        }
        return input;
    }

    public String inputLineStationMenu() {
        String input = sc.nextLine();
        if(!lineStationInputFields.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다.");
        }
        return input;
    }

    public String inputStationName() {
        String input = sc.nextLine();
        if(input.length() < 2) throw new IllegalArgumentException("[ERROR] 지하철역의 이름은 2글자 이상이어야 합니다.");
        return input;
    }

    public String inputLineName() {
        String input = sc.nextLine();
        if(input.length() < 2) throw new IllegalArgumentException("[ERROR] 지하철 노선의 이름은 2글자 이상이어야 합니다.");
        return input;
    }

    public int inputStationSequence() {
        String input = sc.nextLine();
        try{
            int idx = Integer.parseInt(input);
            if(idx < 1) throw new IllegalArgumentException("[ERROR] 1이상의 숫자를 입력해주세요.");
            return idx;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }
}
