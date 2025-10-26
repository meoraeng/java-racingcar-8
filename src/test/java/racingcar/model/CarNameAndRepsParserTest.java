package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


public class CarNameAndRepsParserTest extends NsTest {
    private final CarNameParser parser = new CarNameParser();

    @Test
    @DisplayName("콤마만 사용한 경우 이름 입력값이 문자열 배열로 반환되는지 테스트")
    public void parseNameOnlyCommaSuccessTest() {
        final String userFirstInput = "pobi,woni";
        final String[] expectedReturn = {"pobi", "woni"};

        String[] actual = parser.parseName(userFirstInput);

        assertThat(actual).isEqualTo(expectedReturn);
    }

    @Test
    @DisplayName("콤마와 공백을 함께 쓴 경우 이름 입력값이 문자열 배열로 반환되는지 테스트")
    public void parseNameAllowSpacesSuccessTest() {
        final String userFirstInput = " pobi, woni";
        final String[] expectedReturn = {"pobi", "woni"};

        String[] actual = parser.parseName(userFirstInput);

        assertThat(actual).isEqualTo(expectedReturn);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    @DisplayName("차 이름 입력값이 Null이나 Empty인 경우 IllegalArgumentException  발생")
    public void parseNamesThrowsExceptionForEmpty(String input) {

        assertThatThrownBy(() -> {
            parser.parseRepetitions(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력이 비어있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abcedf",
            "",
            "   "
    })
    @DisplayName("분할된 이름 토큰이 1~5글자가 아닌 값이 있는 경우 IllegalArgumentException 발생")
    public void parseNamesLengthValidation(String token) {
        assertThatThrownBy(() -> {
            parser.parseNames(token);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 1~5자여야 합니다.");
        assertThatThrownBy(() -> {
            parser.parseNames("pobi," + token);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 1~5자여야 합니다.");
    }

    @Test
    @DisplayName("끝에 콤마가 오면 빈 토큰이 되어 IllegalArgumentException 발생")
    public void parseNamesThrowsExceptionForTrailingCommaValidation() {
        assertThatThrownBy(() -> {
            parser.parseNames("pobi,woni,");
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("반복 회수 입력 값이 정수 값으로 반환 되는지 테스트")
    public void parseRepetitionsSuccessTest() {
        final String userSecondInput = "5";
        final int expectedReturn = 5;

        int actual = parser.parseRepetitions(userSecondInput);

        assertThat(actual).isEqualTo(expectedReturn);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    @DisplayName("차 이름 입력값이 Null이나 Empty인 경우 IllegalArgumentException  발생")
    public void parseRepetitionsThrowsExceptionForEmpty(String input) {

        assertThatThrownBy(() -> {
            parser.parseRepetitions(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력이 비어있습니다.");
    }

    @Test
    @DisplayName("0이하의 값이 입력되면 IllegalArgumentException  발생")
    public void parseRepetitionsThrowsExceptionForIntegerRangeTest() {
        final String userSecondInput = "0";

        assertThatThrownBy(() -> {
            parser.parseRepetitions(userSecondInput);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("반복 회수는 1이상의 정수만 허용됩니다.");
    }

    @Override
    protected void runMain() {
    }
}
