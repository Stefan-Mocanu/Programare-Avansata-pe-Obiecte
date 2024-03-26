package lab4.l4t2;

public class UnknownOperandTypeException extends CalculatorException{
    public UnknownOperandTypeException() {
    }

    public UnknownOperandTypeException(String message) {
        super(message);
    }
}
