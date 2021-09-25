package com.example.iphometask7.controllers;

import com.example.iphometask7.dto.HolidayDTO;
import com.example.iphometask7.service.IHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

@ShellComponent
public class HolidayShellController {
    private final IHolidayService holidayService;

    @Autowired
    public HolidayShellController(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @ShellMethod(key = "isToday", value = "Check is today public holiday")
    public String isTodayPublicHoliday(@ShellOption("--code") String countryCode) throws IOException, InterruptedException {
        boolean is = holidayService.isTodayPublicHoliday(countryCode);
        return is? "Today public holiday :)": "Today is not public holiday :(";
    }

    @ShellMethod(key = "next", value = "Returns the upcoming public holidays for the next 365 days for the given country or 7 for worldwide")
    public List<HolidayDTO> nextPublicHolidays(
            @ShellOption("--code") String countryCode) throws IOException, InterruptedException {
        return holidayService.getNextPublicHolidays(countryCode);
    }

    @ShellMethod(key = "all", value = "Returns all holidays for a given country in a given year.")
    public List<HolidayDTO> publicHolidays(
            @ShellOption("--year") String year,
            @ShellOption("--code") @Size(min = 2, max = 2) String countryCode
    ) throws IOException, InterruptedException {
        return holidayService.getPublicHolidays(year, countryCode);
    }
}
