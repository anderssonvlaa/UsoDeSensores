package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SensorLuminosidad extends AppCompatActivity {
    Sensor miSensor;
    SensorManager administradorDeSensores;
    SensorEventListener disparadorEventoSensor;
    TextView lblvalor;
    Button btnvalor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luminosidad);
        lblvalor = (TextView) findViewById(R.id.lblValorLuminosidad);
        btnvalor = (Button) findViewById(R.id.btnValor);
        //Inicializar mi sensor

        administradorDeSensores = (SensorManager)
                getSystemService(SENSOR_SERVICE);
        miSensor =
                administradorDeSensores.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (miSensor == null) {
            Toast.makeText(this, "Su dispositivo no tiene el sensor de luminosidad", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Sensor de Luminosidad detectado",
                    Toast.LENGTH_LONG).show();
        }
        disparadorEventoSensor = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //Colocar el codigo de lo que queremos que haga nuestra app
                //cuando se acerque o se aleje el objeto del sensor
                lblvalor.setText("Valor del sensor: " + sensorEvent.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        iniciarSensor();

        btnvalor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT, lblvalor.getText().toString());

                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    ex.printStackTrace();

                    Snackbar.make(view, "El dispositivo no tiene instalado WhatsApp", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void iniciarSensor() {
        administradorDeSensores.registerListener(disparadorEventoSensor, miSensor, (2000 * 1000));
    }

    public void detenerSensor() {
        administradorDeSensores.unregisterListener(disparadorEventoSensor);
    }

    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }

    @Override
    protected void onResume() {
        iniciarSensor();
        super.onResume();
    }

}