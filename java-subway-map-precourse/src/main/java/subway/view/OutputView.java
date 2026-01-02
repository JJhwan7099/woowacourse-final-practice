package subway.view;

import subway.domain.Line;
import subway.domain.Station;
import subway.dto.LineMapDTO;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printMainView() {
        System.out.println();
        System.out.println("## 메인 화면");
        System.out.println("1. 역 관리");
        System.out.println("2. 노선 관리");
        System.out.println("3. 구간 관리");
        System.out.println("4. 지하철 노선도 출력");
        System.out.println("Q. 종료");
    }

    public void printErrorMessage(Exception e) {
        System.out.println();
        System.out.println(e.getMessage());
    }

    public void printStationMenu() {
        System.out.println();
        System.out.println("## 역 관리 화면\n" +
                "1. 역 등록\n" +
                "2. 역 삭제\n" +
                "3. 역 조회\n" +
                "B. 돌아가기");
    }

    public void printLineMenu() {
        System.out.println();
        System.out.println("## 노선 관리 화면\n" +
                "1. 노선 등록\n" +
                "2. 노선 삭제\n" +
                "3. 노선 조회\n" +
                "B. 돌아가기");
    }

    public void printLineStationMenu() {
        System.out.println();
        System.out.println("## 구간 관리 화면\n" +
                "1. 구간 등록\n" +
                "2. 구간 삭제\n" +
                "B. 돌아가기");
    }

    public void printLineMap(List<LineMapDTO> lineMap) {
        System.out.println();
        System.out.println("## 지하철 노선도");
        for(LineMapDTO lineMapDTO: lineMap) {
            System.out.println("[INFO] " + lineMapDTO.getLineName());
            System.out.println("[INFO] ---");
            for(Station station : lineMapDTO.getStations()) {
                System.out.println("[INFO] " + station.getName());
            }
            System.out.println();
        }
    }

    public void printRegisterStation() {
        System.out.println();
        System.out.println("## 등록할 역 이름을 입력하세요.");
    }

    public void printChooseFunctionMessage() {
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요.");
    }

    public void printStationList(List<Station> stations) {
        System.out.println();
        System.out.println("## 역 목록");
        for(Station station: stations) {
            String stationName = station.getName();
            System.out.println("[INFO] " + stationName);
        }
    }

    public void printRegisterLine() {
        System.out.println();
        System.out.println("## 등록할 노선 이름을 입력하세요.");
    }

    public void printStartStationOfRegisterLine() {
        System.out.println();
        System.out.println("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
    }

    public void printEndStationOfRegisterLine() {
        System.out.println();
        System.out.println("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
    }

    public void printDeleteLine() {
        System.out.println();
        System.out.println("## 삭제할 노선 이름을 입력하세요.");
    }

    public void printDeleteStationSuccessMessage() {
        System.out.println();
        System.out.println("[INFO] 지하철 역이 삭제되었습니다.");
    }

    public void deleteLineSuccessMessage() {
        System.out.println();
        System.out.println("[INFO] 지하철 노선이 삭제되었습니다.");
    }

    public void printLineList(List<Line> lines) {
        System.out.println();
        System.out.println("## 노선 목록");
        for(Line line: lines) {
            System.out.println("[INFO] " + line.getName());
        }
    }

    public void inputLineOfRegisterLineStation() {
        System.out.println();
        System.out.println("## 노선을 입력하세요.");
    }

    public void inputStationOfRegisterLineStation() {
        System.out.println();
        System.out.println("## 역이름을 입력하세요.");
    }

    public void inputSequenceOfRegisterLineStation() {
        System.out.println();
        System.out.println("## 순서를 입력하세요.");
    }

    public void inputLineOfDeleteLineStation() {
        System.out.println();
        System.out.println("## 삭제할 구간의 노선을 입력하세요.");
    }

    public void inputStationOfDeleteLineStation() {
        System.out.println();
        System.out.println("## 삭제할 구간의 역을 입력하세요.");
    }

    public void printDeleteLineStationSuccessMessage() {
        System.out.println();
        System.out.println("[INFO] 구간이 삭제되었습니다.");
    }

    public void printDeleteStation() {
        System.out.println();
        System.out.println("## 삭제할 역 이름을 입력하세요.");
    }

    public void printRegisterStationSuccessMessage() {
        System.out.println();
        System.out.println("[INFO] 지하철 역이 등록되었습니다.");
    }
}
