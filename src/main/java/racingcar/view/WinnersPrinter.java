package racingcar.view;

import java.util.List;

public class WinnersPrinter {
    public void printWinners(List<String> winners) {
        String joined = String.join(", ", winners);
        System.out.println("최종 우승자 : " + joined);
    }
}

