package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static final int DIGIT_SIZE = 3;
    private static BaseballGame baseballGame = new BaseballGame(DIGIT_SIZE);
    private static final String GAME_CONTINUE_STRING = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    public static void main(String[] args) {
        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            int[] ballNumbers = parseInputNumbers(Console.readLine());
            List<Digit> digits = Arrays.stream(ballNumbers)
                    .mapToObj(Digit::new)
                    .collect(Collectors.toList());

            GameResult result = baseballGame.compareResult(digits);
            boolean isCorrect = baseballGame.announceResult(result);

            if (isCorrect) {
                checkContinue();
            }
        }
    }

    private static void checkContinue() {
        System.out.println(GAME_CONTINUE_STRING);
        String inputCode = Console.readLine();
        switch (GameContinueInput.of(inputCode)) {
            case EXIT:
                System.exit(0);
                break;
            case CONTINUE:
            default:
                baseballGame = new BaseballGame(DIGIT_SIZE);
        }
    }

    private static void validateInput(String input) {
        if (input.length() != DIGIT_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private static int[] parseInputNumbers(String input) {
        validateInput(input);

        try {
            return Stream.of(input.split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private enum GameContinueInput {

        CONTINUE("1"),
        EXIT("2"),
        ;

        private String code;

        GameContinueInput(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static GameContinueInput of(String code) {
            if (EXIT.getCode().equals(code)) {
                return EXIT;
            }
            return CONTINUE;
        }
    }
}
