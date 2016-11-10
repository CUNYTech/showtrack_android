package com.slack.cunycodes.showtrack.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.slack.cunycodes.showtrack.App.AppConfig;
import com.slack.cunycodes.showtrack.R;
import com.squareup.picasso.Picasso;

public class ShowDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_detail);
        String showName;
        String showDesc;
        float showRating;
        String showImageUrl;
        String showYear;

        if (savedInstanceState == null) {
            Bundle data = getIntent().getExtras();
            if (data == null) {
                showName = null;
                showDesc = null;
                showImageUrl = null;
                showRating = 0;
                showYear = null;

            } else {
                showName = data.getString(AppConfig.SHOW_DETAIL_NAME);
                showDesc = data.getString(AppConfig.SHOW_DETAIL_SHOWDESP);
                showImageUrl = data.getString(AppConfig.SHOW_DETAIL_IMAGE_URL);
                showRating = data.getFloat(AppConfig.SHOW_DETAIL_SHOW_RATING);
                showYear = data.getString(AppConfig.SHOW_DETAIL_SHOW_YEAR);
            }
        } else {
            showName = null;
            showDesc = null;
            showImageUrl = null;
            showRating = 0;
            showYear = null;
        }

        //Toast .makeText(this, showName + String.valueOf(showID) + showImageUrl, Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.desp);
        TextView showNameView = (TextView) findViewById(R.id.showNameView);
        TextView rating = (TextView) findViewById(R.id.rating);
        ImageView iv = (ImageView) findViewById(R.id.imageView2);

        //Show name
        showNameView.setText(showName);

        //Show Poster
        Picasso.with(this).load(showImageUrl).into(iv);

        //Show Description
        tv.setText(showDesc);
        tv.setMovementMethod(new ScrollingMovementMethod());

        //Show Rating
        rating.setText(String.valueOf("Rating: " + showRating + "\n"));
        rating.setText(String.valueOf("Year: " + showYear));


    }
}

