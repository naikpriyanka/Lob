package com.lob.code.process;

import com.lob.code.model.LetterHeader;
import com.lob.exception.APIException;
import com.lob.exception.AuthenticationException;
import com.lob.exception.InvalidRequestException;
import com.lob.exception.RateLimitException;
import com.lob.model.Address;
import com.lob.model.Letter;
import com.lob.net.LobResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputFileProcessor {

    public void process(LetterHeader letterHeader) {
        Map<String, String> mergeVariables = new HashMap<>();
        mergeVariables.put("name", letterHeader.getName());
        mergeVariables.put("message", letterHeader.getMessage());
        final File file = new File("src/main/resources/letter.html");
        try {
            LobResponse<Letter> response = new Letter.RequestBuilder()
                    .setDescription("Demo Letter")
                    .setFile(file)
                    .setColor(true)
                    .setMergeVariables(mergeVariables)
                    .setTo(
                            new Address.RequestBuilder()
                                    .setName("Leore Avidar")
                                    .setLine1(letterHeader.getAddressLine1())
                                    .setLine2(letterHeader.getAddressLine2())
                                    .setCity(letterHeader.getCity())
                                    .setState(letterHeader.getState())
                                    .setZip(letterHeader.getZipCode())
                                    .setCountry(letterHeader.getCountry())
                    )
                    .setFrom(
                            new Address.RequestBuilder()
                                    .setName(letterHeader.getName())
                                    .setLine1(letterHeader.getAddressLine1())
                                    .setLine2(letterHeader.getAddressLine2())
                                    .setCity(letterHeader.getCity())
                                    .setState(letterHeader.getState())
                                    .setZip(letterHeader.getZipCode())
                                    .setCountry(letterHeader.getCountry())
                    )
                    .create();
            Letter letter = response.getResponseBody();
            System.out.println(letter.toString());
        } catch (APIException | IOException | InvalidRequestException | AuthenticationException | RateLimitException e) {
            e.printStackTrace();
        }
    }
}
