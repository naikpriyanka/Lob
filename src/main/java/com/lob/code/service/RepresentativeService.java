package com.lob.code.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lob.code.model.LetterHeader;
import com.lob.code.model.Official;
import com.lob.code.model.RepresentativeInfo;
import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.stream.Collectors;

public class RepresentativeService {

    final static String SPACE = " ";

    public Official getRepresentativesInfo(LetterHeader letterHeader) throws Exception {
        URIBuilder uriBuilder = getURL(letterHeader);
        try {
            String response = doGET(uriBuilder.toString());
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            RepresentativeInfo representativeInfo = mapper.readValue(response, RepresentativeInfo.class);
            if(!representativeInfo.getOfficials().isEmpty()) {
                return representativeInfo.getOfficials().get(0);
            } else {
                throw new Exception("Did not find representative");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URIBuilder getURL(LetterHeader letterHeader) {
        StringBuilder address = getAddress(letterHeader);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("www.googleapis.com");
        uriBuilder.setPath("/civicinfo/v2/representatives");
        uriBuilder.setParameter("address", address.toString());
        uriBuilder.setParameter("includeOffices", "true");
        uriBuilder.setParameter("roles", "legislatorUpperBody");
        uriBuilder.setParameter("roles", "legislatorLowerBody");
        uriBuilder.setParameter("key", "AIzaSyB1RijsGgGnpIuKXLXylqEZAJ3FpAS-iUk");
        return uriBuilder;
    }

    private StringBuilder getAddress(LetterHeader letterHeader) {
        StringBuilder address = new StringBuilder();
        address.append(letterHeader.getAddressLine1())
                .append(SPACE)
                .append(letterHeader.getAddressLine2())
                .append(SPACE)
                .append(letterHeader.getCity())
                .append(SPACE)
                .append(letterHeader.getState())
                .append(SPACE)
                .append(letterHeader.getCountry())
                .append(SPACE)
                .append(letterHeader.getZipCode());
        return address;
    }

    private String doGET(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        InputStream inputStream = Channels.newInputStream(rbc);
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        rbc.close();
        return result;
    }

}