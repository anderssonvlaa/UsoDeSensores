package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnProximidad, btnLuminosidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProximidad = findViewById(R.id.btnProximidad);
        btnLuminosidad = findViewById(R.id.btnLuminosidad);

        btnProximidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevaVentana = new Intent(view.getContext(), SensorProximidad.class);
                startActivity(nuevaVentana);
            }
        });
        btnLuminosidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevaVentana1 = new Intent(view.getContext(), SensorLuminosidad.class);
                startActivity(nuevaVentana1);
            }
        });
    }

}
