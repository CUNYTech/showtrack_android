package com.slack.cunycodes.showtrack.UI;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.slack.cunycodes.showtrack.App.AppConfig;
import com.slack.cunycodes.showtrack.Helper.Utility;
import com.slack.cunycodes.showtrack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private View mRootView;
    private ProgressDialog pDialog;
    private EditText searchQuery;
    private ImageButton searchButton;
    private ArrayList<String> mArrayList;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mArrayList = new ArrayList<>();
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Collecting Awesomeness");
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_search, container, false);
        searchQuery = (EditText) mRootView.findViewById(R.id.search_text_box);
        searchButton = (ImageButton) mRootView.findViewById(R.id.search_image_button);

        searchQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchQuery.setAlpha(1.0f);
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                onSearchButtonClick();
            }
        });


        return mRootView;
    }

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }



    private void onSearchButtonClick(){
        searchQuery.setAlpha(.1f);
        searchQuery.clearFocus();
        String text = searchQuery.getText().toString().trim();
        searchQuery.setText("");
        if(!text.isEmpty()){
            mArrayList.clear();
            text = Utility.replaceSpaces(text);
            String showURL = AppConfig.URL_SEARCH_ALL_NAME + text;
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
            showDialog();
            final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            JsonArrayRequest jsonRequest = new JsonArrayRequest(
                    showURL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray array) {
                            JSONfileProcessing(array);
                            hideDialog();
                            requestQueue.stop();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "some thing fishy", Toast.LENGTH_LONG).show();
                            hideDialog();
                            requestQueue.stop();
                        }
                    }
            );
            requestQueue.add(jsonRequest);

        }else{
            Toast.makeText(getContext(), "No Query", Toast.LENGTH_LONG).show();
        }
    }

    private void JSONfileProcessing(JSONArray array){
        try {
            for(int i = 0; i< array.length(); i++){
                JSONObject show = array.getJSONObject(i).getJSONObject("show");
                String showName = show.getString("name");

                //TODO: Change code to create appropriate show object for using in view


                mArrayList.add(showName);
            }
            if(!mArrayList.isEmpty()) {
                //TODO: Create appropirate Adapter class (Look into cardview and grid view)


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mArrayList);
                ListView listView = (ListView) mRootView.findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
