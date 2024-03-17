package com.example.tp2;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Exercice5 extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean flashOn = false;
    private boolean shakeDetected = false;
    private long lastShakeTime = 0;
    private static final int SHAKE_THRESHOLD = 800; // in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice5);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastShakeTime) > SHAKE_THRESHOLD) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
            if (acceleration > 2) {
                if (!shakeDetected) {
                    toggleFlash();
                    vibrate();
                    shakeDetected = true;
                }
                lastShakeTime = currentTime;
            }
        } else {
            shakeDetected = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void toggleFlash() {
        try {
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                if (flashOn) {
                    cameraManager.setTorchMode(cameraId, false);
                    flashOn = false;
                } else {
                    cameraManager.setTorchMode(cameraId, true);
                    flashOn = true;
                }
            } else {
                Toast.makeText(this, "Flash non disponible", Toast.LENGTH_SHORT).show();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(200);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void startExercise6(View view) {
        Intent intent = new Intent(this, Exercice6.class);
        startActivity(intent);
    }
}
