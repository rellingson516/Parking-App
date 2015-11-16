package edu.elon.cs.elonparking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;

public class ParkingActivity extends Activity {

    ParkingDB db;
    String building;
    String pass;

    String lot;


    TextView parkingText;
    TextView lotText;
    TextView visitorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        db = new ParkingDB(getBaseContext());
        parkingText = (TextView) findViewById(R.id.parkingText);
        lotText = (TextView) findViewById(R.id.lotText);
        visitorText = (TextView) findViewById(R.id.visitorText);
        Intent intent = getIntent();
        building = intent.getStringExtra("building");
        pass = intent.getStringExtra("pass");
        lot = db.findClosestLot(building, pass);
        setText();
    }

    public void onSelectDirections(View view) {
        String latAndLong = "google.navigation:q=";
        GPSLocation loc = db.getLotLocation(lot);
        latAndLong += loc.getLatitude() + "," + loc.getLongitude();
        Uri uri = Uri.parse(latAndLong);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setData(Uri.parse(latAndLong));
        Intent chooser = Intent.createChooser(intent, "Launch Maps");
        startActivity(intent);
    }

    private void setText() {
        String park = "";
        String visitor = "";
        if(lot.length() == 0) {
            park = "There are no lots available for you to park in right now";
            Button b = (Button)findViewById(R.id.directions);
            b.setVisibility(View.GONE);
        } else {
            park = "The closest available lot is";
        }

        if(pass.equals("Visitor")) {
            visitor = "(Visitor parking only)";
        }

        parkingText.setText(park);
        lotText.setText(lot);
        visitorText.setText(visitor);

    }

}
