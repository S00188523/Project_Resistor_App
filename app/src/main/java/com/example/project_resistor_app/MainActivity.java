package com.example.project_resistor_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_resistor_app.R;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerColor1, spinnerColor2, spinnerColor3, spinnerColor4, spinnerColor5;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerColor1 = findViewById(R.id.spinnerColor1);
        spinnerColor2 = findViewById(R.id.spinnerColor2);
        spinnerColor3 = findViewById(R.id.spinnerColor3);
        spinnerColor4 = findViewById(R.id.spinnerColor4);
        spinnerColor5 = findViewById(R.id.spinnerColor5);
        tvResult = findViewById(R.id.tvResult);

        // Set up adapters for spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_options_band1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor1.setAdapter(adapter);
        spinnerColor2.setAdapter(adapter);
        spinnerColor3.setAdapter(adapter);
        spinnerColor4.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.color_options_band5, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor5.setAdapter(adapter);
    }

    public void onCalculate(View view) {
        // Implement the calculation logic here
        int value = calculateResistorValue(
                spinnerColor1.getSelectedItemPosition(),
                spinnerColor2.getSelectedItemPosition(),
                spinnerColor3.getSelectedItemPosition(),
                spinnerColor4.getSelectedItemPosition(),
                spinnerColor5.getSelectedItemPosition()
        );

        // Update the result TextView
        tvResult.setText("Result: " + value + " Ohms");
    }

    private int calculateResistorValue(int band1, int band2, int band3, int band4, int band5) {
        // E12 series resistor values (in ohms)
        int[] e12Series = {
                10, 12, 15, 18, 22, 27, 33, 39, 47, 56, 68, 82
        };

        // Calculate the resistance value
        int resistance = (e12Series[band1] * 10 + e12Series[band2]) * (int) Math.pow(10, band3);

        // Apply the tolerance (band4) to the calculated resistance
        double tolerance;
        switch (band4) {
            case 0: tolerance = 0.01; break;  // 1%
            case 1: tolerance = 0.02; break;  // 2%
            case 2: tolerance = 0.005; break; // 0.5%
            case 3: tolerance = 0.0025; break;// 0.25%
            case 4: tolerance = 0.001; break; // 0.1%
            case 5: tolerance = 0.05; break;  // 5%
            case 6: tolerance = 0.1; break;   // 10%
            case 7: tolerance = 0.2; break;   // 20%
            case 8: tolerance = 0.3; break;   // 30%
            case 9: tolerance = 0.4; break;   // 40%
            default: tolerance = 0;
        }

        // Calculate the minimum and maximum resistance values with tolerance
        double minResistance = resistance * (1 - tolerance);
        double maxResistance = resistance * (1 + tolerance);

        // Display the result
        tvResult.setText("Resistance: " + resistance + " Ohms\n"
                + "Tolerance: " + (tolerance * 100) + "%\n"
                + "Range: " + minResistance + " Ohms - " + maxResistance + " Ohms");

        return resistance;
    }

}
