package com.example.androiduitesting;

import androidx.annotation.NonNull;

public class City {
    private final String cityName;

    public City(String cityName) {
        this.cityName = cityName == null ? "" : cityName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    @NonNull
    @Override
    public String toString() {
        // ArrayAdapter will use this to display list rows
        return cityName;
    }
}

