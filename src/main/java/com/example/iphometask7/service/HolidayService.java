package com.example.iphometask7.service;

import java.lang.String;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HolidayService {
    public final String URL = "https://date.nager.at/api/v2/";

    public boolean checkIsTodayPublicHoliday(String countryCode) throws IOException, InterruptedException {
        final String pathName = "IsTodayPublicHoliday";
        HttpResponse<String> response = getResponse(pathName + "/" + countryCode);
        return response.statusCode() == 200;
    }

    public List<HashMap<String, Object>> getNextPublicHolidays(List<String> necessaryKeys, String countryCode) throws IOException, InterruptedException {
        String pathName;
        HttpResponse<String> response;
        if (countryCode.equals("WORLDWIDE")) {
            pathName = "NextPublicHolidaysWorldwide";
            response = getResponse(pathName);
        } else {
            pathName = "NextPublicHolidays";
            response = getResponse(pathName + "/" + countryCode);
        }
        List<HashMap<String, Object>> read = JsonPath.parse(response.body()).read("$[*]");
        return filterMapsByNecessaryKeys(read, necessaryKeys);

    }


    public List<HashMap<String, Object>> getPublicHolidays(String year, List<String> necessaryKeys, String countryCode) throws IOException, InterruptedException {
        String pathName ="PublicHolidays";
        HttpResponse<String> response = getResponse(pathName + "/" + year + "/" + countryCode);
        List<HashMap<String, Object>> read = JsonPath.parse(response.body()).read("$[*]");
        return filterMapsByNecessaryKeys(read, necessaryKeys);
    }


    public HttpResponse<String> getResponse(String path) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + path))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404) throw new IllegalArgumentException("Not Found");
        return response;
    }



    public List<HashMap<String, Object>> filterMapsByNecessaryKeys(List<HashMap<String, Object>> read, List<String> necessaryKeys) {
        var result = new ArrayList<HashMap<String, Object>>();
        for (HashMap<String, Object> mapFromRead : read) {
            var map = new HashMap<String, Object>();
            for (String necessaryKey : necessaryKeys) {
                if (mapFromRead.containsKey(necessaryKey)) {
                    map.put(necessaryKey, mapFromRead.get(necessaryKey));
                }
            }
            result.add(map);
        }
        return result;
    }

}
