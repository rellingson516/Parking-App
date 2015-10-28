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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ChoosePassActivity extends Activity {

    private final String FILENAME = "save.txt";
    Spinner passes;
    String pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pass);
        passes = (Spinner)findViewById(R.id.passSelect);
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
        nextPage();
    }

    private void getPersistentData() throws IOException {
        Context context = getBaseContext();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(in));
            pass = reader.readLine();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void nextPage() {
        Intent intent = new Intent(getApplicationContext(), SavePassActivity.class);
        intent.putExtra("pass", pass);
        startActivity(intent);
    }


}
