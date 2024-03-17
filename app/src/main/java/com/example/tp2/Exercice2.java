package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercice2 extends AppCompatActivity {

    private TextView textMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView sensorListView = findViewById(R.id.sensor_list_view);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        SensorListAdapter adapter = new SensorListAdapter(this, sensorList);
        sensorListView.setAdapter(adapter);
    }


    private static class SensorListAdapter extends ArrayAdapter<Sensor> {
        private final Context mContext;
        private final List<Sensor> mSensorList;

        public SensorListAdapter(Context context, List<Sensor> sensorList) {
            super(context, R.layout.list_item_sensor, sensorList);
            mContext = context;
            mSensorList = sensorList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item_sensor, null);
            }

            TextView sensorNameTextView = view.findViewById(R.id.sensor_name_text_view);
            TextView sensorStatusTextView = view.findViewById(R.id.sensor_status_text_view);

            Sensor sensor = mSensorList.get(position);
            if (sensor != null) {
                sensorNameTextView.setText(sensor.getName());
                sensorNameTextView.setTextColor(Color.GREEN);
                sensorStatusTextView.setText("Présent");
                sensorStatusTextView.setTextColor(Color.GREEN);
            } else {
                sensorNameTextView.setText("Capteur inconnu");
                sensorNameTextView.setTextColor(Color.RED);
                sensorStatusTextView.setText("Absent");
                sensorStatusTextView.setTextColor(Color.RED);
            }

            return view;
        }
    }
    public void startExercise3(View view) {
        Intent intent = new Intent(this, Exercice3.class);
        startActivity(intent);
    }
}