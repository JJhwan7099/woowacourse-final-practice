package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.dto.RoundResult;
import racingcar.model.Car;
import racingcar.repository.CarRepository;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class RacingCarService {
    private final CarRepository carRepository;

    public RacingCarService(
            CarRepository carRepository
    ) {
        this.carRepository = carRepository;
    }

    public void saveCars(String[] carNames) {
        for(String carName : carNames) {
            Car car = new Car(carName);
            carRepository.addCar(car);
        }
    }

    public List<RoundResult> race(int round) {
        List<Car> cars = carRepository.getCars();
        List<RoundResult> roundResults = new ArrayList<>();
        for(int i=0 ; i<round; i++) {
            for (Car car : cars) {
                pickRandomNumberAndMoveCar(car);
            }
            roundResults.add(RoundResult.from(cars));
        }
        return roundResults;
    }

    private static void pickRandomNumberAndMoveCar(Car car) {
        if (Randoms.pickNumberInRange(0, 9) >= 4)
            car.move();
    }

    public List<String> getWinners() {
        List<String> winners = carRepository.findWinners();
        return winners;
    }
}
