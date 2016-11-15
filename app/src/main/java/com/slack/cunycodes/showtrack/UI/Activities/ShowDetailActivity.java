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
        String showYr;

        String ShowLang;
        String type;
        String status;
        int runtime;

        if (savedInstanceState == null) {
            Bundle data = getIntent().getExtras();
            if (data == null) {
                showName = null;
                showDesc = null;
                showImageUrl = null;
                showRating = 0;
                showYr = null;

                ShowLang = null;
                type = null;
                status = null;
                runtime = 0;
            } else {
                showName = data.getString(AppConfig.SHOW_DETAIL_NAME);
                showDesc = data.getString(AppConfig.SHOW_DETAIL_SHOWDESP);
                showImageUrl = data.getString(AppConfig.SHOW_DETAIL_IMAGE_URL);
                showRating = data.getFloat(AppConfig.SHOW_DETAIL_SHOW_RATING);
                showYr = data.getString(AppConfig.SHOW_DETAIL_SHOW_YEAR);

                ShowLang = data.getString(AppConfig.SHOW_DETAIL_SHOW_LANG);
                type = data.getString(AppConfig.SHOW_DETAIL_SHOW_TYPE);
                status = data.getString(AppConfig.SHOW_DETAIL_SHOW_STATUS);
                runtime = data.getInt(AppConfig.SHOW_DETAIL_SHOW_RUNTIME);
            }
        } else {
            showName = null;
            showDesc = null;
            showImageUrl = null;
            showRating = 0;
            showYr = null;

            ShowLang = null;
            type = null;
            status = null;
            runtime = 0;
        }

        //Toast .makeText(this, showName + String.valueOf(showID) + showImageUrl, Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.desp);
        TextView showNameView = (TextView) findViewById(R.id.showNameView);
        TextView rating = (TextView) findViewById(R.id.rating);
        ImageView iv = (ImageView) findViewById(R.id.poster);
        TextView ShowYear = (TextView) findViewById(R.id.year);

        TextView showLanguage = (TextView) findViewById(R.id.lang);
        TextView ShowType = (TextView) findViewById(R.id.showType);
        TextView showStatus = (TextView) findViewById(R.id.showStat);
        TextView showRuntime = (TextView) findViewById(R.id.ShowRuntime);

        //Show name
        showNameView.setMovementMethod(new ScrollingMovementMethod());

        //Show Poster
        Picasso.with(this).load(showImageUrl).into(iv);

        //Show Description
        tv.setText(showDesc);
        tv.setMovementMethod(new ScrollingMovementMethod());

        //Show Rating
        if (showRating > 0) {
            rating.setText(String.valueOf("Rating: " + showRating + "\n"));
        } else {
            rating.setText("No rating available");
        }

        //Show Year
        ShowYear.setText(String.valueOf("Year: " + showYr));

        //Show Language
        if (ShowLang == null) {
            showLanguage.setText("No language available");
        } else {
            showLanguage.setText(String.valueOf("Language: " + ShowLang));
        }

        //Show Type
        ShowType.setText(String.valueOf("Type: " + type));

        //Show Status
        showStatus.setText(String.valueOf("Status: " + status));

        //Show Runtime
        showRuntime.setText(String.valueOf("Runtime: " + runtime));

    }
}

