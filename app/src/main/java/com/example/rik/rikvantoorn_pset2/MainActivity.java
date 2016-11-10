package com.example.rik.rikvantoorn_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToInput(View view) {
        Intent goToInput = new Intent(this, InputActivity.class);

        final int result = 1;

        startActivity(goToInput);
    }
}
