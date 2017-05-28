package pl.krzysztofwilk.rpn.output;

import java.io.FileNotFoundException;

import static pl.krzysztofwilk.rpn.RpnParser.LOG;

public class OutputFactoryImpl implements  OutputFactory {

    static final String FILE_PREFIX = "file:";

    @Override
    public Output makeOutput(String output) {
        if (output.contentEquals("console")) {
            return new ConsoleOutput();
        } else if (output.startsWith(FILE_PREFIX)) {
            try {
                return new FileOutput(output);
            } catch (FileNotFoundException e) {
                LOG.error("Cannot open file {}", e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Illegal output: '" + output + "'");
        }

        throw new IllegalArgumentException("Illegal output: '" + output + "'");
    }
}
