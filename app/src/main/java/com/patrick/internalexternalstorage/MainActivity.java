package com.patrick.internalexternalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_Data, et_Filename;
    Button btn_Shared, btn_IS, btn_IC, btn_EC, btn_ES, btn_EPS, btn_Next;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Data = findViewById(R.id.etData);
        et_Filename = findViewById(R.id.etFilename);
        btn_Shared = findViewById(R.id.btnSP);
        btn_IS = findViewById(R.id.btnIS);
        btn_IC = findViewById(R.id.btnIC);
        btn_EC = findViewById(R.id.btnEC);
        btn_ES = findViewById(R.id.btnES);
        btn_EPS = findViewById(R.id.btnEPS);
        btn_Next = findViewById(R.id.btnNext);
    }

    public void next (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void saveSharedPreference (View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", et_Data.getText().toString());
        editor.commit();
        Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStorage (View view) {
        String message = et_Data.getText().toString();
        String filename = et_Filename.getText().toString();
        try {
            fos = openFileOutput(filename + ".txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Storage saved!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalCache(View view){
        String filename = et_Filename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, filename + ".txt");
    }
}
