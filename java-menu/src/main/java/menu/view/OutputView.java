package menu.view;

import menu.domain.Category;
import menu.domain.Day;
import menu.dto.MenuRecommendsInfosDTO;

import java.util.List;

public class OutputView {
    public void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printRequestCoachesName() {
        System.out.println();
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void printRequestIgnoreFoodsPerCoach(String name) {
        System.out.println();
        System.out.println(name + "(이)가 못 먹는 메뉴를 입력해 주세요.");
    }

    public void printResult(MenuRecommendsInfosDTO menuRecommendsInfosDTO) {
        System.out.println("\n메뉴 추천 결과입니다.");
        System.out.print("[ 구분 ");
        for(Day day: Day.values()) System.out.print("| " + day.getValue()+"요일 ");
        System.out.println("]");
        System.out.print("[ 카테고리 ");
        for(String category : menuRecommendsInfosDTO.categorySequnce()) System.out.print("| " + category + " ");
        System.out.println("]");
        for(String coachName : menuRecommendsInfosDTO.menuRecommendsPerCoach().keySet()) {
            List<String> foods = menuRecommendsInfosDTO.menuRecommendsPerCoach().get(coachName);
            System.out.print("[ " + coachName+ " ");
            for(String foodName: foods) System.out.print("| "+foodName+" ");
            System.out.println("]");
        }
        System.out.println("\n추천을 완료했습니다.");
    }

    public void printErrorMessage(Exception e) {
        System.out.println();
        System.out.println(e.getMessage());
    }
}
