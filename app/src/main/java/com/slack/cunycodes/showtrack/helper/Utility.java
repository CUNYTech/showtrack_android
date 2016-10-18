package com.slack.cunycodes.showtrack.Helper;

import android.graphics.drawable.GradientDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.slack.cunycodes.showtrack.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mofi on 9/6/16.
 */
public class Utility {
    public static boolean validatePassword(String str) {
        //this is not necessary because we will check for this before calling the method
        if (!(str.length() > 0)) {
            return false;
        }

        //Password must be at least 8 character long
        boolean hasAtLeastEight = str.length() >= 8;

        //Password needs at least one uppercase letter
        boolean hasUpper = !str.equals(str.toLowerCase());

        //Password needs at least one lowercase letter
        boolean hasLower = !str.equals(str.toUpperCase());

        //password needs at least one special character
        boolean hasSpecial = !str.matches("[A-Za-z0-9]");

        return hasAtLeastEight && hasUpper && hasLower && hasSpecial;
    }

    public static String getHashPassword(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] byteOfMessage = str.getBytes("UTF-8");
            byte[] digest = md.digest(byteOfMessage);
            result = new String(digest, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void customView(View v, int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(R.dimen.shape_radius);
        shape.setColor(backgroundColor);
        v.setBackground(shape);
    }

    public static String[]  decoded(String JWTEncoded) throws Exception {
        String[] info = new String[2];
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            String userJSON = getJson(split[1]);
            JSONObject user = new JSONObject(userJSON);

            info[0] = user.getString("username");
            info[1] = user.getString("email");
        } catch (UnsupportedEncodingException e) {
            //Error
        }
        return info;
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.DEFAULT);
        return new String(decodedBytes, "UTF-8");
    }
}
