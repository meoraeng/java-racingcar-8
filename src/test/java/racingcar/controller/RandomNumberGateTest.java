package racingcar.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGateTest extends NsTest {
    @DisplayName("랜덤 값 4이상이면 true 반환")
    @Test
    public void returnTrueWhenRandomIsOverFour() {
        assertRandomNumberInRangeTest(
                () -> {
                    assertThat(new RandomNumberGate().allow()).isTrue();
                }, 4
        );
    }

    @DisplayName("랜덤 값이 4미만이면 false 반환")
    @Test
    public void returnFalseWhenRandomIsOverFour() {
        assertRandomNumberInRangeTest(
                () -> {
                    assertThat(new RandomNumberGate().allow()).isFalse();
                }, 0
        );
    }

    @Test
    @DisplayName("여러 번 호출해도 각 난수에 따라 올바르게 판단한다")
    void allowForMultipleCalls() {
        assertRandomNumberInRangeTest(
                () -> {
                    RandomNumberGate gate = new RandomNumberGate();
                    assertThat(gate.allow()).isTrue();
                    assertThat(gate.allow()).isFalse();
                    assertThat(gate.allow()).isTrue();
                },
                5, 3, 9
        );
    }

    @Override
    protected void runMain() {
    }
}
