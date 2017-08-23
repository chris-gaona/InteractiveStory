package com.chrisgaona.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chrisgaona.interactivestory.R;
import com.chrisgaona.interactivestory.model.Page;
import com.chrisgaona.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));
        if (name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        mStory = new Story();
        loadPage(0);
    }

    private void loadPage(int pageNumber) {
        Page page = mStory.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);
    }
}
