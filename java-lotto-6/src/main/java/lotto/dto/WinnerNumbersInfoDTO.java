package lotto.dto;

import java.util.List;

public record WinnerNumbersInfoDTO(
        List<Integer> winningNumbers
) {
}
