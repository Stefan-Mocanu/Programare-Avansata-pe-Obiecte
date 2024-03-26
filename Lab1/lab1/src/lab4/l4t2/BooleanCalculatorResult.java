package lab4.l4t2;

public class BooleanCalculatorResult extends CalculatorResult {
    public BooleanCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() throws InvalidOperationException {
        CalculatorRequest req = getRequest();
        boolean a = (boolean) req.leftOperand();
        boolean b = (boolean) req.rightOperand();
        switch (req.operation()) {
            case "||": {
                return a || b;
            }
            case "&&": {
                return a && b;
            }
            default: {
                throw new InvalidOperationException();
            }
        }
    }
}
