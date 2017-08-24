package com.chrisgaona.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chrisgaona.interactivestory.R;
import com.chrisgaona.interactivestory.model.Page;
import com.chrisgaona.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // initialize variables attached to ui
        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);

        // access intent
        Intent intent = getIntent();
        // assign Key: name's value to name variable passed from MainActivity
        name = intent.getStringExtra(getString(R.string.key_name));
        // if no name was passed in
        if (name == null || name.isEmpty()) {
            // make name default to Friend
            name = "Friend";
        }
        Log.d(TAG, name);

        // initialize a new Story in onCreate of activity
        mStory = new Story();
        // start with page 0
        loadPage(0);
    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);

        final Page page = mStory.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        // add name if placeholder is included. Won't add if not
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);

        if (page.isFinalPage()) {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);

            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    // call finish() to remove all the stacks & go back to MainActivity
//                    finish();
                    loadPage(0);
                }
            });
        } else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        // make sure buttons are visible
        choice1Button.setVisibility(View.VISIBLE);
        choice2Button.setVisibility(View.VISIBLE);

        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // walk through of the below:
        // on page 1
        // but started on page 0

        // this will pop page 1 from our stack leaving page 0...not empty
        pageStack.pop();

        if (pageStack.isEmpty()) {
            super.onBackPressed();
        } else {
            // this second pop will remove page 0...reloads page 0
            loadPage(pageStack.pop());
        }
    }
}
