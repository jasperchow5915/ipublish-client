<%-- 
    Document   : 404.jsp
    Created on : Mar 24, 2014, 9:52:31 PM
    Author     : lingjunqiu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error 500 (Internal server error)!!</title>
    </head>
    <style>

        #error{
            position: absolute;
            right: 680px;
            bottom: 400px;
            color: #907f77;
            font-size: 16px;
        }
        #error strong{
            color: #000000;
        }
        #response{
            margin-top: 10px;
            color: #000000;
        }
        #image{
            background-image: url(error.gif);
            width: 267px;
            height: 240px;
            position: absolute;
            right: 180px;
            bottom: 300px;
        }
        #brand{
            background-image: url(brand.gif);
            width: 151px;
            height: 48px;
            position: absolute;
            right: 670px;
            bottom: 480px;
        }
    </style>
    <body>
        <div id='error'>
            <strong>500.</strong>
            That's an error
            <div id='response'>Internal server error</div>
        </div>
        
        <div id='image'></div>
        <div id='brand'></div>
    </body>
</html>

