package com.slack.cunycodes.showtrack.Objects;

/**
 * Created by mofi on 10/19/16.
 */

public class Show {
    private int showID;
    private String showName;
    private String showDescription;
    private String[] showGenre;
    private String showType;
    private String showLanguage;
    private float showRating;
    private String showYear;

    private String showImageURL;
    private String timeString;
    private String showStatus;
    private int showRuntime;

    public Show(int showID, String showYear, String showName, String[] showGenre,
                String showLanguage, String showType, float showRating,
                String timeString, String showImageURL, String showDescription, String showStatus, int showRuntime) {
        this.showID = showID;
        this.showName = showName;
        this.showGenre = showGenre;

        this.showLanguage = showLanguage;
        this.showType = showType;
        this.showYear = showYear.split("-")[0];
        this.showRating = showRating;
        this.timeString = timeString;
        this.showImageURL = showImageURL;
        this.showDescription = stripHtml(showDescription);
        this.showStatus = showStatus;
        this.showRuntime = showRuntime;

    }

    public String getShowDescription() {
        return showDescription;
    }

    public String[] getShowGenre() {
        return showGenre;
    }

    public int getShowID() {
        return showID;
    }

    public String getShowImageURL() {
        return showImageURL;
    }

    public String getShowLanguage() {
        return showLanguage;
    }

    public String getShowName() {
        return showName;
    }

    public float getShowRating() {
        return showRating;
    }

    public String getShowType() {
        return showType;
    }

    public String getShowYear() {
        return showYear;
    }

    public String getTime() {
        return timeString;
    }

    private String stripHtml(String html) {
        return android.text.Html.fromHtml(html).toString();
    }

    public String getShowStatus() {
        return showStatus;
    }

    public int getShowRuntime() {
        return showRuntime;
    }

}
