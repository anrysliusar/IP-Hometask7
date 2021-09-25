package com.example.iphometask7.repositories;

import com.example.iphometask7.models.Holiday;

import java.io.IOException;
import java.util.List;

public interface IHolidayRepo {
    boolean checkIsTodayPublicHoliday(String countryCode) throws IOException, InterruptedException;
    List<Holiday> findNextPublicHolidays(String countryCode) throws IOException, InterruptedException ;
    List<Holiday> findPublicHolidays(String year, String countryCode) throws IOException, InterruptedException ;

}
