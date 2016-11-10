package com.slack.cunycodes.showtrack.UI.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.slack.cunycodes.showtrack.App.AppConfig;
import com.slack.cunycodes.showtrack.App.AppController;
import com.slack.cunycodes.showtrack.Helper.Utility;
import com.slack.cunycodes.showtrack.Objects.Show;
import com.slack.cunycodes.showtrack.Objects.Time;
import com.slack.cunycodes.showtrack.R;
import com.slack.cunycodes.showtrack.UI.Activities.ShowDetailActivity;
import com.slack.cunycodes.showtrack.UI.Adapter.ShowAdapter;

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
    private ShowAdapter mAdapter;
    private ListView listView;
    private ArrayList<Show> mShowList;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mArrayList = new ArrayList<>();
        mShowList = new ArrayList<>();
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
        listView = (ListView) mRootView.findViewById(R.id.list_view);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                onSearchButtonClick();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ShowDetailActivity.class);
                intent.putExtra(AppConfig.SHOW_DETAIL_NAME, mShowList.get(i).getShowName());
                intent.putExtra(AppConfig.SHOW_DETAIL_IMAGE_URL, mShowList.get(i).getShowImageURL());
                intent.putExtra(AppConfig.SHOW_DETAIL_SHOWID, mShowList.get(i).getShowID());
                intent.putExtra(AppConfig.SHOW_DETAIL_SHOWDESP, mShowList.get(i).getShowDescription());
                intent.putExtra(AppConfig.SHOW_DETAIL_SHOW_GENRE, mShowList.get(i).getShowGenre());
                intent.putExtra(AppConfig.SHOW_DETAIL_SHOW_RATING, mShowList.get(i).getShowRating());
                intent.putExtra(AppConfig.SHOW_DETAIL_SHOW_YEAR, mShowList.get(i).getShowYear());
                startActivity(intent);
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

    private void onSearchButtonClick() {
        searchQuery.setAlpha(.1f);
        searchQuery.clearFocus();
        String text = searchQuery.getText().toString().trim();
        searchQuery.setText("");
        if (!text.isEmpty()) {
            if(mAdapter!=null) {
                mAdapter.clear();
            }
            text = Utility.replaceSpaces(text);
            String showURL = AppConfig.URL_SEARCH_ALL_NAME + text;
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
            showDialog();
            JsonArrayRequest jsonRequest = new JsonArrayRequest(
                    showURL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray array) {
                            JSONfileProcessing(array);
                            hideDialog();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "some thing fishy", Toast.LENGTH_LONG).show();
                            hideDialog();
                        }
                    }
            );
            AppController.getInstance().addToRequestQueue(jsonRequest, AppConfig.JSON_OBJECT_REQ);

        } else {
            Toast.makeText(getContext(), "No Query", Toast.LENGTH_LONG).show();
        }
    }

    private void JSONfileProcessing(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject show = array.getJSONObject(i).getJSONObject("show");
                String showName = show.getString("name");

                int showID = show.getInt("id");
                String showDescription = show.getString("summary");

                JSONArray genres = show.getJSONArray("genres");
                String[] showGenre;
                if(genres.length() > 0){
                    showGenre = new String[genres.length()];
                    for (int j = 0; j < genres.length(); j++) {
                        showGenre[j] = genres.getString(j);
                    }
                }else{
                    showGenre = new String[]{null};
                }

                String showType = show.getString("type");
                String showLanguage = show.getString("language");

//                float showRating = ((show.getJSONObject("rating").getJSONObject("average")) == null)?-1.0f:
//                        (float)show.getJSONObject("rating").getDouble("average");

                Object rating = show.getJSONObject("rating").optDouble("average");

                double showRating;
                if(rating != null){
                    showRating = (double)rating;
                }else{
                    showRating = -1f;
                }

                String showYear = show.getString("premiered");
                String showImageURL = show.getJSONObject("image").getString("original");
                String timeString = show.getJSONObject("schedule").getString("time");

                Time time;
                if(timeString.equals("") || timeString == null) {
                    time = null;
                }else{
                    time = new Time(timeString);
                }

                String showTime = time!=null?time.toString():"unknown";
                //TODO: Change code to create appropriate show object for using in view

                Show currentShow = new Show(
                        showID,showYear,showName,showGenre,showLanguage,
                        showType,(float)showRating,showTime,showImageURL,showDescription
                );
                mShowList.add(currentShow);
                mArrayList.add(showName);
            }
            if (!mArrayList.isEmpty()) {
                //TODO: Create appropirate Adapter class (Look into cardview and grid view)
                mAdapter = new ShowAdapter(getContext(), mShowList);
                listView.setAdapter(mAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
