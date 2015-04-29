

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/style.css" />
        <link href='css/test.css' rel='stylesheet' type='text/css'>
    </head>
    <body>
          <% 
      if (request.getAttribute("username") != null || session.getAttribute("username") != null) 
      {
          if(request.getAttribute("username")!=null && session.getAttribute("username")==null)
           {   
            session.setAttribute("username",request.getAttribute("username") );
          }            
        %>
        
        <%@include file="Header.jsp" %> 
         <div class="lg-container-frame">
             
             <div style="font-size: 22px;"><br /><br />
                In this paper, we propose database intrusion detection mechanism to enhance the security of DBMS. In a typical database environment, it is possible to define the profile of transactions that each user is allowed to execute. In our approach, we use the transactions profile and overall system architecture is divided into two parts, learning phase and intrusion detection phase. The learning phase generates authorized transactions profile automatically and is used at detection phase to check the behaviour of executable transactions. We also implement the detection phase with the help of Counting Bloom Filter (CBF) and comparing both the approaches.
            </div>
            <div style="font-size: 22px;"><br />
                Database security research is mainly concerned about the protection of database from unauthorized access. The unauthorized access may be in form of execution of malicious transactions that may breach the security of database and lead to break the integrity over the database. These malicious transactions (database intrusions) are to be taken care of. Researchers have taken interest to develop the database IDS (intrusion detection system) to protect the database from malicious transactions
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
