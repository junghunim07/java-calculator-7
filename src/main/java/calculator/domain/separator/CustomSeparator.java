package calculator.domain.separator;

import calculator.domain.number.Number;
import calculator.domain.number.Numbers;
import java.util.Arrays;
import java.util.List;

public class CustomSeparator implements Separator {

    private final String separator;

    public CustomSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public Numbers separate(String value) {
        return new Numbers(convert(validateSeparator(value)));
    }

    private List<String> divideBySeparator(String value) {
        return Arrays.stream(value.split(separator)).toList();
    }

    private List<Number> convert(List<String> values) {
        return values.stream()
                .map(Number::new)
                .toList();
    }

    private List<String> validateSeparator(String value) {
        validateEdgeSeparator(value);
        validateContinuousSeparator(value);
        return divideBySeparator(value);
    }

    private void validateEdgeSeparator(String value) {
        if (hasEdgeSeparator(value)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateContinuousSeparator(String value) {
        if (isContinuousSeparator(value)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasEdgeSeparator(String value) {
        return value.startsWith(separator) || value.endsWith(separator);
    }

    private boolean isContinuousSeparator(String value) {
        return value.contains(separator.repeat(2));
    }
}
