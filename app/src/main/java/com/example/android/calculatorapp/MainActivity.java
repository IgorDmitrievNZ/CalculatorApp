package com.example.android.calculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previous_text);
        display = findViewById(R.id.display_text);
        display.setShowSoftInputOnFocus(false);

        findViewById(R.id.key_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_0));
            }
        });

        findViewById(R.id.key_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_1));
            }
        });

        findViewById(R.id.key_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_2));
            }
        });

        findViewById(R.id.key_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_3));
            }
        });

        findViewById(R.id.key_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_4));
            }
        });

        findViewById(R.id.key_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_5));
            }
        });

        findViewById(R.id.key_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_6));
            }
        });

        findViewById(R.id.key_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_7));
            }
        });

        findViewById(R.id.key_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_8));
            }
        });

        findViewById(R.id.key_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_9));
            }
        });

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay(getResources().getString(R.string.key_dot));
            }
        });

        findViewById(R.id.key_ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText("");
                previousCalculation.setText("");
            }
        });

        findViewById(R.id.key_addition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(display.getText().length() == 0) && checkLastIsAction())
                    updateDisplay(getResources().getString(R.string.key_addition));
            }
        });

        findViewById(R.id.key_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.getText().length() == 0) {
                    updateDisplay(getResources().getString(R.string.key_subtract));
                } else if (!(display.getText().length() == 0) && checkLastIsAction())
                    updateDisplay(getResources().getString(R.string.key_subtract));
            }
        });

        findViewById(R.id.key_multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(display.getText().length() == 0) && checkLastIsAction())
                    updateDisplay(getResources().getString(R.string.key_multiply));
            }
        });

        findViewById(R.id.key_divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(display.getText().length() == 0) && checkLastIsAction())
                    updateDisplay(getResources().getString(R.string.key_divide));
            }
        });

        findViewById(R.id.key_equal).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                String userExp = display.getText().toString();

                previousCalculation.setText(userExp + getResources().getString(R.string.key_equal));

                userExp = userExp.replaceAll(getResources().getString(R.string.key_divide), "/");
                userExp = userExp.replaceAll(getResources().getString(R.string.key_multiply), "*");

                Expression expression = new Expression(userExp);
                String result = String.valueOf(expression.calculate());
                display.setText(result);
                display.setSelection(result.length());

            }
        });

        findViewById(R.id.parentheses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPos = display.getSelectionStart();
                int openPar = 0;
                int closedPar = 0;
                int textLength = display.getText().length();

                for (int i = 0; i < cursorPos; i++) {
                    if (display.getText().toString().substring(i, i + 1).equals("(")) {
                        openPar += 1;
                    }
                    if (display.getText().toString().substring(i, i + 1).equals(")")) {
                        closedPar += 1;
                    }
                }
                if (openPar == closedPar || display.getText().toString().substring(textLength - 1, textLength).equals("(")) {
                    updateDisplay("(");
                } else if (closedPar < openPar && !display.getText().toString().substring(textLength - 1, textLength).equals("(")) {
                    updateDisplay(")");
                }
                display.setSelection(cursorPos + 1);
            }
        });

        findViewById(R.id.square_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay("sqrt(");
            }
        });
    }

    private boolean checkLastIsAction() {
        char lastValue = display.getText().charAt(display.getText().length() - 1);

        switch (lastValue) {
            case '×':
            case '÷':
            case '+':
            case '-':
                return false;
            default:
                return true;
        }
    }

    private void updateDisplay(String str) {
        String oldString = display.getText().toString();

        int cursorPos = display.getSelectionStart();
        String leftStr = oldString.substring(0, cursorPos);
        String rightStr = oldString.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, str, rightStr));
        display.setSelection(cursorPos + str.length());
    }
}
