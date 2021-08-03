package com.example.temperaturecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_temperature;
    Button btn_calc;
    RadioButton rbtn_celsius;
    RadioButton rbtn_fahrenheite;
    TextView tv_answer;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_temperature = findViewById(R.id.et_temperature);
        rbtn_celsius = findViewById(R.id.rbtn_celsius);
        rbtn_fahrenheite = findViewById(R.id.rbtn_fahrenheite);
        btn_calc= findViewById(R.id.btn_calc);
        tv_answer= findViewById(R.id.tv_answer);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }
    private void calculateAnswer() {
        Calculations calculations = new Calculations();
        String temp_value = et_temperature.getText().toString();

        if (TextUtils.isEmpty(temp_value)) {
            Toast.makeText(this, "Please add a value", Toast.LENGTH_LONG).show();
            temp_value = "0.0";
            tv_answer.setText(temp_value);
        } else {
            try {
                Float temp = Float.parseFloat(temp_value);

                if (rbtn_celsius.isChecked()) {
                    temp = calculations.convertCelciusToFahrenheit(temp);
                } else if (rbtn_fahrenheite.isChecked()) {
                    temp = calculations.convertFahrenheitToCelcius(temp);
                } else {
                    Toast.makeText(this, "Please select Radio button", Toast.LENGTH_LONG).show();
                    temp = 0.0f;
                }
                tv_answer.setText(new Float(temp).toString());
            }catch(NullPointerException e){}
        }
    }
}