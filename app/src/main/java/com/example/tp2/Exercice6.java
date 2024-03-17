package com.example.tp2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Exercice6 extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice6);

        imageView = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null) {
            // Gérer le cas où le capteur de proximité n'est pas disponible sur l'appareil
            imageView.setImageResource(R.drawable.indispo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float distance = event.values[0];
            if (distance < proximitySensor.getMaximumRange()) {
                // Objet proche
                imageView.setImageResource(R.drawable.near1);
            } else {
                // Objet loin
                imageView.setImageResource(R.drawable.away_logo);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Méthode non utilisée pour le capteur de proximité, mais doit être implémentée
    }
    public void startExercise7(View view) {
        Intent intent = new Intent(this, Exercice7.class);
        startActivity(intent);
    }
}
