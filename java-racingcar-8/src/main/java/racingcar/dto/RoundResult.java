package racingcar.dto;

import racingcar.model.Car;

import java.util.ArrayList;
import java.util.List;

public record RoundResult(
        List<CarNameAndPosition> carNameAndPostions
) {
    public static RoundResult from(List<Car> cars) {
        List<CarNameAndPosition> carPostions = new ArrayList<>();
        for(Car car : cars) {
            carPostions.add(new CarNameAndPosition(car.getName(), car.getPosition()));
        }
        return new RoundResult(carPostions);
    }
    public record CarNameAndPosition(
            String carName,
            int position
    ) { }

}
