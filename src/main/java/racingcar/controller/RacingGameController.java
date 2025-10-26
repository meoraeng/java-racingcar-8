package racingcar.controller;

import racingcar.model.Cars;
import racingcar.model.ExcutionGate;
import racingcar.model.factory.CarsFactory;
import racingcar.model.port.PlayResultPrinter;
import racingcar.view.WinnersPrinter;

public class RacingGameController {
    private final CarsFactory factory;
    private final ExcutionGate gate;
    private final PlayResultPrinter playPrinter;
    private final WinnersPrinter winnersPrinter;

    public RacingGameController(CarsFactory factory,
                                ExcutionGate gate,
                                PlayResultPrinter playPrinter,
                                WinnersPrinter winnersPrinter) {
        this.factory = factory;
        this.gate = gate;
        this.playPrinter = playPrinter;
        this.winnersPrinter = winnersPrinter;
    }

    public void play(String[] names, int repetitions) {
        Cars cars = factory.createFrom(names);

        System.out.println("실행 결과");
        for (int i = 0; i < repetitions; i++) {
            cars.moveForwardAllowedCars(gate);
            cars.printAllTo(playPrinter);
            System.out.println();
        }

        winnersPrinter.printWinners(cars.findWinnersNames());
    }
}
