package baseball;

public class Digit {

    private int number;

    public Digit(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Digit digit = (Digit) obj;

        return this.number == digit.number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
