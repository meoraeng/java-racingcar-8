package racingcar.model.factory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Cars;
import racingcar.model.validator.UniqueNamesValidator;
import racingcar.model.validator.Validator;

public class CarsFactoryTest {
    @Test
    @DisplayName("이름 배열을 받아 Cars를 생성")
    void createCarsSuccess() {
        Validator<String[]> validator = new UniqueNamesValidator();
        CarsFactory factory = new CarsFactory(validator);

        Cars cars = factory.createFrom(new String[]{"pobi", "woni"});

        assertThat(cars).isNotNull();
    }

    @Test
    @DisplayName("중복된 이름이 있으면 예외 발생")
    void createCarsFailureForDuplicateNamesCar() {
        Validator<String[]> validator = new UniqueNamesValidator();
        CarsFactory factory = new CarsFactory(validator);

        assertThatThrownBy(() -> factory.createFrom(new String[]{"pobi", "pobi"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}