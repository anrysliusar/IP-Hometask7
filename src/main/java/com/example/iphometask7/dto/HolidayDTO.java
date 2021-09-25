package com.example.iphometask7.dto;

public class HolidayDTO {
    private String localName;
    private String date;

    public HolidayDTO() {
    }

    public HolidayDTO(String localName, String date) {
        this.localName = localName;
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "HolidayDTO{" +
                "localName='" + localName + '\'' +
                ", date=" + date +
                '}';
    }
}
