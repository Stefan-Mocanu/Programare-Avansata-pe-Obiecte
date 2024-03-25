package lab3.l3t6;

public class BooleanCalculatorResult extends CalculatorResult {
    public BooleanCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() {
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
        }
        return null;
    }
}
