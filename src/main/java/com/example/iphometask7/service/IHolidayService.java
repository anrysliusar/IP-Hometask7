package com.example.iphometask7.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface IHolidayService {
    boolean checkIsTodayPublicHoliday(String countryCode) throws IOException, InterruptedException;
    List<HashMap<String, Object>> getNextPublicHolidays(List<String> necessaryKeys, String countryCode) throws IOException, InterruptedException ;
    List<HashMap<String, Object>> getPublicHolidays(String year, List<String> necessaryKeys, String countryCode) throws IOException, InterruptedException ;
}
