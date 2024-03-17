package com.example.tp2;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Exercice3 extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView accelerationValuesTextView;
    private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        layout = findViewById(R.id.layout);
        accelerationValuesTextView = findViewById(R.id.text_acceleration_values);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            String text = "X: " + x + "\nY: " + y + "\nZ: " + z;
            accelerationValuesTextView.setText(text);

            int color;
            if (Math.abs(x) < 3 && Math.abs(y) < 3 && Math.abs(z) < 3) {
                color = Color.GREEN; // Valeurs moyennes
            } else if (Math.abs(x) >= 3 || Math.abs(y) >= 3 || Math.abs(z) >= 3) {
                color = Color.RED; // Valeurs supérieures
            } else {
                color = Color.BLACK; // Valeurs inférieures
            }
            layout.setBackgroundColor(color);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void startExercise4(View view) {
        Intent intent = new Intent(this, Exercice4.class);
        startActivity(intent);
    }

}