<%-- 
    Document   : action
    Created on : Mar 16, 2014, 1:25:01 PM
    Author     : lingjunqiu
--%>
<%@page import="GoogleAPI.GsearchEntities"%>
<%@page import="GoogleAPI.GoogleSearch"%>
<%@page import="java.util.logging.Level"%>
<%@page import="TwitterAPI.JfreeChat"%>
<%@page import="TwitterAPI.SentiAnalysis"%>
<%@page import="TwitterAPI.TweetEntities"%>
<%@page import="TwitterAPI.TwitterAPI"%>
<%@page import="client.Bridge"%>
<%@page import="client.Ipublish"%>
<%@page import="java.util.*"%>
<%@page import="conferenceentities.Coreconf"%>
<%@taglib uri="/WEB-INF/tlds/ipublish" prefix="ipublish" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ipublish</title>

    </head>
    <style>
        body{margin-left: 10px;margin-right: 10px;margin-top: 0px;}

        #tb1record:hover{cursor: crosshair;}
        #masthead1 {text-align: center; background:#006699; margin-top:0px;}
        #masthead1 h1 {font-size:20px; margin:0 auto; padding:8px 0; color:#fff;}
        #masthead2 {text-align: center; background:#006699; margin-top:0px;clear: left}
        #masthead2 h1 {font-size:20px; margin:0 auto; padding:8px 0; color:#fff;}
        #masthead3 {text-align: center; background:#006699; margin-top:0px;}
        #masthead3 h1 {font-size:20px; margin:0 auto; padding:8px 0; color:#fff;}
        #tb1 th{text-align: left;background-color: #FF9933}
        #tb1 tr:hover{background-color:#FF9933}
        #tb1 td a{text-decoration:none}
        #tb2 th{text-align: left;background-color: #FF9933}
        #tb2 tr:hover{background-color:#FF9933}
        #tb2 td a{text-decoration:none}
        #tb1wraper{overflow-y: auto; height: 305px;}
        #tb2wraper{overflow-y: auto; height: 300px; float: left;width:60%;}
        #img{float: left; width:40%; height: 300px;}
        #tb3wraper{text-align: left;overflow: auto; float: left;width:50%;}
        #tb3 th{text-align: left;background-color: #FF9933}
        #tb3{border-color: black}
        #tb4wraper{text-align: left; float: left;width:25%;max-height: 300px;overflow:auto}
        #tb5wraper{text-align: left;float: left;width:25%;clear: right;}
        #googleOuter{margin-top: 10px}
        #tweetOuter{margin-top: 10px;margin-bottom: 40px;}
    </style>
    <body>      
        <%
            Bridge bridge = new Bridge();
            String str = request.getParameter("query");
            System.out.println("query: " + str);
            String searchBy = request.getParameter("searchBy");
            String year = request.getParameter("year");
            ArrayList<Coreconf> records = null;

            if (searchBy.equals("all") || str.equals("")) {
                records = bridge.getAll();
            } else if (searchBy.equals("title")) {
                records = bridge.getByTitle(str);
            } else if (searchBy.equals("acronym")) {
                records = bridge.getByAcronym(str);
            } else if (searchBy.equals("rank")) {
                records = bridge.getByRank(str);
            } else if (searchBy.equals("forcode")) {
                records = bridge.getByForcode("forcode");
            }
%>
<div id="masthead1"><h1>There are <%=records.size()%> Records in Database</h1></div>
<%
            if (records.size() == 0) {
                out.write("<font color=#ad9ca6>Your search - <strong>" + str + "</strong> - did not match any record in database</font>");
            } else {
        %>
        <div id="tb1wraper"> 
            <table style="margin:auto" id='tb1' border="1">
                <tr>
                    <th>CONF_ID</th>
                    <th>CONF_TITLE</th>
                    <th>Acronym</th>
                    <th>Rank</th>
                    <th>Changed</th>
                    <th>Forcode</th>
                </tr>
                <%
                    for (Coreconf coreconf : records) {
                        String confId = Integer.toString(coreconf.getConfId());
                        String confTitle = coreconf.getConfTitle();
                        String acronym = coreconf.getAcronym();
                        String rank = coreconf.getRank();
                        String changed = coreconf.getChanged().toString();
                        String forcode = coreconf.getForcode();
                        if (changed.equals("false")) {
                            changed = "No";
                        } else {
                            changed = "Yes";
                        }
                %>

                <tr>
                    <td><%=confId%></td>
                    <td>
                        <a href="searchDetails.jsp?acronym=<%=acronym%>&title=<%=confTitle%>&rank=<%=rank%>&changed=<%=changed%>&forcode=<%=forcode%>&year=<%=year%>">
                            <%=confTitle%>
                        </a>
                    </td>
                    <td><%=acronym%></td>
                    <td><%=rank%></td>
                    <td><%=changed%></td>
                    <td><%=forcode%></td>
                </tr>

                <%
                        }
                    }
                %>
            </table>
        </div>
        <%
            if (str.equals("") || !(records.size() == 0)) {

            } else {
        %>
        <br/>
        <hr/>
        <div id="masthead2"><h1>Google Search Results for "<%=str%>,<%=year%>"</h1></div>
        <%
            GoogleSearch googleSearch = new GoogleSearch();
            GsearchEntities gsearch = new GsearchEntities();
            try {
                gsearch = googleSearch.gsearch(str, year);
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
                        for (int i = 0; i < summarys.size(); i++) {
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
                    out.write("<font color=#ad9ca6>" + e.getMessage() + "</font>");
                }
            }
            //out the twitter content. 
            if (str.equals("") || !(records.size() == 0)) {

            } else {
        %>
        <hr/>
        <br/>
        <div id="masthead2"><h1> Relevant Tweets for "<%=str%>"</h1></div>
        <%
            TwitterAPI twitterAPI = new TwitterAPI();

            ArrayList<TweetEntities> tweets = new ArrayList<TweetEntities>();
            try {
                tweets = twitterAPI.tweets(str);

                SentiAnalysis sa = new SentiAnalysis();
        %>
        <div id="tweetOuter">
        <div id="tb2wraper" > 
            <table border="1" id="tb2"> 
                <tr>
                    <th colspan="2">Username</th>
                    <th>CreateAt</th>
                    <th>content</th>
                </tr>

                <%
                    for (TweetEntities tweet : tweets) {
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
        <br/><br/>
        <hr/>
        <br/><br/>
        <%                } catch (RuntimeException e) {
                    out.write("<font color=#ad9ca6>" + e.getMessage() + "for <strong>" + str + "</strong></font>");
                }
            }
        %>
    </body>
</html>
