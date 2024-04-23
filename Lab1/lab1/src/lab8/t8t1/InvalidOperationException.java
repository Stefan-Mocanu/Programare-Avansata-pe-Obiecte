package lab8.t8t1;

public class InvalidOperationException extends CalculatorException {
    public InvalidOperationException() {
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}
