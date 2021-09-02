package com.example.android.calculatorapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.calculatorapp.domain.AppTheme;

public class ThemeStorage {
    private static final String KEY_THEME = "KEY_THEME";
    private Context context;
    private SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("theme_storage", Context.MODE_PRIVATE);
    }

    public void setAppTheme(AppTheme appTheme) {
        sharedPreferences.edit()
                .putString(KEY_THEME, appTheme.getKey())
                .apply();
    }

    public AppTheme getAppTheme() {
        String value = sharedPreferences.getString(KEY_THEME, AppTheme.DARK.getKey());

        for (AppTheme theme : AppTheme.values()) {
            if (theme.getKey().equals(value)) {
                return theme;
            }
        }
        return AppTheme.DARK;
    }
}
