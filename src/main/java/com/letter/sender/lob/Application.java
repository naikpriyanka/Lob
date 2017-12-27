package com.letter.sender.lob;

import com.letter.sender.lob.model.LetterHeader;
import com.letter.sender.lob.writer.OutputWriter;
import com.lob.Lob;
import com.letter.sender.lob.process.InputFileProcessor;
import com.letter.sender.lob.reader.InputFileReader;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        Lob.init("test_e2357492b594adbccb8fac6764318da28dc", "2017-11-08");
        InputFileReader inputFileReader = new InputFileReader();
        if(args.length > 0) {
            try {
                LetterHeader letterHeader = inputFileReader.read(args[0]);
                InputFileProcessor inputFileProcessor = new InputFileProcessor();
                String url = inputFileProcessor.process(letterHeader);
                OutputWriter outputWriter = new OutputWriter();
                System.out.println(outputWriter.write(url));
            } catch (IOException e) {
                System.out.println("Wrong input path");
            }
        } else {
            System.out.println("Input file not specified");
        }
    }
}
