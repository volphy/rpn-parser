package pl.krzysztofwilk.rpn.output;

import java.math.BigDecimal;

public class ConsoleOutput implements Output {

    @Override
    @SuppressWarnings("squid:S106")
    public void display(String[]expression, BigDecimal result) {
        System.out.println(format(expression, result));
    }
}
