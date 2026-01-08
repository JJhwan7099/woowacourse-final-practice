package lotto.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Application;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoControllerTest extends NsTest {
    @Test
    void 통합_기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("4000", "3,11,15,29,35,42", "7"); // 당첨 번호
                    assertThat(output()).contains(
                            "4개를 구매했습니다.",
                            "[3, 11, 15, 28, 36, 45]",
                            "[1, 6, 12, 24, 33, 41]",
                            "[2, 13, 20, 30, 38, 44]",
                            "[5, 9, 19, 23, 32, 37]",

                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",

                            "총 수익률은 125.0%입니다."
                    );
                },
                List.of(3, 11, 15, 28, 36, 45),
                List.of(1, 6, 12, 24, 33, 41),
                List.of(2, 13, 20, 30, 38, 44),
                List.of(5, 9, 19, 23, 32, 37)
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
