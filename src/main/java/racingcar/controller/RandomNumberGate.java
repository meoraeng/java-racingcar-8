package racingcar.controller;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

import racingcar.model.ExcutionGate;

public class RandomNumberGate implements ExcutionGate {
    public boolean allow() {
        int randomNumber = pickNumberInRange(0, 9);

        return randomNumber >= 4;
    }
}
