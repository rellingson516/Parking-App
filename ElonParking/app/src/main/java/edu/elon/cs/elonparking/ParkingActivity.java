package edu.elon.cs.elonparking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ParkingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
    }

    public void onSelectDirections(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

}
