package lab4.l4t2;

final public class DoubleCalculatorResult extends CalculatorResult {
    public DoubleCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() throws InvalidOperationException {
        CalculatorRequest req = getRequest();
        double a = (double)req.leftOperand();
        double b = (double)req.rightOperand();
        switch (req.operation()){
            case "+": {
                return a+b;
            }
            case "*":{
                return a*b;
            }
            case "-":{
                return a-b;
            }
            case "/":{
                if(b==0)throw new InvalidOperationException();
                return a/b;
            }
            default:{
                throw new InvalidOperationException();
            }
        }
    }
}
