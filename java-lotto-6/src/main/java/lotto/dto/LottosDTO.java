package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;

public record LottosDTO(
        List<LottoInfo> lottoInfos
) {
    public static LottosDTO from(List<Lotto> lottos) {
        return new LottosDTO(
                lottos.stream()
                        .map(lotto -> new LottoInfo(lotto.getNumbers()))
                        .toList()
        );
    }

    public record LottoInfo(
            List<Integer> lottoNumbers
    ){}
}
