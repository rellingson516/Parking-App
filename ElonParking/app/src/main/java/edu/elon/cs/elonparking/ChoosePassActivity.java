package edu.elon.cs.elonparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class ChoosePassActivity extends Activity {

    private final String FILENAME = "save.txt";
    Spinner passes;
    String pass = "";
    Spinner buildings;
    String building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pass);
        passes = (Spinner)findViewById(R.id.passSelect);
        buildings = (Spinner) findViewById(R.id.buildingSelect);
        try {
            getPersistentData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!pass.equals("")) {
            ArrayAdapter adapter = (ArrayAdapter) passes.getAdapter();
            int index = adapter.getPosition(pass);
            passes.setSelection(index);
        }
    }

    public void onSelectPass(View view) {
        pass = passes.getSelectedItem().toString();
        building = buildings.getSelectedItem().toString();
        nextPage();
    }

    private void getPersistentData() throws IOException {
        Context context = getBaseContext();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(in));
            pass = reader.readLine();
            System.out.print(pass);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void putPersistentData() throws IOException {
        Context context = getBaseContext();
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            System.out.print(pass);
            writer.write(pass);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void nextPage() {
        if(pass.equals("Select Pass")) {
            Toast.makeText(getApplicationContext(), "Please select a pass", Toast.LENGTH_SHORT).show();
        } else if(building.equals("Select Building")) {
            Toast.makeText(getApplicationContext(), "Please select a building", Toast.LENGTH_SHORT).show();
        } else {
            try {
                putPersistentData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getApplicationContext(), ParkingActivity.class);
            intent.putExtra("pass", pass);
            intent.putExtra("building", building);
            startActivity(intent);
        }
    }


}
