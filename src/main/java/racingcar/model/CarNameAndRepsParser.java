package racingcar.model;

public class CarNameAndRepsParser {
    public String[] parseNames(String userFirstInput) {
        validateEmptyInput(userFirstInput);

        userFirstInput = userFirstInput.trim();

        // 콤백 전후의 공백 제거 및 마지막 빈 토큰까지 포함
        String[] tokens = userFirstInput.split("\\s*,\\s*", -1);

        for (String name : tokens) {
            validateNameLength(name);
        }

        return tokens;
    }

    private void validateNameLength(final String name) {
        if (name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("이름은 1~5자여야 합니다: '" + name + "'");
        }
    }

    private void validateEmptyInput(final String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }

    public int parseRepetitions(final String userSecondInput) {
        validateEmptyInput(userSecondInput);

        int parsedRepetitions = Integer.parseInt(userSecondInput);

        if (parsedRepetitions <= 0) {
            throw new IllegalArgumentException("반복 회수는 1이상의 정수만 허용됩니다: " + parsedRepetitions);
        }

        return parsedRepetitions;
    }
}
