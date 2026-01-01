package racingcar.repository;

import racingcar.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<String> findWinners() {
        int maxPosition = findMaxPostion();
        List<String> winners = new ArrayList<>();
        for(Car car : cars) {
            if(car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    private int findMaxPostion() {
        int position = 0;
        for(Car car: cars) {
            if(car.getPosition() > position)
                position = car.getPosition();
        }
        return position;
    }
}
