package com.example.iphometask7.controllers;

import com.example.iphometask7.dto.HolidayDTO;
import com.example.iphometask7.service.IHolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/holiday")
public class HolidayRestController {
    private final IHolidayService holidayService;

    @Autowired
    public HolidayRestController(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }


    @Operation(summary = "Check is today public holiday")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The check was successful", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Invalid code", content = @Content)
    })
    @GetMapping("/isToday")
    public String isTodayPublicHoliday(@RequestParam("code") String countryCode) throws IOException, InterruptedException {
        boolean is = holidayService.isTodayPublicHoliday(countryCode);
        return is ? "Today public holiday :)" : "Today is not public holiday :(";
    }

    @Operation(summary = "Returns the upcoming public holidays for the next 365 days for the given country or 7 for worldwide")
    @ApiResponse(responseCode = "200", description = "upcoming public holidays were returned", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Not Available", content = @Content)
    @GetMapping("/next")
    public List<HolidayDTO> nextPublicHolidays(@RequestParam("code") String countryCode) throws IOException, InterruptedException {
        return holidayService.getNextPublicHolidays(countryCode);
    }


    @Operation(summary = "Returns all holidays for a given country in a given year.")
    @ApiResponse(responseCode = "200", description = "all holidays for a given country in a given year were returned", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Not Available", content = @Content)
    @GetMapping("/all")
    public List<HolidayDTO> publicHolidays(
            @RequestParam("year") String year,
            @RequestParam("code") String countryCode
    ) throws IOException, InterruptedException {
        return holidayService.getPublicHolidays(year, countryCode);
    }

}
