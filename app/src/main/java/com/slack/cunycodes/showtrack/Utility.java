package com.slack.cunycodes.showtrack;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

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
}
