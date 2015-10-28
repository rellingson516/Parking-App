package edu.elon.cs.elonparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class SavePassActivity extends Activity {

    private final String FILENAME = "save.txt";

    String pass;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_pass);

        intent = new Intent(getApplicationContext(),ChooseBuildingActivity.class);
    }

    public void onSavePass(View view) {
        try {
            putPersistentData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nextPage();
    }

    public void onDenySavePass(View view) {
        nextPage();
    }

    private void nextPage() {
        intent.putExtra("pass", pass);
        startActivity(intent);
    }



    private void putPersistentData() throws IOException {
        Context context = getBaseContext();
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(pass);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
