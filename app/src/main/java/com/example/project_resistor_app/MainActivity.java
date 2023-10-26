package com.example.project_resistor_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        String[] bandEntries = {"Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Gray", "White"};

        // Set up adapters for the three spinners using the custom adapter
        ArrayAdapter<String> adapter = new ColoredSpinnerAdapter(this, android.R.layout.simple_spinner_item, bandEntries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor1.setAdapter(adapter);
        spinnerColor2.setAdapter(adapter);
        spinnerColor3.setAdapter(adapter);

        // set up the "Clear" button
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearValues();
            }
        });
    }


    public void onCalculate(View view) {
        // Implement the calculation logic here
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

    private void clearValues() {
        // Clear the selected values and result
        spinnerColor1.setSelection(0);
        spinnerColor2.setSelection(0);
        spinnerColor3.setSelection(0);
        tvResult.setText("Result: ");
    }
}
