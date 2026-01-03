package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateSameNumber(numbers);
        this.numbers = numbers;
    }

    private void validateSameNumber(List<Integer> numbers) {
        for(Integer number : numbers) {
            int sameNumberCount = Collections.frequency(numbers, number);
            if(sameNumberCount > 1) throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
