package com.example.iphometask7.service;

import java.lang.String;

import com.example.iphometask7.dto.HolidayDTO;
import com.example.iphometask7.mappers.CustomMapper;
import com.example.iphometask7.models.Holiday;
import com.example.iphometask7.repositories.IHolidayRepo;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HolidayServiceImpl implements IHolidayService {
    private final IHolidayRepo holidayRepo;

    @Autowired
    public HolidayServiceImpl(IHolidayRepo holidayRepo) {
        this.holidayRepo = holidayRepo;
    }

    @Override
    public boolean isTodayPublicHoliday(String countryCode) throws IOException, InterruptedException {
        return holidayRepo.checkIsTodayPublicHoliday(countryCode);
    }

    @Override
    public List<HolidayDTO> getNextPublicHolidays(String countryCode) throws IOException, InterruptedException {
        List<Holiday> nextPublicHolidays = holidayRepo.findNextPublicHolidays(countryCode);
        List<HolidayDTO> holidayDTOList = new ArrayList<>();
        for (Holiday nextPublicHoliday : nextPublicHolidays) {
            HolidayDTO map = CustomMapper.map(nextPublicHoliday, HolidayDTO.class);
            holidayDTOList.add(map);
        }
        return holidayDTOList;
    }

    @Override
    public List<HolidayDTO> getPublicHolidays(String year, String countryCode) throws IOException, InterruptedException {
        List<Holiday> publicHolidays = holidayRepo.findPublicHolidays(year, countryCode);
        List<HolidayDTO> holidayDTOList = new ArrayList<>();
        for (Holiday publicHoliday : publicHolidays) {
            HolidayDTO map = CustomMapper.map(publicHoliday, HolidayDTO.class);
            holidayDTOList.add(map);
        }
        return holidayDTOList;
    }
}
