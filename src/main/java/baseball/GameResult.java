package baseball;

public class GameResult {

    private int balls;
    private int strikes;

    public GameResult(int balls, int strikes) {
        this.balls = balls;
        this.strikes = strikes;
    }

    public int getBalls() {
        return balls;
    }

    public int getStrikes() {
        return strikes;
    }

    public boolean isNothing() {
        return balls == 0 && strikes == 0;
    }

    public boolean isAllStrikes(int digitSize) {
        return strikes == digitSize;
    }
}
