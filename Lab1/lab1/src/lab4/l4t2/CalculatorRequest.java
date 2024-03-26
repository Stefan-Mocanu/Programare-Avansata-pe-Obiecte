package lab4.l4t2;

public record CalculatorRequest(Object leftOperand, Object rightOperand, String operation) {

    public String getRequestType(){
        if (leftOperand instanceof Boolean && rightOperand instanceof Boolean)return "Boolean";
        if (leftOperand instanceof Integer && rightOperand instanceof Integer)return "Integer";
        if (leftOperand instanceof Double && rightOperand instanceof Double)return "Double";
        return "error";
    }

    @Override
    public String toString(){
        return leftOperand.toString() + operation + rightOperand.toString();
    }
}
