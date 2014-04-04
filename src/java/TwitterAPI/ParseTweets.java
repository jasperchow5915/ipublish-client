/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author lingjunqiu
 */
public class ParseTweets {
    
    private String text;
    private String createdAt;
    private String profileURL;
    private String username;
    private ArrayList<TweetEntities> tweets = new ArrayList<>();

    public void parse(String jsonData) throws FileNotFoundException {
        
        JsonParser parser = new JsonParser();
        
        JsonObject jsonObject = (JsonObject)parser.parse(jsonData);
        JsonArray statuses = (JsonArray)jsonObject.get("statuses");  
        if(statuses.size()==0){
            throw new RuntimeException("No Tweet results");
        }else{
        Iterator i = statuses.iterator();
        
        while(i.hasNext()){
            TweetEntities tweet = new TweetEntities();
            JsonObject statuse = (JsonObject) i.next();

            text = statuse.get("text").getAsString();
            tweet.setText(text);  
            createdAt = statuse.get("created_at").getAsString();
            tweet.setCreatedAt(createdAt);
            
            JsonObject user = (JsonObject) statuse.get("user"); 
            username = user.get("name").getAsString();
            tweet.setUsername(username);
            profileURL = user.get("profile_image_url_https").getAsString();
            tweet.setProfileURL(profileURL);
            tweets.add(tweet);
            }
            }
        }
    
     
    public ArrayList<TweetEntities> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<TweetEntities> tweets) {
        this.tweets = tweets;
    }



}
