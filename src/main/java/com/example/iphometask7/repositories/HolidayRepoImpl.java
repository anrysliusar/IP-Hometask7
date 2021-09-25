package com.example.iphometask7.repositories;

import com.example.iphometask7.models.Holiday;
import com.example.iphometask7.models.CountryCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class HolidayRepoImpl implements IHolidayRepo{
    public final String URL = "https://date.nager.at/api/v2/";

    public boolean checkIsTodayPublicHoliday(String countryCode) throws IOException, InterruptedException {
        final String pathName = "IsTodayPublicHoliday";
        HttpResponse<String> response = getResponse(pathName + "/" + countryCode);
        return response.statusCode() == 200;
    }

    public List<Holiday> findNextPublicHolidays(String countryCode) throws IOException, InterruptedException {
        String pathName;
        HttpResponse<String> response;
        if (countryCode.equals(CountryCode.WORLDWIDE.toString())) {
            pathName = "NextPublicHolidaysWorldwide";
            response = getResponse(pathName);
        } else {
            pathName = "NextPublicHolidays";
            response = getResponse(pathName + "/" + countryCode);
        }
        List<HashMap<String, Object>> read = JsonPath.parse(response.body()).read("$[*]");


        return listMapToHoliday(read);

    }

    public List<Holiday> findPublicHolidays(String year, String countryCode) throws IOException, InterruptedException {
        String pathName ="PublicHolidays";
        HttpResponse<String> response = getResponse(pathName + "/" + year + "/" + countryCode);
        List<HashMap<String, Object>> read = JsonPath.parse(response.body()).read("$[*]");
        return listMapToHoliday(read);
    }


    private HttpResponse<String> getResponse(String path) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + path))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404) throw new IllegalArgumentException("Not Found");
        return response;
    }

    private List<Holiday> listMapToHoliday(List<HashMap<String, Object>> hashMapList){
        final ObjectMapper mapper = new ObjectMapper();
        List<Holiday> holidays = new ArrayList<>();
        for (HashMap<String, Object> map : hashMapList) {
            if (map.containsKey("type")){
                Object type = map.remove("type");
                map.put("types", List.of(type));
            }
            Holiday pojo = mapper.convertValue(map, Holiday.class);
            holidays.add(pojo);
        }
        return  holidays;
    }
}


