package pl.krzysztofwilk.rpn.output;

import java.math.BigDecimal;

public interface Output {

    void display(String[] expression, BigDecimal result);

    default String format(String[] expression, BigDecimal result) {
        StringBuilder entry= new StringBuilder();
        for (String token : expression) {
            entry.append(token);
            entry.append(" ");
        }
        entry.append("  =   ");
        entry.append(result);

        return entry.toString();
    }
}
