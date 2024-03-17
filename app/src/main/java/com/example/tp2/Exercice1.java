package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Exercice1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView sensorListView = findViewById(R.id.sensor_list_view);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorNames = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            sensorNames.add(sensor.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sensorNames);
        sensorListView.setAdapter(adapter);
    }
    public void startExercise2(View view) {
        Intent intent = new Intent(this, Exercice2.class);
        startActivity(intent);
    }


}