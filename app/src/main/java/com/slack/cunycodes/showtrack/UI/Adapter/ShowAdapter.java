package com.slack.cunycodes.showtrack.UI.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.slack.cunycodes.showtrack.App.AppController;
import com.slack.cunycodes.showtrack.Objects.Show;
import com.slack.cunycodes.showtrack.R;

import java.util.List;

/**
 * Created by mofi on 10/19/16.
 */

public class ShowAdapter extends ArrayAdapter<Show> {
    private Context mContext;
    public ShowAdapter(Context context, List<Show> objects) {
        super(context, 0, objects);
        mContext = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Show show = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.show_list_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.show_list_item_name);
        TextView year = (TextView) convertView.findViewById(R.id.show_list_item_year);
        TextView desc = (TextView) convertView.findViewById(R.id.show_list_item_desc);
        TextView language = (TextView) convertView.findViewById(R.id.show_list_item_language);
        TextView rating = (TextView) convertView.findViewById(R.id.show_list_item_rating);

        ImageView image = (ImageView) convertView.findViewById(R.id.show_list_item_image);

        String showName = show.getShowName();
        String showYear = show.getShowYear();
        String showDesc = show.getShowDescription();
        float rate = show.getShowRating();
        String showRating;
        if(rate > 0) {
            showRating = String.format("Rating %s/10", String.valueOf(rate));
        }else{
            showRating = "Unrated";
        }
        String showLanguage = show.getShowLanguage();
        String imageUrl = show.getShowImageURL();

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        // Loading image with placeholder and error image
        imageLoader.get(show.getShowImageURL(), ImageLoader.getImageListener(
                image, R.drawable.loading, R.drawable.error));


        if((mContext.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE){
            if(showName.length()>26){
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            }else if(showName.length()>13){
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
            }
        }else {
            if (showName.length() > 26) {
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            } else if (showName.length() > 13) {
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            }
        }



        name.setText(showName);
        year.setText(showYear);
        desc.setText(showDesc);
        language.setText(showLanguage);
        rating.setText(showRating);

//        TextView name = (TextView) convertView.findViewById(R.id.name_event);
//        TextView location = (TextView) convertView.findViewById(R.id.location_event);
//        TextView date = (TextView) convertView.findViewById(R.id.date_event);
//        ImageView bg = (ImageView) convertView.findViewById(R.id.bg_event);
//        name.setText(event.getName());
//        location.setText(event.getLocation());
//        date.setText(event.getDate());
//        bg.setImageResource(event.getBgImage());
        return convertView;
        //return super.getView(position, convertView, parent);
    }

}
