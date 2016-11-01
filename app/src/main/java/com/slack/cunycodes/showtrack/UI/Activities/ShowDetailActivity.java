package com.slack.cunycodes.showtrack.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.slack.cunycodes.showtrack.App.AppConfig;
import com.slack.cunycodes.showtrack.R;

public class ShowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);


        String showName;
        String showDesc;
        int showID;
        String showImageUrl;


        if (savedInstanceState == null) {
            Bundle data = getIntent().getExtras();
            if (data == null) {
                showName = null;
                showID = 0;
                showDesc = null;
                showImageUrl = null;
            } else {
                showName = data.getString(AppConfig.SHOW_DETAIL_NAME);
                showID = data.getInt(AppConfig.SHOW_DETAIL_SHOWID);
                showDesc = data.getString(AppConfig.SHOW_DETAIL_SHOWDESP);
                showImageUrl = data.getString(AppConfig.SHOW_DETAIL_IMAGE_URL);


            }
        } else {
            showName = null;
            showID = 0;
            showDesc = null;
            showImageUrl = null;
        }


        Toast.makeText(this, showName + String.valueOf(showID) + showImageUrl, Toast.LENGTH_SHORT).show();


        //TODO: Get rid of this
        TextView tv = (TextView) findViewById(R.id.tempTextView);


    }
}
