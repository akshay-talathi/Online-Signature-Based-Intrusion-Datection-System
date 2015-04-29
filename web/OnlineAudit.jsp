<%-- 
    Document   : OnlinAudit
    Created on : Feb 7, 2013, 1:24:56 PM
    Author     : test
--%>

<%@page import="Authentication.authentication"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="Database.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Audit Area</title>
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
             <div style="float: right;font-size: 25px; color: brown" id="sequence">Sequence no &nbsp; : &nbsp; <%= authentication.monitor.size() %>  </div>
            <form action="OnlineAuditServlet" id="Query-form" name="lg-form" method="post">
                
                <div>
                    <label for="query">Enter Query Here :</label>
                    <div>
                        <textarea rows="3" cols="30" name="Query" id="query" placeholder="Write Query Here" style="resize: none;font-size: 26px; "></textarea> 
                        </div>
                </div>
                
                <div>
                    <center><button type="submit" id="login">Query Process</button></center>
                </div>
                
            </form>
             
        <% 
        if (request.getAttribute("valid") != null) 
        {
        %>
             <div id="valid"><%= request.getAttribute("valid") %></div>
         <% 
          }
          else if(request.getAttribute("invalid") != null)
          {
        %>
        <div id="invalid"><%= request.getAttribute("invalid") %></div>
      
            <% } %>
            
        
            
            
            
            
            
            <div class="lg-container-table" style="overflow: auto">
                <%
                if(request.getAttribute("result") != null)
                  {
                    DatabaseConnection db = new DatabaseConnection();
                    db.dbconnection();
                    
                    String query = request.getAttribute("Query").toString();
                    
                    ResultSet rs = db.getResultSet(query);    
                    ResultSetMetaData md = rs.getMetaData();
                    
                    int col = md.getColumnCount();
    %>
                <table width="95%">
    <tr>
    <% 
                    for( int i = 1; i <= md.getColumnCount(); i++ )
                     {
                 %>
                 <th><%= md.getColumnLabel(i) %></th>
                 
                <% } %>
    </tr>
    <tr>
    <%
       while(rs.next()) 
           {
            for( int i = 1; i <= md.getColumnCount(); i++ )
                {
%>
<td align="center"><%= rs.getString(i) %></td>
      <% } %>
      </tr>
   <% } 
             request.setAttribute("result", null);   
             } %>   
        </table>    
            </div>
            
            
            
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
