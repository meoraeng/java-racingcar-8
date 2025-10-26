package racingcar.model;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest extends NsTest {
    static class TestPlayResultPrinter implements PlayResultPrinter {
        final List<String> printedResults = new ArrayList<>();

        @Override
        public void print(String name, int point) {
            String dash = "-".repeat(point);
            printedResults.add(name + ":" + dash);
        }
    }

    @Test
    @DisplayName("자동차는 생성 시 point는 0이며, 이 상태에서는 -가 출력되지 않는다")
    void initialPointIsZero() {
        Car car = new Car("pobi");
        TestPlayResultPrinter printer = new TestPlayResultPrinter();

        car.printTo(printer);

        assertThat(printer.printedResults).containsExactly("pobi : ");
    }

    @Test
    @DisplayName("moveForward 호출 시 point가 증가하며 -가 누적된 point 만큼 출력")
    void moveForward_multipleTimes() {
        Car car = new Car("pobi");
        TestPlayResultPrinter printer = new TestPlayResultPrinter();

        car.moveForward();
        car.moveForward();
        car.moveForward();
        car.printTo(printer);

        assertThat(printer.printedResults).containsExactly("pobi : ---");
    }


    @Override
    protected void runMain() {
    }
}
