/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.ipublish.web.tag;

import TwitterAPI.JfreeChat;
import TwitterAPI.SentiAnalysis;
import TwitterAPI.TweetEntities;
import TwitterAPI.TwitterAPI;
import client.Bridge;
import client.Ipublish;
import conferenceentities.Coreconf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author lingjunqiu
 */
public class outputTag extends TagSupport{
    
    @Override
    public int doStartTag() throws JspException{
        
        HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
        JspWriter out = this.pageContext.getOut();
        
        Bridge bridge = new Bridge();
        Ipublish ipublish = new Ipublish();
          String str = request.getParameter("keyword");
            String searchBy = request.getParameter("searchBy");
            ArrayList<Coreconf> records = null;
            StringBuilder buf = new StringBuilder();
        
            if (searchBy.equals("title")) {
                   
                records = bridge.getByTitle(str);
            } else {
                records = bridge.getByAcronym(str);
            }
        
             
             
             buf.append("<table border=\"1\">");
             buf.append("<tr><th>CONF_ID</th>");
             buf.append("<th>CONF_TITLE</th>");
             buf.append("<th>Acronym</th>");
             buf.append("<th>Rank</th>");
             buf.append("<th>Changed</th>");
             buf.append("<th>Forcode</th></tr>");
                    
            for (Coreconf coreconf : records) {
                String confId = Integer.toString(coreconf.getConfId());
                String confTitle = coreconf.getConfTitle();
                String acronym = coreconf.getAcronym();
                String rank = coreconf.getRank();
                String changed = coreconf.getChanged().toString();
                String forcode = coreconf.getForcode();
                if(changed.equals("false"))
                    changed = "No";
                else
                    changed = "Yes";
                       
                    buf.append("<tr><td>").append(confId);
                    buf.append("</td><td>").append(confTitle);
                    buf.append("</td><td>").append(acronym);
                    buf.append("</td><td>").append(rank);
                    buf.append("</td><td>").append(changed);
                    buf.append("</td><td>").append(forcode);
            }             
                    buf.append("</td></tr></table>");
           
                    
           //out the twitter content. 
            TwitterAPI twitterAPI = new TwitterAPI();
            StringBuilder bufTwitter = new StringBuilder();
            try {
                ArrayList<TweetEntities> tweets = new ArrayList<TweetEntities>();
                tweets = twitterAPI.tweets(str);
                SentiAnalysis sa = new SentiAnalysis();
                for(TweetEntities tweet:tweets)
                {

                    String username = tweet.getUsername();
                    String text = tweet.getText(); 
                    String profileURL = tweet.getProfileURL();
                    //System.out.println(text);
                    bufTwitter.append(text);
                    sa.sentimentAnaysis(text); 
                }
                int ptweet = sa.getPtweet();
                int ntweet = sa.getNtweet();
                int neutweet = sa.getNeutweet();
                JfreeChat jfreeChat = new JfreeChat();
                jfreeChat.pie(ptweet, ntweet, neutweet);
            } catch (Exception ex) {
                Logger.getLogger(outputTag.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        try {
            //out.println(buf);
            out.println(bufTwitter);
        } catch (IOException ex) {
            System.out.println("sorry");
        }
                    ipublish.close();  

        
        return super.doStartTag();
        
    }
}
