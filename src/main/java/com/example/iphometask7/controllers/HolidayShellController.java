package com.example.iphometask7.controllers;

import com.example.iphometask7.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ShellComponent
public class HolidayShellController {
    private final HolidayService holidayService;

    @Autowired
    public HolidayShellController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @ShellMethod(key = "isToday", value = "Check is today public holiday")
    public String isTodayPublicHoliday(@ShellOption("--code") String countryCode) throws IOException, InterruptedException {
        boolean is = holidayService.checkIsTodayPublicHoliday(countryCode);
        return is? "Today public holiday :)": "Today is not public holiday :(";
    }

    @ShellMethod(key = "next", value = "Returns the upcoming public holidays for the next 365 days for the given country or 7 for worldwide")
    public List<HashMap<String, Object>> nextPublicHolidays(
            @ShellOption("--code") String countryCode) throws IOException, InterruptedException {
        List<String> necessaryKeys = new ArrayList<>(List.of("date", "localName"));
        return holidayService.getNextPublicHolidays( necessaryKeys, countryCode);
    }

    @ShellMethod(key = "all", value = "Returns all holidays for a given country in a given year.")
    public List<HashMap<String, Object>> publicHolidays(
            @ShellOption("--year") String year,
            @ShellOption("--code") @Size(min = 2, max = 2) String countryCode
    ) throws IOException, InterruptedException {
        List<String> necessaryKeys = new ArrayList<>(List.of("date", "localName"));
        return holidayService.getPublicHolidays(year, necessaryKeys, countryCode);
    }
}
