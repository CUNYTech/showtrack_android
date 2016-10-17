package com.slack.cunycodes.showtrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.slack.cunycodes.showtrack.App.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText userName = (EditText) findViewById(R.id.txtUserNameRegister);
        final EditText password = (EditText) findViewById(R.id.txtPasswordRegister);
        final EditText email = (EditText) findViewById(R.id.txtEmailRegister);
        final Button register = (Button) findViewById(R.id.btnRegister);
        final EditText display = (EditText) findViewById(R.id.txtDisplayNameRegister);

        final TextView signup = (TextView) findViewById(R.id.txtSigninNow);

        Utility.customView(register, ContextCompat.getColor(this, R.color.color_button));


        final String[] dataType = {""};
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String nameuser = userName.getText().toString().toLowerCase().trim();
//                String emailAddr = email.getText().toString().toLowerCase().trim();
//                String pass = password.getText().toString().trim();
//                String displayName = display.getText().toString().trim();
//
//                JSONObject post_dict = new JSONObject();
//
//                try {
//                    post_dict.put("email",emailAddr);
//                    post_dict.put("username" , nameuser);
//                    post_dict.put("dispay_name",displayName);
//                    post_dict.put("password", pass);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                dataType[0] = "register";
//                if (!nameuser.isEmpty() && !emailAddr.isEmpty() && !pass.isEmpty()&& !displayName.isEmpty()) {
//                    BackgroundWorker bg = new BackgroundWorker(getApplicationContext());
//                    try {
//                        String jsonStr = bg.execute(dataType[0], post_dict.toString()).get();
////                        Log.d(LOG_TAG, jsonStr);
//                        JSONObject jObj = new JSONObject(jsonStr);
//                        boolean error = jObj.getBoolean("error");
//                        String token = jObj.optString("token");
//                        // Check for error node in json
//                        if (!token.isEmpty() || token != null) {
//                            // user successfully logged in
//                            // Create login session
//
//                            // Now store the user in SQLite
//
//
//                            // Inserting row in users table
//                            // Launch main activity
//                            Intent intent = new Intent(RegistrationActivity.this,
//                                    LoginActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            // Error in login. Get the error message
//                            String errorMsg = jObj.getString("error-reason");
//                            Toast.makeText(getApplicationContext(),
//                                    errorMsg, Toast.LENGTH_LONG).show();
//                        }
//                    } catch (InterruptedException | ExecutionException | JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    // Prompt user to enter credentials
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter the credentials!", Toast.LENGTH_LONG)
//                            .show();
//                }


                String nameuser = userName.getText().toString().toLowerCase().trim();
                String emailAddr = email.getText().toString().toLowerCase().trim();
                String pass = password.getText().toString().trim();
                String displayName = display.getText().toString().trim();

                JSONObject post_dict = new JSONObject();

                try {
                    post_dict.put("email", emailAddr);
                    post_dict.put("username", nameuser);
                    post_dict.put("display_name", displayName);
                    post_dict.put("password", pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(LOG_TAG, post_dict.toString());

                if (nameuser.isEmpty() || emailAddr.isEmpty() || pass.isEmpty() || displayName.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    final RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            AppConfig.URL_REGISTER,
                            post_dict,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                                    requestQueue.stop();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                    Log.d(LOG_TAG, error.toString());
                                    error.printStackTrace();
                                    requestQueue.stop();
                                }
                            }
                    );
                    requestQueue.add(jsonRequest);
                }

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
