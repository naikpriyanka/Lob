package com.letter.sender.lob.reader;

import com.letter.sender.lob.model.LetterHeader;

import java.io.*;

public class InputFileReader {

    public LetterHeader read(String filename) throws IOException {
        File file = new File(filename);
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
            validateFields(letterHeader);
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        } catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(fileReader != null) {
                fileReader.close();
            }
        }
        return letterHeader;
    }

    private void validateFields(LetterHeader letterHeader) throws Exception {
        if(letterHeader.getAddressLine1().isEmpty())
            throw new Exception("Invalid input");
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
