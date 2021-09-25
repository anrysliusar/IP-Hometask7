package com.example.iphometask7.models;

import java.util.List;

public class Holiday {
    public String date;
    public String localName;
    public String name;
    public String countryCode;
    public boolean fixed;
    public boolean global;
    public Object counties;
    public Object launchYear;
    public List<String> types;

    public Holiday() {
    }

    public Holiday(String date, String localName, String name, String countryCode, boolean fixed, boolean global, Object counties, Object launchYear, List<String> types) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.fixed = fixed;
        this.global = global;
        this.counties = counties;
        this.launchYear = launchYear;
        this.types = types;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Object getCounties() {
        return counties;
    }

    public void setCounties(Object counties) {
        this.counties = counties;
    }

    public Object getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Object launchYear) {
        this.launchYear = launchYear;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
