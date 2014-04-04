<%-- 
    Document   : index
    Created on : Mar 15, 2014, 8:56:08 PM
    Author     : lingjunqiu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Ipublish</title>
    </head>
    
    <style>
        body {
            margin: 0px;
            padding: 0px;
            font-size: 12px;   
            font: normal 100% 'Arial','Helvetica','Verdana',sans-serif; 
            color: #333;
            
        }
        #form{margin: 0 auto;}
        #inputArea {margin-top:30px; margin-bottom:10px; padding: 0;}
        #inputArea input {margin:0; padding:0; width:470px; height:25px; font-size:14px;}
        #inputArea input[type="image"] {width:150px; height:30px; margin-left:5px; float:right;}
        #masthead {text-align: center; background:#006699; margin-top:0px;}
        #masthead h1 {font-size:20px; margin:0 auto; padding:8px 0; color:#fff;}
        #outer {margin-left:180px; width:700px;margin: 0 auto;}
        #searchBy select { width:130px; height:24px; background:none; border:none;background-color: #006699;color: #fff;}
        #searchBy{margin-left: 70px;float: left;} 
        #choose{ width:80px; height:24px; background:none; border:none;background-color: #006699;color: #fff;}
        #years{float: left;margin-left: 25px;}
        #filter{float: left;margin-right: 20px;color: red;font-weight: bold; font-style: oblique;}
       #content{margin: 0 auto;}
    </style>
    <body>  
        <div id="form">
            <form action="action.jsp" method="post">
                    <div id="masthead">
                        <h1>The Search Engine for academic conference</h1>
                    </div>
                    
                    <div id="outer">
                        <div id="inputArea">       
                            <ul id="qsearch">
                                <label for="query" class="hidden"></label>
                                <input type="text" id="query" name="query" />
                                <label for="queryButton" class="hidden"></label>
                                <input type="image" src="images/search_btn_up.jpg" width="150" height="30" alt="Search" id="queryButton" name="queryButton" value="Search"/>
                            </ul>
                       
                    </div> <!-- end outer -->
                    <br/>
<div id="content">
                <div id="searchBy">
                    <div id="filter">Search by:</div>
                    <select name="searchBy" id="select">
                        <option value="all">All</option>
                        <option value="title">Conference Name</option>
                        <option value="acronym">Acronym</option>
                        <option value="rank">Rank</option>
                        <option value="forcode">Forcode</option>
                    </select>
                </div>

                <div id="years">
                    <select name="year" id="choose">
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option> 
                        <option value="2009">2009</option> 
                        <option value="2008">2008</option> 
                    </select>
                </div>
                </div>
                     </div>
            </form>
        </div>
    </body>
</html>
