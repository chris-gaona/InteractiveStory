package com.chrisgaona.interactivestory.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chrisgaona.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize variables attached to ui
        nameField = (EditText) findViewById(R.id.nameEditText);
        startButton = (Button) findViewById(R.id.startButton);

        // on click of start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the name from the nameField
                String name = nameField.getText().toString();
                // call startStory function & pass in name parameter
                startStory(name);
            }
        });
    }

    private void startStory(String name) {
        // create new intent for StoryActivity
        Intent intent = new Intent(this, StoryActivity.class);
        // access string resources from strings.xml
        Resources resources = getResources();
        // get a specific string resource & assign it as key
        String key = resources.getString(R.string.key_name);
        // add name as extra piece in intent
        intent.putExtra(key, name);
        // start the new activity
        startActivity(intent);
    }
}
