package racingcar.model;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.port.PlayResultPrinter;

public class CarsTest {
    static class TestPlayResultPrinter implements PlayResultPrinter {
        final List<String> printed = new ArrayList<>();

        @Override
        public void print(String name, int point) {
            printed.add(name + " : " + "-".repeat(point));
        }
    }

    static class AlwaysAllowGate implements ExcutionGate {
        @Override
        public boolean allow() {
            return true;
        }
    }

    static class AlwaysBlockGate implements ExcutionGate {
        @Override
        public boolean allow() {
            return false;
        }
    }

    @Test
    @DisplayName("게이트가 허용되면 모든 자동차가 전진한다")
    void moveForwardWhenAllowedTest() {
        Cars cars = new Cars(List.of(new Car("pobi"), new Car("woni")));

        cars.moveForwardAllowedCars(new AlwaysAllowGate());
        TestPlayResultPrinter printer = new TestPlayResultPrinter();
        cars.printAllTo(printer);

        assertThat(printer.printed).containsExactly("pobi : -", "woni : -");
    }

    @Test
    @DisplayName("게이트 allow가 true가 아닌 경우 자동차가 전진하지 않는다")
    void moveForwardWhenBlockedTest() {
        Cars cars = new Cars(List.of(new Car("pobi"), new Car("woni")));

        cars.moveForwardAllowedCars(new AlwaysBlockGate());
        TestPlayResultPrinter printer = new TestPlayResultPrinter();
        cars.printAllTo(printer);

        assertThat(printer.printed).containsExactly("pobi : ", "woni : ");
    }

    @Test
    @DisplayName("가장 멀리 간 자동차들을 우승자로 찾는다")
    void findWinnersTest() {
        Car pobi = new Car("pobi");
        Car woni = new Car("woni");
        Car lavin = new Car("lavin");

        pobi.moveForward();
        pobi.moveForward();
        woni.moveForward();
        woni.moveForward();

        Cars cars = new Cars(List.of(pobi, woni, lavin));

        List<Car> winners = cars.findWinners();
        assertThat(winners).extracting("name").containsExactlyInAnyOrder("pobi", "woni");
    }

    @Test
    @DisplayName("Cars는 모든 자동차의 출력을 PlayResultPrinter로 위임한다")
    void printAllToTest() {
        Cars cars = new Cars(List.of(new Car("pobi"), new Car("woni")));
        TestPlayResultPrinter printer = new TestPlayResultPrinter();

        cars.printAllTo(printer);

        assertThat(printer.printed).containsExactly("pobi : ", "woni : ");
    }
}
