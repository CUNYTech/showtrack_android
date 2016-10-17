package com.slack.cunycodes.showtrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.slack.cunycodes.showtrack.helper.SessionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        final SessionManager sessionManager = new SessionManager(getApplicationContext());

        boolean loggedIn = sessionManager.isLoggedIn();
        String token = sessionManager.getToken();
        textView.setText(token);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }

            private void logout() {
                sessionManager.setLogin(false);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
