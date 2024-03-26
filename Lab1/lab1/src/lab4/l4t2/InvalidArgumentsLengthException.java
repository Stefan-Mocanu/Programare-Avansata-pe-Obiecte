package lab4.l4t2;

public class InvalidArgumentsLengthException extends CalculatorRuntimeException{
    public InvalidArgumentsLengthException() {
    }

    public InvalidArgumentsLengthException(String message) {
        super(message);
    }
}
