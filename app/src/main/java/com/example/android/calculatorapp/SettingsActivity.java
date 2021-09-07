package com.example.android.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.calculatorapp.domain.AppTheme;
import com.example.android.calculatorapp.storage.ThemeStorage;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ThemeStorage storage = new ThemeStorage(this);

        setTheme(storage.getAppTheme().getTheme());

        setContentView(R.layout.activity_settigs);

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.theme_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storage.setAppTheme(AppTheme.DARK);
                recreate();
            }
        });

        findViewById(R.id.theme2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storage.setAppTheme(AppTheme.LIGHT);
                recreate();
            }
        });
    }
}
