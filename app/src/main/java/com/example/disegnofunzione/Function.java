package com.example.disegnofunzione;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

//rappresenta una funzione, utilizzando la libreria exp4j
public class Function {
    private String var = "x";       //variabile

    private ExpressionBuilder builder;      //builder, serve per costruire la funzione

    private Expression function;    //funzione vera e propria

    public String getStringFunction() {
        return stringFunction;
    }

    private String stringFunction;      //funzione (in formato stampabile)

    @Override
    public String toString() {      //ridefinizione di toString(), per comodità
        return getStringFunction();
    }

    public Function(String stringFunction) {        //costruttore, a partire da una stringa
        this.stringFunction = stringFunction;
        builder = new ExpressionBuilder(stringFunction)
                .variable(var);
        function = builder.build();
    }

    public double Evaluate(double x) throws ArithmeticException {       //calcola f(x)
        double result = function
                .setVariable(var, x)
                .evaluate();
        return result;
    }
}