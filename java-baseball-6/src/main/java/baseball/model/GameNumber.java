package baseball.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class GameNumber {
    private final List<Integer> numbers = new ArrayList<>();

    public void generate() {
        for(int i=0; i<3; i++) numbers.add(Randoms.pickNumberInRange(1,9));
    }

    public GameResult getResult(String answer) {
        int ball = 0;
        int strike = 0;
        for(int i=0; i<numbers.size(); i++) {
            if(answer.charAt(i) - '0' == numbers.get(i)) {
                strike++;
                continue;
            }
            if(numbers.contains(answer.charAt(i)-'0')) {
                ball++;
            }
        }
        return new GameResult(ball, strike);
    }
}
