package lab8.t8t1;

public class UnknownOperandTypeException extends CalculatorException {
    public UnknownOperandTypeException() {
    }

    public UnknownOperandTypeException(String message) {
        super(message);
    }
}
