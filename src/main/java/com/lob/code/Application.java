package com.lob.code;

import com.lob.Lob;
import com.lob.code.model.LetterHeader;
import com.lob.code.process.InputFileProcessor;
import com.lob.code.reader.InputFileReader;
import com.lob.code.writer.OutputWriter;

public class Application {

    public static void main(String[] args) throws Exception {
        Lob.init("test_e2357492b594adbccb8fac6764318da28dc", "2017-11-08");
        InputFileReader inputFileReader = new InputFileReader();
        LetterHeader letterHeader = inputFileReader.read();
        InputFileProcessor inputFileProcessor = new InputFileProcessor();
        String url = inputFileProcessor.process(letterHeader);
        OutputWriter outputWriter = new OutputWriter();
        System.out.println(outputWriter.write(url));
    }
}
