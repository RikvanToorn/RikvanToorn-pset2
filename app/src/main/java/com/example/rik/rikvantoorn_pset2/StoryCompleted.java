package com.example.rik.rikvantoorn_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Rik on 11-11-2016.
 */

public class StoryCompleted extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_layout);

        Bundle story = getIntent().getExtras();
        String completedstory = story.getString("story");
        TextView theStory = (TextView) findViewById(R.id.theStory);
        theStory.setText(completedstory);
    }

    public void back(View view) {
        Intent goToInput = new Intent(this, MainActivity.class);

        final int result = 1;

        startActivity(goToInput);
        finish();
    }
}
