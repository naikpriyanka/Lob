package com.lob.letter.sender;

import com.lob.Lob;
import com.lob.letter.sender.model.LetterHeader;
import com.lob.letter.sender.process.InputFileProcessor;
import com.lob.letter.sender.reader.InputFileReader;
import com.lob.letter.sender.writer.OutputWriter;

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
