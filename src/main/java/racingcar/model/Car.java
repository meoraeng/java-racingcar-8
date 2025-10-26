package racingcar.model;

import racingcar.model.port.PlayResultPrinter;

public class Car {
    private final String name;
    private int point = 0;

    public Car(String name) {
        this.name = name;
    }

    public void moveForward() {
        point++;
    }

    public void printTo(PlayResultPrinter printer) {
        printer.print(name, point);
    }
}
