package racingcar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.model.port.PlayResultPrinter;

public class Cars {
    public List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<Car>(cars);
    }

    private void moveForwardAllowedCar(ExcutionGate gate, Car car) {
        if (gate.allow()) {
            car.moveForward();
        }
    }

    public void moveForwardAllowedCars(ExcutionGate gate) {
        for (Car car : cars) {
            moveForwardAllowedCar(gate, car);
        }
    }

    public void printAllTo(PlayResultPrinter printer) {
        for (Car car : cars) {
            car.printTo(printer);
        }
    }

    public List<Car> findWinners() {
        if (cars.isEmpty()) {
            return List.of();
        }

        int maxPoint = cars.stream()
                .mapToInt(Car::point)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(c -> c.hasPoint(maxPoint))
                .toList();
    }
}