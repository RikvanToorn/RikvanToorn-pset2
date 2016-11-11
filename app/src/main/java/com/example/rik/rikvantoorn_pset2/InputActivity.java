package com.example.rik.rikvantoorn_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;

/**
 * Created by Rik on 8-11-2016.
 */

public class InputActivity extends Activity {

    private Story story;
    String word;
    int wordsLeft;
    // create the page and ask for the first word
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);

        InputStream stream = this.getResources().openRawResource(R.raw.madlib1_tarzan);
        story = new Story(stream);

        word = story.getNextPlaceholder();
        EditText field = (EditText) findViewById(R.id.words_input);
        field.setHint(word);
        int wordsLeft = story.getPlaceholderRemainingCount();
        TextView remainingWords = (TextView) findViewById(R.id.wordsLeft);
        remainingWords.setText(Integer.toString(wordsLeft) + " Words to complete.");
    }

    //function when the button is pressed
    public void insertWord(View view) {
        EditText next = (EditText) findViewById(R.id.words_input);
        String word = next.getText().toString();

        //checks if user atleast puts in something
        if (word.length() == 0) {
            Toast toast = Toast.makeText(this, "You can't enter nothing", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            story.fillInPlaceholder(word);
        }


        //Go to the show story page with the updated story
        if (story.isFilledIn()) {
            Intent completedStory = new Intent(this, StoryCompleted.class);
            completedStory.putExtra("story", story.toString());
            startActivity(completedStory);
            finish();
        }
        //if not all the words are filled in yet update everything and ask for new word
        else {
            wordsLeft = story.getPlaceholderRemainingCount();
            TextView remainingWords = (TextView) findViewById(R.id.wordsLeft);
            remainingWords.setText(Integer.toString(wordsLeft) + " Words to complete.");

            next.setText("");
            word = story.getNextPlaceholder();
            next.setHint(word);

            Toast toast = Toast.makeText(this, "Keep filling in words!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onSaveInstanceState(Bundle outState){

        // save all the data
        outState.putSerializable("story", story);
        outState.putString("next", word);
        outState.putInt("wordsleft", wordsLeft);

        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle inState){

        //get all the data from the save
        story = (Story)inState.getSerializable("story");
        word = inState.getString("next");
        EditText field = (EditText) findViewById(R.id.words_input);
        field.setHint(word);
        int wordsLeft = inState.getInt("wordsleft");
        TextView remainingWords = (TextView) findViewById(R.id.wordsLeft);
        remainingWords.setText(Integer.toString(wordsLeft) + " Words to complete.");

        super.onSaveInstanceState(inState);
    }

}
