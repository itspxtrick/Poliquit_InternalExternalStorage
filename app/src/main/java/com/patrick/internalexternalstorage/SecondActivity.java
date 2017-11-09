package com.patrick.internalexternalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    TextView tv_Data;
    FileInputStream fis;
    BufferedReader br;
    Button btn_Shared, btn_IS, btn_IC, btn_EC, btn_ES, btn_EPS, btn_Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_Data = findViewById(R.id.tvData);
        btn_Shared = findViewById(R.id.btnSP);
        btn_IS = findViewById(R.id.btnIS);
        btn_IC = findViewById(R.id.btnIC);
        btn_EC = findViewById(R.id.btnEC);
        btn_ES = findViewById(R.id.btnES);
        btn_EPS = findViewById(R.id.btnEPS);
        btn_Next = findViewById(R.id.btnNext);

    }

    public void previous (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadSharedPreference(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String data = preferences.getString("data","");
        tv_Data.setText(data);
    }

    public void loadInternalStorage (View view) throws IOException {
        String newline = "";
        String data = "";
        try{
            fis = openFileInput("storage.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Data.setText(data);
    }

    public void loadInternalCache(View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getCacheDir();
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Data.setText(data);
    }

    public void loadExternalCache(View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getExternalCacheDir();
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Data.setText(data);
    }

    public void loadExternalStorage (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getExternalFilesDir("Patrick");
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Data.setText(data);
    }

    public void loadExternalPublic (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File (folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Data.setText(data);

    }
}
