package baseball;

import java.util.List;

public class BaseballGame {

    public static final int DIGIT_LENGTH = 3;
    private final Digits answer;

    public BaseballGame(int digitSize) {
        this.answer = Digits.ofRandomDigits(digitSize);
    }

    public GameResult compareResult(List<Digit> digits) {
        int balls = 0;
        int strikes = 0;

        for (int round = 0; round < DIGIT_LENGTH; round++) {
            int curPos = answer.indexOf(digits.get(round));

            boolean equalNumberExists = (curPos != -1);
            boolean isEqualPosition = (round == curPos);

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
            System.out.println("낫싱");
            return false;
        }

        System.out.println(createResultString(result));

        if (result.isAllStrikes(DIGIT_LENGTH)) {
            System.out.println(DIGIT_LENGTH + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }

        return false;
    }

    private String createResultString(GameResult result) {
        String resultString = "";
        boolean hasBalls = result.getBalls() > 0;
        boolean hasStrikes = result.getStrikes() > 0;

        if (hasBalls) {
            resultString += result.getBalls() + "볼";
        }

        if (hasBalls && hasStrikes) {
            resultString += " ";
        }

        if (hasStrikes) {
            resultString += result.getStrikes() + "스트라이크";
        }

        return resultString;
    }
}
