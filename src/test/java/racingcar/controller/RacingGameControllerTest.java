package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.ExcutionGate;
import racingcar.model.factory.CarsFactory;
import racingcar.model.port.PlayResultPrinter;
import racingcar.model.validator.UniqueNamesValidator;
import racingcar.model.validator.Validator;
import racingcar.view.WinnersPrinter;

public class RacingGameControllerTest {
    static class TestPlayResultPrinter implements PlayResultPrinter {
        final List<String> lines = new ArrayList<>();

        @Override
        public void print(String name, int point) {
            lines.add(name + " : " + "-".repeat(point));
        }
    }

    static class TestWinnersPrinter extends WinnersPrinter {
        String last;

        @Override
        public void printWinners(List<String> winners) {
            last = String.join(", ", winners);
        }
    }

    static class TempGate implements ExcutionGate {
        private final boolean[] pattern;
        private int index = 0;

        TempGate(boolean... pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean allow() {
            if (index >= pattern.length) {
                return pattern[pattern.length - 1];
            }
            return pattern[index++];
        }
    }

    @Test
    @DisplayName("단일 라운드: 게이트 allow 여부에 따라 이동/출력, 우승자 출력 위임하기")
    void oneRoundPlayAndAnnouceWinner() {
        Validator<String[]> nameValidator = new UniqueNamesValidator();
        CarsFactory factory = new CarsFactory(nameValidator);
        TestPlayResultPrinter playPrinter = new TestPlayResultPrinter();
        TestWinnersPrinter winnersPrinter = new TestWinnersPrinter();

        ExcutionGate gate = new TempGate(true, false);

        RacingGameController controller = new RacingGameController(factory, gate, playPrinter, winnersPrinter);
        controller.play(new String[]{"pobi", "woni"}, 1);

        assertThat(playPrinter.lines).containsExactly(
                "pobi : -",
                "woni : "
        );
        assertThat(winnersPrinter.last).isEqualTo("pobi");
    }

    @Test
    @DisplayName("여러 라운드: 공동 우승자도 콤마로 출력")
    void multiRoundAndMultipleWinners() {
        Validator<String[]> nameValidator = new UniqueNamesValidator();
        CarsFactory factory = new CarsFactory(nameValidator);
        TestPlayResultPrinter playPrinter = new TestPlayResultPrinter();
        TestWinnersPrinter winnersPrinter = new TestWinnersPrinter();
        ExcutionGate gate = new TempGate(true, true, false, true, true, false);

        RacingGameController controller = new RacingGameController(factory, gate, playPrinter, winnersPrinter);

        controller.play(new String[]{"pobi", "woni", "lavine"}, 2);

        assertThat(playPrinter.lines).containsExactly(
                "pobi : -", "woni : -", "lavine : ",
                "pobi : --", "woni : --", "lavine : "
        );

        assertThat(winnersPrinter.last).isEqualTo("pobi, woni");
    }
}
