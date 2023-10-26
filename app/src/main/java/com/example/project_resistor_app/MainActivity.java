package com.example.project_resistor_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerColor1, spinnerColor2, spinnerColor3;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerColor1 = findViewById(R.id.spinnerColor1);
        spinnerColor2 = findViewById(R.id.spinnerColor2);
        spinnerColor3 = findViewById(R.id.spinnerColor3);
        tvResult = findViewById(R.id.tvResult);

        String[] bandEntries = {"Band 1", "Band 2", "Band 3"};

        // Set up adapters for the three spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bandEntries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor1.setAdapter(adapter);
        spinnerColor2.setAdapter(adapter);
        spinnerColor3.setAdapter(adapter);
    }

    public void onCalculate(View view) {
        int value = calculateResistorValue(
                spinnerColor1.getSelectedItemPosition(),
                spinnerColor2.getSelectedItemPosition(),
                spinnerColor3.getSelectedItemPosition()
        );

        // Update the result TextView
        String resultText = "Resistance: " + value + " Ohms";
        tvResult.setText(resultText);
    }

    private int calculateResistorValue(int band1, int band2, int band3) {
        // E12 series resistor values (in ohms)
        int[] e12Series = {
                10, 12, 15, 18, 22, 27, 33, 39, 47, 56, 68, 82
        };

        // Calculate the resistance value
        int resistance = (e12Series[band1] * 10 + e12Series[band2]) * (int) Math.pow(10, band3);

        return resistance;
    }
}
