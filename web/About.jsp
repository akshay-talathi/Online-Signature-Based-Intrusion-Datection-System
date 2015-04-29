<%-- 
    Document   : About
    Created on : Feb 7, 2013, 1:16:02 PM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <link rel="stylesheet" href="css/style.css" />
        <link href='css/test.css' rel='stylesheet' type='text/css'>
    </head>
    <body>
         <% 
      if (request.getAttribute("username") != null || session.getAttribute("username") != null) 
      {
        %>
        
          <%@include file="Header.jsp" %> 
         <div class="lg-container-frame">
             
            <div style="font-size: 22px;"><br /><br />
                We have proposed two approaches to detect the intrusions in database. They provide an additional layer of security in DBMS.
                It can be considered as generic approach for any database and limitation of the exiting database security mechanisms.
                We are extending our work with the help of CBF to ensure security in database. Our comparisons show that it performs better.
            </div>
            
            <div id="message"></div>
        </div>
         
           <% 
                   
         } else
         {
           request.setAttribute("error", "please login first!!!!");  
           request.getRequestDispatcher("login.jsp").forward(request, response);   
         }
      %>
    </body>
</html>
