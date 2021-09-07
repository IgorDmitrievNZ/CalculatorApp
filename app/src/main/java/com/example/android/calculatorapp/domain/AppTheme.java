package com.example.android.calculatorapp.domain;

import com.example.android.calculatorapp.R;

public enum AppTheme {
    DARK(R.style.AppTheme2, "dark"),
    LIGHT(R.style.AppTheme, "light");

    private int theme;
    private String key;

    AppTheme(int value, String key) {
        this.theme = value;
        this.key = key;
    }

    public int getTheme() {
        return theme;
    }

    public String getKey() {
        return key;
    }
}
