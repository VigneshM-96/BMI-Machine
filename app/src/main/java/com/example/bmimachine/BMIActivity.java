package com.example.bmimachine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class BMIActivity extends AppCompatActivity {

    LinearLayout layoutInput, layoutResult;
    EditText etHeight, etWeight;
    TextView tvBMIValue, tvBMIStatus;
    ImageView btnBack;
    MaterialButton btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmiactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔹 Initialize Views
        layoutInput = findViewById(R.id.layoutInput);
        layoutResult = findViewById(R.id.layoutResult);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        tvBMIValue = findViewById(R.id.tvBMIValue);
        tvBMIStatus = findViewById(R.id.tvBMIStatus);
        btnBack = findViewById(R.id.btnBack);
        btnCalc = findViewById(R.id.btnCalc);

        // 🔹 Calculate BMI
        btnCalc.setOnClickListener(v -> calculateBMI());

        // 🔹 Back to Input UI
        btnBack.setOnClickListener(v -> {
            layoutResult.setVisibility(View.GONE);
            layoutInput.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.GONE);
        });
    }

    private void calculateBMI() {

        if (etHeight.getText().toString().isEmpty() ||
                etWeight.getText().toString().isEmpty()) {
            return;
        }

        double height = Double.parseDouble(etHeight.getText().toString());
        double weight = Double.parseDouble(etWeight.getText().toString());

        double bmi = weight / (height * height);

        tvBMIValue.setText(String.format("%.2f", bmi));

        if (bmi < 18.5) {
            tvBMIStatus.setText("Underweight");
        } else if (bmi < 25) {
            tvBMIStatus.setText("Healthy Weight");
        } else if (bmi < 30) {
            tvBMIStatus.setText("Overweight");
        } else {
            tvBMIStatus.setText("Obese");
        }

        // 🔹 Switch Card Appearance
        layoutInput.setVisibility(View.GONE);
        layoutResult.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
    }
}
