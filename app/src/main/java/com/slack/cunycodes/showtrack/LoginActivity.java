package com.slack.cunycodes.showtrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.slack.cunycodes.showtrack.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private EditText userName;
    private EditText passWord;
    private Button login;
    private CheckBox remember;
    private TextView register;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.txtUserNameLogin);
        passWord = (EditText) findViewById(R.id.txtPasswordLogin);
        login = (Button) findViewById(R.id.btnLogin);
        remember = (CheckBox) findViewById(R.id.chkRemember);
        register = (TextView) findViewById(R.id.txtRegisterNow);

        Utility.customView(login, ContextCompat.getColor(this, R.color.color_button));
        session = new SessionManager(getApplicationContext());

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging In");

        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Login Clicked", Toast.LENGTH_SHORT).show();
//                String username = userName.getText().toString().toLowerCase().trim();
//                String pass = passWord.getText().toString().trim();
//
//                String dataType = "login";
//                JSONObject post_dict = new JSONObject();
//
//                try {
//                    post_dict.put("username" , username);
//                    post_dict.put("password", pass);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                if (!username.isEmpty() && !pass.isEmpty()) {
//                    BackgroundWorker bg = new BackgroundWorker(getApplicationContext());
//                    try {
//                        String jsonStr = bg.execute(dataType, post_dict.toString()).get();
//                        JSONObject jObj = new JSONObject(jsonStr);
//                        String error = jObj.optString("non_field_errors");
//                        String errorString = "Unable to login with provided credentials.";
//
//                        // Check for error node in json
//                        if (!error.equals(errorString)) {
//                            // user successfully logged in
//                            // Create login session
//                            String token = jObj.getString("token");
//                            session.setLogin(true);
//                            session.setToken(token);
//
//                            String s = session.getToken();
//                            // Now store the user in SQLite
//
//                            int x=1;
//
//
//
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//
//                        } else {
//                            // Error in login. Get the error message
//                            String errorMsg = jObj.getString("error-message");
//                            Toast.makeText(getApplicationContext(),
//                                    errorMsg, Toast.LENGTH_LONG).show();
//                        }
//                    } catch (InterruptedException | ExecutionException | JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    // Prompt user to enter credentials
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter the credentials!", Toast.LENGTH_LONG)
//                            .show();
//                }
                showDialog();
                String username = userName.getText().toString().toLowerCase().trim();
                String pass = passWord.getText().toString().trim();

                JSONObject post_dict = new JSONObject();

                try {
                    post_dict.put("username", username);
                    post_dict.put("password", pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (username.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    final RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            AppConfig.URL_LOGIN,
                            post_dict,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {
                                        String token = response.getString("token");
                                        session.setLogin(true);
                                        session.setToken(token);
                                        hideDialog();
                                        requestQueue.stop();
                                        Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                                        startActivity(intent);
                                        finish();


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    hideDialog();
                                    requestQueue.stop();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "some thing fishy", Toast.LENGTH_LONG).show();
                                    hideDialog();
                                    requestQueue.stop();
                                }
                            }
                    );
                    requestQueue.add(jsonRequest);
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
}
