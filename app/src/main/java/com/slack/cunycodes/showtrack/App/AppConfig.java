package com.slack.cunycodes.showtrack.App;

/**
 * Created by mofi on 9/11/16.
 */
public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://dango.us-east-1.elasticbeanstalk.com/api/v1/accounts/login/";

    // Server user register url
    public static String URL_REGISTER = "http://dango.us-east-1.elasticbeanstalk.com/api/v1/accounts/register/";


    public static String URL_SEARCH_ALL_NAME = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/search/";


    public static String URL_SEARCH_INDIVIDUAL_NAME = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/single/";


    public static String URL_SEARCH_ALL_ID = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/";


    public static String URL_SEARCH_SHOW_EPISODE = "http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/";



    /*

    http://dango.us-east-1.elasticbeanstalk.com/api/v2/search/:showname
    http://dango.us-east-1.elasticbeanstalk.com/api/v2/single/:showname
    http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/:id
    http://dango.us-east-1.elasticbeanstalk.com/api/v2/show/:id/episodes


     */
}
