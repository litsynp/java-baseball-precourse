package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.stream.Stream;

import static baseball.Constants.DIGIT_LENGTH;

public class Application {

    private static final BaseballGame baseballGame = new BaseballGame();
    private static final String GAME_CONTINUE_STRING = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    public static void main(String[] args) {
        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            int[] ballNumbers = parseInputNumbers(Console.readLine());
            Digit[] digits = baseballGame.createBaseballs(ballNumbers);
            GameResult result = baseballGame.compareResult(digits);
            boolean isCorrect = baseballGame.announceResult(result);

            if (isCorrect) {
                checkContinue();
            }
        }
    }

    public static void checkContinue() {
        System.out.println(GAME_CONTINUE_STRING);
        String input = Console.readLine();

        if (input.equals("2")) {
            System.exit(0);
        }
        baseballGame.startNewGame();
    }

    public static int[] parseInputNumbers(String input) {
        if (input.length() != DIGIT_LENGTH) {
            throw new IllegalArgumentException();
        }

        try {
            return Stream.of(input.split("")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
