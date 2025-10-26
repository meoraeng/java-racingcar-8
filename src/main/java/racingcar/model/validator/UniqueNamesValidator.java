package racingcar.model.validator;

import java.util.HashSet;
import java.util.Set;

public class UniqueNamesValidator implements Validator<String[]> {
    @Override
    public void validate(String[] names) {
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("이름 배열이 비어있습니다.");
        }

        Set<String> seenNames = new HashSet<>();
        
        for (String name : names) {
            name = name.trim();
            validateEmptyName(name);
            ensureUnique(seenNames, name);
        }
    }

    private void validateEmptyName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("배열에 비어있는 값이 있습니다." + name);
        }
    }

    private void ensureUnique(Set<String> seenNames, String name) {
        if (!seenNames.add(name)) {
            throw new IllegalArgumentException("이름에 중복이 존재합니다 : " + name);
        }
    }
}
