package pl.krzysztofwilk.rpn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.krzysztofwilk.rpn.operator.Operator;
import pl.krzysztofwilk.rpn.operator.OperatorFactoryImpl;
import pl.krzysztofwilk.rpn.operator.OperatorSymbol;
import pl.krzysztofwilk.rpn.output.Output;
import pl.krzysztofwilk.rpn.output.OutputFactoryImpl;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class RpnParser {

    public static final Logger LOG = LoggerFactory.getLogger(RpnParser.class);

    private Output output;

    @SuppressWarnings("squid:S1068")
    private static final String DEFAULT_OUTPUT  = "console";

    @SuppressWarnings("squid:S1068")
    private static final String TMP_OUTPUT = "file:target/output.log";

    public static void main(String[] args) {
        RpnParser parser = new RpnParser();

        OutputFactoryImpl outputFactory = new OutputFactoryImpl();

        parser.output = outputFactory.makeOutput(TMP_OUTPUT);

        BigDecimal result = parser.calculate(args);

        parser.printResult(args, result);
    }

    BigDecimal calculate(String[] expression) {
        Deque<BigDecimal> stack = new ArrayDeque<>();

        int tokenNo = 0;

        OperatorFactoryImpl operatorFactory = new OperatorFactoryImpl();

        List<String> symbols = OperatorSymbol.symbols();

        while (tokenNo < expression.length) {
            String token = expression[tokenNo].trim();
            tokenNo++;

            if (isNumber(token)) {
                stack.push(new BigDecimal(token));
            } else if (symbols.contains(token)) {
                BigDecimal a = stack.pop();
                BigDecimal b = stack.pop();

                Operator operator = operatorFactory.makeOperator(token);
                operator.apply(a, b, stack);
            } else {
                throw new IllegalArgumentException("Illegal token: '" + token + "'");
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        return token.matches("^[-+]?[\\d]*\\.?[\\d]+$");
    }

    private void printResult(String[] expression, BigDecimal result) {
        output.display(expression, result);
    }
}
