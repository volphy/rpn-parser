package pl.krzysztofwilk.rpn.output;

import java.util.Arrays;
import java.util.List;

public enum OutputName {
    CONSOLE("console"),
    FILE("file");

    private String output;

    OutputName(String output) {
        this.output = output;
    }

    public String output() {
        return output;
    }

    public static List<String> outputs() {
        return Arrays.asList(CONSOLE.output, FILE.output);
    }
}
