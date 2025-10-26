package racingcar.view;

import racingcar.model.port.PlayResultPrinter;

public class PlayResultConsolePrinter implements PlayResultPrinter {
    @Override
    public void print(String name, int point) {
        String dash = "-".repeat(point);
        System.out.println(name + " : " + dash);
    }
}
