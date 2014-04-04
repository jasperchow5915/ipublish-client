/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package TwitterAPI;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import org.netbeans.saas.RestConnection;

/**
 *
 * @author lingjunqiu
 */
public class TwitterAPI {
    
    public static final String CONSUMER_KEY = "2hWF1lnnlLpJoqQIgd3bYA"; 
    public static final String CONSUMER_SECRET = "hicZ3EuPpStpRytr18HqZJzxCSZtgGr3QnRVa5g7A4";
    public static final String OAUTH_TOKEN = "843525348-eSvunGt9yT7JdCjcuTh1NclWXiE3oJKsKYNMaCtF";
    public static final String OAUTH_TOKEN_SECRET = "aLgqbAWkTFpXulPKrgW5BNXV04cLApENjk5ouJGOJZB4R";
   

    public ArrayList<TweetEntities> tweets(String query) throws Exception {
        OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
        //String query = "ubicomp";
        query = query.replaceAll("[ /&]","%20");
        ParseTweets parseTweets = new ParseTweets();
        ArrayList<TweetEntities> tweets;

        String URL="https://api.twitter.com/1.1/search/tweets.json?q=" + query +"&count=20&lang=en"; 
       // System.out.println("--->" + consumer.sign(URL));
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        RestConnection conn = new RestConnection(consumer.sign(URL), pathParams, queryParams); 
        String response = conn.get().getDataAsString();
        parseTweets.parse(response);
        tweets = parseTweets.getTweets();
      
        return tweets;
    }
    
   
}

