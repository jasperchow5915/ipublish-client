<%-- 
    Document   : another
    Created on : Mar 23, 2014, 8:11:56 PM
    Author     : lingjunqiu
--%>

<%@page import="TwitterAPI.JfreeChat"%>
<%@page import="TwitterAPI.SentiAnalysis"%>
<%@page import="TwitterAPI.TweetEntities"%>
<%@page import="TwitterAPI.TwitterAPI"%>
<%@page import="GoogleAPI.GsearchEntities"%>
<%@page import="java.util.ArrayList"%>
<%@page import="GoogleAPI.GoogleSearch"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Result</title>
    </head>
        <style>
        
        body{margin-left: 10px;margin-right: 10px;margin-top: 0px;}
        #masthead1 {text-align: center; background:#006699;margin-bottom: 10px}
        #masthead1 h1 {font-size:25px; margin:0 auto; padding:8px 0; color:yellow;}
        #masthead2 {text-align: center; background:#006699; margin-top:10px;clear: left}
        #masthead2 h1 {font-size:20px; margin:0 auto; padding:8px 0; color:#fff;}
        #masthead3 {text-align: center; background:#006699; margin-top:10px;}
        #masthead3 h1 {font-size:20px; margin:0 auto; padding:8px 0; color:#fff;}
        #tb1 th{text-align: left;background-color: #FF9933}
        #tb2 th{text-align: left;background-color: #FF9933}
        #tb2 tr:hover{background-color:#FF9933}
        #tb2 td a{text-decoration:none}
        #tb2wraper{overflow-y: auto; height: 300px; float: left;width:60%;}
        #img{float: left; width:40%; height: 300px;}
        #tb3wraper{text-align: left;overflow: auto; float: left;width:50%;}
        #tb3 th{text-align: left;background-color: #FF9933}
        #tb3{border-color: black}
        #tb4wraper{text-align: left; float: left;width:25%;max-height: 300px;overflow:auto}
        #tb5wraper{text-align: left;float: left;width:25%;clear: right;}
        #googleOuter{margin-top: 10px}
        #tweetOuter{margin-top: 10px;margin-bottom: 40px;}
        #foo{background-color: cornsilk}
    </style>
   
    <div id="container">
    <body>
        
        <%
            String query=request.getParameter("acronym");
            String year=request.getParameter("year");
            String confTitle=request.getParameter("title");
            String rank=request.getParameter("rank");
            String changed=request.getParameter("changed");
            String forcode=request.getParameter("forcode");
        %>
        <div id="masthead1"><h1>Full Title: <%=confTitle%></h1></div>
         <table id='tb1' border="1" cellpadding="0" cellspacing="0" >
            <tr>
                <th>Acronym</th>
                <th>Rank</th>
                <th>Changed</th>
                <th>Forcode</th>
            </tr>
            <tr> 
                <td><%=query%></td>
                <td><%=rank%></td>
                <td><%=changed%></td>
                <td><%=forcode%></td>
            </tr>
            </table>
     
        <div id="masthead2"><h1>Google Custome Search Result for "<%=query%>,<%=year%>"</h1></div>
         
         <%
            GoogleSearch googleSearch = new GoogleSearch();
            GsearchEntities gsearch = new GsearchEntities();
            try {
                gsearch = googleSearch.gsearch(query, year);
                String link = gsearch.getLink();
                String snippet = gsearch.getSnippet();
                String endDate = gsearch.getEndDate();
                String location = gsearch.getLocation();
                ArrayList<String> summarys = gsearch.getSummarys();
                ArrayList<String> startDates = gsearch.getStartDates();
                %>
                <div id="googleOuter">
                <div id="tb3wraper"  > 
                    <table border="1" id="tb3" cellpadding="0" cellspacing="0" > 
                         <tr>
                             <th>Summary </th>
                             <th>Start Time</th>
                         </tr>
                <%
                for(int i=0;i<summarys.size();i++){
                    String summary = summarys.get(i);
                    String startDate = startDates.get(i);
                    %>
                    
                         <tr>
                             <td colspan="1.2"><%=summary%>: </td>
                             <td><%=startDate%></td>
                         </tr>  
                <%
                }
                    String locationURL = googleSearch.gsearchByImg(location);
                    String[] cityDes = googleSearch.gsearchByCity(location);
        %>
                        <tr>
                            <td>EndDate: </td>
                            <td><%=endDate%></td>
                         </tr>
                          <tr>
                              <td>Location: </td>
                              <td><%=location%></td>
                         </tr>
                          <tr>
                              <td>Description: </td>
                              <td><a><%=snippet%></a><a href="<%=link%>">more</a></td>
                         </tr>
                     </table>
                    </div>
                     
                     <div id="tb4wraper" > 
                     <img src="<%=locationURL%>"/>
                    </div>
                     <div id="tb5wraper">
                         <span style="font-size: 18px"><b><i>City Description: </i></b></span>   <%=cityDes[0]%> <a href="<%=cityDes[1]%>">more</a>
                     </div>
                </div>
        <%
            } catch (RuntimeException e) {
                out.write("<font color=#ad9ca6>"+e.getMessage()+"</font>");
            }
        %>
             <hr/>
            <br/>
            <div id="masthead2"><h1> Relevant Tweets for "<%=query%>"</h1></div>

            <%        
           //out the twitter content. 
            TwitterAPI twitterAPI = new TwitterAPI();
            
                ArrayList<TweetEntities> tweets = new ArrayList<TweetEntities>();
                try{
                tweets = twitterAPI.tweets(query);
                  
                SentiAnalysis sa = new SentiAnalysis();
              %>
              <div id="tweetOuter">
              <div id="tb2wraper" > 
               <table border="1" id="tb2" cellpadding="0" cellspacing="0" >  
                     <tr>
                     <th colspan="2">Username</th>
                     <th>CreateAt</th>
                     <th>content</th>
                     </tr>
                 
              <%
            
                for(TweetEntities tweet:tweets)
                {
                    String text = tweet.getText();   
                    String username = tweet.getUsername();
                    String profileURL = tweet.getProfileURL();
                    String createAt = tweet.getCreatedAt();
                    %>
                    <tr>
                         <td>
                        <img src="<%=profileURL%>">
                        </td>
                        <td><%=username%></td>
                        <td><%=createAt%></td>
                        <td><%=text%></td>
                    </tr>
                   
         <%
                    sa.sentimentAnaysis(text);
                }
                int ptweet = sa.getPtweet();
                int ntweet = sa.getNtweet();
                int neutweet = sa.getNeutweet();
                JfreeChat jfreeChat = new JfreeChat();
                jfreeChat.pie(ptweet, ntweet, neutweet);
     
                Thread.currentThread().sleep(2000);
        
            %>
        </table>
              </div>
        <img src="images/sentiAnalysis.png" id="img">
              </div>
   
        <%
                }catch(RuntimeException e){
                   out.write("<font color=#ad9ca6>"+e.getMessage()+"for <strong>"+query+"</strong></font>");
                }
        %>
        &nbsp;
        &nbsp;
        &nbsp;
        &nbsp;
        &nbsp;
        
    </body>
     </div>
</html>
