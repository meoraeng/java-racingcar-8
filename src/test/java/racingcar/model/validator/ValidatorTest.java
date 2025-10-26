package racingcar.model.validator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public abstract class ValidatorTest<T> extends NsTest {
    protected abstract Validator<T> validator();

    protected abstract T validSample();

    @Test
    @DisplayName("비어있는 경우 throw")
    public void emptyInputValidatiorTest() {
        assertThatThrownBy(() -> validator().validate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효한 값에 대해서는 예외를 던지지 않는다.")
    void validInputDoesNotThrowTest() {
        T valid = validSample();
        assertThatCode(() -> validator().validate(valid))
                .doesNotThrowAnyException();
    }


    @Override
    protected void runMain() {
    }
}
