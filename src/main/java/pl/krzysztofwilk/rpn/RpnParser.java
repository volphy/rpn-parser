package pl.krzysztofwilk.rpn;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class RpnParser {

    private String getExpression(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Missing RPN expression");
        }

        return args[0];
    }

    private BigDecimal calculateExact(String expression) {
        StringTokenizer st = new StringTokenizer(expression, " ");
        Deque<BigDecimal> stack = new ArrayDeque<>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();

            if (token.matches("^[-+]?[\\d]*\\.?[\\d]+$")) {
                stack.push(new BigDecimal(token));
            } else if (token.matches("^[+\\-]$")) {
                BigDecimal a = stack.pop();
                BigDecimal b = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(b.add(a));
                        break;
                    case "-":
                        stack.push(b.subtract(a));
                        break;
//                    case "*":
//                        stack.push(b.multiply(a));
//                        break;
//                    case "/":
//                        stack.push(b.divide(a));
//                        break;
                    default:
                        break; // Should not reach here
                }
            } else {
                throw new IllegalStateException("Illegal token: '" + token + "'");
            }
        }
        return stack.pop();
    }

    int calculate(String expression) {
        StringTokenizer st = new StringTokenizer(expression, " ");
        Deque<Integer> stack = new ArrayDeque<>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();

            if (token.matches("^\\d+$")) {
                stack.push(Integer.valueOf(token));
            } else if (token.matches("^[+\\-*/]$")) {
                Integer a = stack.pop();
                Integer b = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(b + a);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(b * a);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                    default:
                        break; // Should not reach here
                }
            } else {
                throw new IllegalStateException("Illegal token: '" + token + "'");
            }
        }
        return stack.pop();
    }

    private void printResult(String expression, BigDecimal result, PrintStream outputStream) {
        outputStream.println(expression + " = " + result);
    }

    public static void main(String[] args) {
        RpnParser parser = new RpnParser();

        String expression = parser.getExpression(args);

        BigDecimal result = parser.calculateExact(expression);

        parser.printResult(expression, result, System.out);
    }
}
