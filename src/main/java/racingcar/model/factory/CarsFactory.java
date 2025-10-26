package racingcar.model.factory;


import java.util.ArrayList;
import java.util.List;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.validator.Validator;

public class CarsFactory {
    private final Validator<String[]> nameValidator;

    public CarsFactory(Validator<String[]> nameValidator) {
        this.nameValidator = nameValidator;
    }

    public Cars createFrom(String[] nameTokens) {
        nameValidator.validate(nameTokens);

        List<Car> cars = new ArrayList<>();
        for (String name : nameTokens) {
            cars.add(new Car(name.trim()));
        }

        return new Cars(cars);
    }
}
