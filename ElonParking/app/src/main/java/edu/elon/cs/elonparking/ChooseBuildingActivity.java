package edu.elon.cs.elonparking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;

public class ChooseBuildingActivity extends Activity {

    String pass;
    String building;
    Spinner buildings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_building);

    }

    public void onSelectBuilding(View view) {
        Intent intent = new Intent(getApplicationContext(), ParkingActivity.class);
        intent.putExtra("pass", pass);
        intent.putExtra("building", building);
        startActivity(intent);
    }

}
