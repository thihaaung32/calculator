package calculator.operators;

import calculator.evaluator.Operand;

public class AddOperator extends Operator {

    public int priority() {
        return 1; // The Priority level for '+' operator
    }


    public Operand execute(Operand operandOne, Operand operandTwo) {
        int result = operandOne.getValue() + operandTwo.getValue();
        return new Operand(result);
    }
}
