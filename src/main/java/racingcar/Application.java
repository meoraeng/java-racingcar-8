package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.controller.RandomNumberGate;
import racingcar.model.CarNameAndRepsParser;
import racingcar.model.ExcutionGate;
import racingcar.model.factory.CarsFactory;
import racingcar.model.port.PlayResultPrinter;
import racingcar.model.validator.UniqueNamesValidator;
import racingcar.model.validator.Validator;
import racingcar.view.InputView;
import racingcar.view.PlayResultConsolePrinter;
import racingcar.view.WinnersPrinter;

public class Application {
    public static void main(String[] args) {
        InputView input = new InputView();
        CarNameAndRepsParser parser = new CarNameAndRepsParser();

        String[] nameTokens = parser.parseNames(input.readCarNames());
        int repetitions = parser.parseRepetitions(input.readRepetitions());

        Validator<String[]> validator = new UniqueNamesValidator();
        CarsFactory factory = new CarsFactory(validator);

        ExcutionGate gate = new RandomNumberGate();
        PlayResultPrinter playPrinter = new PlayResultConsolePrinter();
        WinnersPrinter winnersPrinter = new WinnersPrinter();

        RacingGameController controller = new RacingGameController(factory, gate, playPrinter, winnersPrinter);

        controller.play(nameTokens, repetitions);
    }
}
