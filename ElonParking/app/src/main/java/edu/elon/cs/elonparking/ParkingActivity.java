package edu.elon.cs.elonparking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ParkingActivity extends Activity {

    ParkingDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        db = new ParkingDB(getBaseContext());

    }

    public void onSelectDirections(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

}
