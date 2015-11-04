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
        textView = (TextView) findViewById(R.id.parkingText);
        Intent intent = getIntent();
        building = intent.getStringExtra("building");
        pass = intent.getStringExtra("pass");
        setText();
    }

    public void onSelectDirections(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

    private void setText() {
        String text = "";
        String lot = db.findClosestLot(building,pass);
        if(lot.length() == 0) {
            text = "There are no lots available for you to park in right now";
        } else {
            text = "The closest available lot is\n" + lot;
        }

        if(pass.equals("Visitor")) {
            text += " \n(Visitor parking only)";
        }

        textView.setText(text);

    }

}
