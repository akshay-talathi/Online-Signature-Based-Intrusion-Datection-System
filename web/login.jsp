<%-- 
    Document   : login
    Created on : Feb 6, 2013, 5:01:28 AM
    Author     : Hemant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/style.css" />
        <link href='css/test.css' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery-1.7.min.js"></script>
        
       
    </head>
    <body>
        <div class="lg-container">
            <h1>Online Transaction Area</h1>
            <form action="authentication" id="lg-form" name="lg-form" method="post">
                <div>
                    <label for="username">Username:</label>
                    <input type="text" name="username" id="username" placeholder="username"/>
                </div>
                
                <div>
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" placeholder="password" />
                </div>
                
                <div>
                    <center> <button type="submit" id="login">Login</button></center>
                </div>
                
            </form>
             <% if (request.getAttribute("error")!=null) {%>
             <div id="message">
                  <%= request.getAttribute("error").toString() %>
              </div>
             <%}%>
            
        </div>
    </body>
</html>
