package lab2.task7;

public class CalculatorRequest {
    Object leftOperand;
    Object rightOpernad;
    String operation;
    public CalculatorRequest(Object leftOperand, Object rightOperand, String operation) {
        this.leftOperand = leftOperand;
        this.rightOpernad = rightOperand;
        this.operation = operation;
    }
    public String getRequestType(){
        if (leftOperand instanceof Boolean && rightOpernad instanceof Boolean)return "Boolean";
        if (leftOperand instanceof Integer && rightOpernad instanceof Integer)return "Integer";
        if (leftOperand instanceof Double && rightOpernad instanceof Double)return "Double";
        return "error";
    }
    @Override
    public String toString(){
        return leftOperand.toString() + operation + rightOpernad.toString();
    }
}
