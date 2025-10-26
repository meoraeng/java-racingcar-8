package racingcar.view;


import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String CAR_NAMES_INPUT_GUIDE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private final String REPETITIONS_INPUT_GUIDE = "시도할 횟수는 몇 회인가요?";

    public String readCarNames() {
        System.out.println(CAR_NAMES_INPUT_GUIDE);
        return Console.readLine();
    }

    public String readRepetitions() {
        System.out.println(REPETITIONS_INPUT_GUIDE);
        return Console.readLine();
    }
}
