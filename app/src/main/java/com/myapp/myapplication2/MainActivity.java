package com.myapp.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public AutoCompleteTextView autoCompleteTextView;
    public List<nasaInfo> searchList;
    public String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.actv);
        searchList = new ArrayList<>();

        nasaInfo temp = new nasaInfo("Search for something", "");
        List<nasaInfo> current = new ArrayList<>();
        current.add(temp);
        AutoCompleteCustomAdapter adapter = new AutoCompleteCustomAdapter(this, current);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "i"+i+",l"+l+",view"+view, Toast.LENGTH_SHORT).show();
            }
        });
    }
}