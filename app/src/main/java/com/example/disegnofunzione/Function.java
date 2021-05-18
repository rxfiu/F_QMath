package com.example.disegnofunzione;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {
    private String VAR = "x";
    private ExpressionBuilder Builder;
    private Expression Function;

    public Function(String stringFunction) {
        Builder = new ExpressionBuilder(stringFunction)
                .variable(VAR);
        Function = Builder.build();
    }

    public double Evaluate(double x) throws ArithmeticException {
        double result = Function
                .setVariable(VAR, x)
                .evaluate();
        return result;
    }

    private ExpressionBuilder getBuilder() {
        return Builder;
    }

    private void setBuilder(ExpressionBuilder builder) {
        Builder = builder;
    }

    private Expression getFunction() {
        return Function;
    }

    private void setFunction(Expression function) {
        Function = function;
    }
}