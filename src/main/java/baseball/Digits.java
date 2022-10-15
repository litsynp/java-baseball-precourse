package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Digits {

    public static final int ANSWER_MIN_VALUE = 1;
    public static final int ANSWER_MAX_VALUE = 9;

    private final List<Digit> digits;

    public Digits(List<Digit> digits) {
        this.digits = digits;
    }

    public int getDigitSize() {
        return digits.size();
    }

    public void setDigit(int index, Digit digit) {
        if (index < 0 || index >= getDigitSize()) {
            throw new IllegalArgumentException("숫자의 색인은 0 이상 " + (getDigitSize()) + "미만이어야 합니다.");
        }

        digits.set(index, digit);
    }

    public int indexOf(Digit digit) {
        return digits.indexOf(digit);
    }

    public static Digits ofRandomDigits(int digitSize) {
        Set<Integer> uniqueDigits = new LinkedHashSet<>();
        while (uniqueDigits.size() != digitSize) {
            int random = Randoms.pickNumberInRange(ANSWER_MIN_VALUE, ANSWER_MAX_VALUE);
            uniqueDigits.add(random);
        }

        List<Digit> digits = uniqueDigits.stream()
                .mapToInt(Number::intValue)
                .mapToObj(Digit::new)
                .collect(Collectors.toList());

        return new Digits(digits);
    }
}
