package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    Map<Rank, Integer> results = new EnumMap<>(Rank.class);

    public LottoResult() {
        for(Rank rank : Rank.getKeys()) {
            results.put(rank, 0);
        }
    }

    public void increaseRankCount(Rank rank) {
        results.putIfAbsent(rank, 0);
        results.compute(rank, (k, value) -> value + 1);
    }

    public Map<Rank, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
