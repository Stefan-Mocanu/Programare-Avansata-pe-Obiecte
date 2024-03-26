package lab4.l4t2;

public class InvalidOperationException extends CalculatorException{
    public InvalidOperationException() {
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}
