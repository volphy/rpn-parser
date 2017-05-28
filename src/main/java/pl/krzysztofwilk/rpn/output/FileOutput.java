package pl.krzysztofwilk.rpn.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import static pl.krzysztofwilk.rpn.RpnParser.LOG;
import static pl.krzysztofwilk.rpn.output.OutputFactoryImpl.FILE_PREFIX;

public class FileOutput implements Output {

    private FileOutputStream stream;

    FileOutput(String output) throws FileNotFoundException {
        String fileName = output.replace(FILE_PREFIX, "");

        stream= new FileOutputStream(new File(fileName));
    }

    @Override
    public void display(String[] expression, BigDecimal result) {
        byte[] binaryEntry = format(expression, result).getBytes();

        try {
            stream.write(binaryEntry);
        } catch (IOException e) {
            LOG.error("Cannot print to file:  {}", e.getMessage(), e);
        }
    }
}
