package com.example.iphometask7.service;

import com.example.iphometask7.dto.HolidayDTO;
import com.example.iphometask7.models.Holiday;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface IHolidayService {
    boolean isTodayPublicHoliday(String countryCode) throws IOException, InterruptedException;
    List<HolidayDTO> getNextPublicHolidays(String countryCode) throws IOException, InterruptedException ;
    List<HolidayDTO> getPublicHolidays(String year, String countryCode) throws IOException, InterruptedException ;
}
