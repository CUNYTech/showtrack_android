package com.slack.cunycodes.showtrack.App;

/**
 * Created by mofi on 9/11/16.
 */
public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://starter-dev2.us-east-1.elasticbeanstalk.com/api/v1/accounts/login/";

    // Server user register url
    public static String URL_REGISTER = "http://starter-dev2.us-east-1.elasticbeanstalk.com/api/v1/accounts/register/";

    public static String URL_SEARCH_ALL_NAME ="http://starter-dev2.us-east-1.elasticbeanstalk.com/api/v2/search/";


    public static String URL_SEARCH_INDIVIDUAL_NAME = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/single/";

    public static String URL_SEARCH_ALL_ID = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/";

    public static String URL_SEARCH_SHOW_EPISODE = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/";

    public static String JSON_OBJECT_REQ = "json_obj_req";

    public static String JSON_ARRAY_REQ = "json_arr_req";

    public static String JSON_STRING_REQ = "json_str_req";

    public static String JSON_IMAGE_REQ = "json_str_req";
    /*
    TODO: Finish adding all other variables.
     */

    public static String SHOW_DETAIL_NAME = "showName";

    public static String SHOW_DETAIL_SHOWID = "showID";

    public static String SHOW_DETAIL_SHOWDESP = "showDesp";

    public static String SHOW_DETAIL_IMAGE_URL = "imageURL";



    public static String SHOW_DETAIL_SHOW_GENRE = "showGenre";

    public static String SHOW_DETAIL_SHOW_LANG = "showLang";

    public static String SHOW_DETAIL_SHOW_RATING = "showRating";

    public static String SHOW_DETAIL_SHOW_YEAR = "showYear";

    public static String SHOW_DETAIL_SHOW_TIME = "showTime";

    public static String SHOW_DETAIL_SHOW_RUNTIME = "showRunTime";

    public static String SHOW_DETAIL_SHOW_STATUS = "showStatus";

    public static String SHOW_DETAIL_SHOW_TYPE = "showType";




    /*

    /*
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

     */

    /*

    http://dango.us-east-1.elasticbeanstalk.com/api/v2/search/:showname
    http://dango.us-east-1.elasticbeanstalk.com/api/v2/single/:showname
    http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/:id
    http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/:id/episodes


     */
}
