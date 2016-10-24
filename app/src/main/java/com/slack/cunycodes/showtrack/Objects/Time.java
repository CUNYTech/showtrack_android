package com.slack.cunycodes.showtrack.Objects;

/**
 * Created by mofi on 10/19/16.
 */

public class Time {
    private String time;

    public Time(String time) {
        this.setTime(time);
    }

    public void setTime(String time) {
        String timeModifier = "";
        StringBuilder sb = new StringBuilder();

        String[] splice = time.split(":");
        int hour = Integer.parseInt(splice[0]);

        if(hour > 12 ){
            hour = hour % 12;
            timeModifier = "PM";
        } else{
            timeModifier = "AM";
        }

        sb.append(String.valueOf(hour)+":"+splice[1]+" "+
                timeModifier);


        this.time = sb.toString();
    }

    @Override
    public String toString() {
        return time;
    }
}
