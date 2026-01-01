package racingcar.view;

import racingcar.dto.RoundResult;
import racingcar.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void roundResultOutput(List<RoundResult> roundResults) {
        System.out.println("실행 결과");
        for(RoundResult roundResult : roundResults) {
            for(int i=0; i<roundResult.carNameAndPostions().size(); i++) {
                String carName = roundResult.carNameAndPostions().get(i).carName();
                int postion = roundResult.carNameAndPostions().get(i).position();
                System.out.println(carName + " : " + "-".repeat(postion));
            }
            System.out.println();
        }
    }

    public void winnersOutput(List<String> winners) {
        String result = winners.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("최종 우승자 : " + result);
    }

    public void errorOutput(Exception e) {
        System.out.println(e.getMessage());
    }
}
