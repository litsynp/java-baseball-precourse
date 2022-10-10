package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static baseball.Constants.ANSWER_MAX_VALUE;
import static baseball.Constants.ANSWER_MIN_VALUE;
import static baseball.Constants.DIGIT_LENGTH;

public class BaseballGame {

    private Digit[] answers = new Digit[DIGIT_LENGTH];

    private final String BALL_STRING = "볼";
    private final String STRIKE_STRING = "스트라이크";
    private final String NOTHING_STRING = "낫싱";
    private final String ALL_STRIKES_STRING = DIGIT_LENGTH + "개의 숫자를 모두 맞히셨습니다! 게임 종료";

    public BaseballGame() {
        generateAnswers();
    }

    public void startNewGame() {
        generateAnswers();
    }

    private void generateAnswers() {
        Set<Integer> uniqueDigits = new LinkedHashSet<>();
        while (uniqueDigits.size() != 3) {
            int random = Randoms.pickNumberInRange(ANSWER_MIN_VALUE, ANSWER_MAX_VALUE);
            uniqueDigits.add(random);
        }

        answers = uniqueDigits.stream()
                .mapToInt(Number::intValue)
                .mapToObj(Digit::new)
                .toArray(Digit[]::new);
    }

    public Digit[] createBaseballs(int[] ballNumbers) {
        Digit[] digits = new Digit[DIGIT_LENGTH];
        for (int i = 0; i < DIGIT_LENGTH; i++) {
            digits[i] = new Digit(ballNumbers[i]);
        }
        return digits;
    }

    public GameResult compareResult(Digit[] digits) {
        int balls = 0;
        int strikes = 0;

        for (int round = 0; round < DIGIT_LENGTH; round++) {
            int position = Arrays.asList(answers).indexOf(digits[round]);

            boolean equalNumberExists = (position != -1);
            boolean isEqualPosition = (round == position);

            if (equalNumberExists && isEqualPosition) {
                strikes += 1;
            }

            if (equalNumberExists && !isEqualPosition) {
                balls += 1;
            }
        }

        return new GameResult(balls, strikes);
    }

    public boolean announceResult(GameResult result) {
        if (result.isNothing()) {
            System.out.println(NOTHING_STRING);
            return false;
        }

        System.out.println(createResultString(result));

        if (result.isAllStrikes()) {
            System.out.println(ALL_STRIKES_STRING);
            return true;
        }

        return false;
    }

    private String createResultString(GameResult result) {
        String resultString = "";
        boolean hasBalls = result.getBalls() > 0;
        boolean hasStrikes = result.getStrikes() > 0;

        if (hasBalls) {
            resultString += result.getBalls() + BALL_STRING;
        }

        if (hasBalls && hasStrikes) {
            resultString += " ";
        }

        if (hasStrikes) {
            resultString += result.getStrikes() + STRIKE_STRING;
        }

        return resultString;
    }
}
