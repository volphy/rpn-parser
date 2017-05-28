package pl.krzysztofwilk.rpn.operator;

import java.util.Arrays;
import java.util.List;

public enum OperatorSymbol {
    ADDITION("+"),
    MINUS("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private String symbol;

    OperatorSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public static List<String> symbols() {
        return Arrays.asList(ADDITION.symbol(),
                MINUS.symbol,
                MULTIPLICATION.symbol,
                DIVISION.symbol);
    }
}
