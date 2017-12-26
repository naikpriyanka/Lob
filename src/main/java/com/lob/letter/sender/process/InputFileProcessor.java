package com.lob.letter.sender.process;

import com.lob.letter.sender.model.LetterHeader;
import com.lob.letter.sender.model.Official;
import com.lob.letter.sender.service.RepresentativeService;
import com.lob.model.Address;
import com.lob.model.Letter;
import com.lob.net.LobResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class InputFileProcessor {

    public String process(LetterHeader letterHeader) {
        Map<String, String> mergeVariables = new HashMap<>();
        mergeVariables.put("name", letterHeader.getName());
        mergeVariables.put("message", letterHeader.getMessage());
        final File file = new File("src/main/resources/letter.html");
        try {
            LobResponse<Letter> response = getLetterRequest(letterHeader, mergeVariables, file).create();
            Letter letter = response.getResponseBody();
            return letter.getUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Letter.RequestBuilder getLetterRequest(LetterHeader letterHeader, Map<String, String> mergeVariables, File file) throws Exception {
        return new Letter.RequestBuilder()
                .setDescription("Lob Coding Challenge Letter")
                .setFile(file)
                .setColor(true)
                .setMergeVariables(mergeVariables)
                .setTo(getToAddress(letterHeader))
                .setFrom(getFromAddress(letterHeader));
    }

    private Address.RequestBuilder getToAddress(LetterHeader letterHeader) throws Exception {
        RepresentativeService representativeService = new RepresentativeService();
        Official official = representativeService.getRepresentativesInfo(letterHeader);
        return new Address.RequestBuilder()
                .setName(official.getName())
                .setLine1(official.getAddress().get(0).getLine1())
                .setCity(official.getAddress().get(0).getCity())
                .setState(official.getAddress().get(0).getState())
                .setZip(official.getAddress().get(0).getZip());
    }

    private Address.RequestBuilder getFromAddress(LetterHeader letterHeader) {
        return new Address.RequestBuilder()
                .setName(letterHeader.getName())
                .setLine1(letterHeader.getAddressLine1() + " " + letterHeader.getAddressLine2())
                .setCity(letterHeader.getCity())
                .setState(letterHeader.getState())
                .setZip(letterHeader.getZipCode())
                .setCountry(letterHeader.getCountry());
    }
}
