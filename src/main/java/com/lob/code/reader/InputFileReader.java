package com.lob.code.reader;

import com.lob.code.model.LetterHeader;

import java.io.*;

public class InputFileReader {

    public LetterHeader read() throws IOException {
        String fileName = "input.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String line;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        LetterHeader letterHeader = new LetterHeader();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                setFieldsOfLetterHeader(letterHeader, line);
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        finally {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(fileReader != null) {
                fileReader.close();
            }
        }
        return letterHeader;
    }

    private void setFieldsOfLetterHeader(LetterHeader letterHeader, String input) {
        String[] fields = input.split(":");
        if(fields[0].contains("Name")) {
            letterHeader.setName(fields[1].trim());
        } else if(fields[0].contains("Address Line 1")) {
            letterHeader.setAddressLine1(fields[1].trim());
        } else if(fields[0].contains("Address Line 2")) {
            letterHeader.setAddressLine2(fields[1].trim());
        } else if(fields[0]. contains("City")) {
            letterHeader.setCity(fields[1].trim());
        } else if(fields[0].contains("State")) {
            letterHeader.setState(fields[1].trim());
        } else if(fields[0].contains("Country")) {
            letterHeader.setCountry(fields[1].trim());
        } else if(fields[0].contains("Zip Code")) {
            letterHeader.setZipCode(fields[1].trim());
        } else if(fields[0].contains("Message")) {
            letterHeader.setMessage(fields[1].trim());
        }
    }
}
