package racingcar.model.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UniqueNamesValidatorTest extends ValidatorTest<String[]> {
    @Override
    protected Validator<String[]> validator() {
        return new UniqueNamesValidator();
    }

    @Override
    protected String[] validSample() {
        return new String[]{"pobi", "woni"};
    }

    @Test
    @DisplayName("중복이 존재하는 경우에 대해 validation")
    void duplicateNameTest() {
        assertThatThrownBy(() -> validator().validate(new String[]{"a", "b", "a"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("대소문자를 구분하여 중복을 체크한다.")
    void caseSensitiveAllowTest() {
        assertThatCode(() -> validator().validate(new String[]{"pobi", "POBI"}))
                .doesNotThrowAnyException();
    }
}
