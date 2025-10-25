package racingcar.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProbabilityGateTest extends NsTest {
    @DisplayName("랜덤 값 4이상이면 true 반환")
    @Test
    public void returnTrueWhenRandomIsOverFour() {
        assertRandomNumberInRangeTest(
                () -> {
                    
                }
        );
    }

    @DisplayName("랜덤 값이 4미만이면 false 반환")
    @Test
    public void returnTrueWhenRandomIsOverFour() {

    }

    @Override
    protected void runMain() {
    }
}
