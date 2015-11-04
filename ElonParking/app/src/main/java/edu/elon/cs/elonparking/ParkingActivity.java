package edu.elon.cs.elonparking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class ParkingActivity extends Activity {

    ParkingDB db;
    String building;
    String pass;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        db = new ParkingDB(getBaseContext());
        Intent intent = getIntent();
        building = intent.getStringExtra("building");
        pass = intent.getStringExtra("pass");

    }

    public void onSelectDirections(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

}
