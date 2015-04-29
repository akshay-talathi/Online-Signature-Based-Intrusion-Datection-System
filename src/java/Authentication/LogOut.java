/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import Database.DatabaseConnection;
import Database.Transaction;
import Database.chkLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.ProcessQuery;

/**
 *
 * @author test
 */
public class LogOut extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            String username = request.getSession().getAttribute("username").toString();
            chkLogin c = new chkLogin();
            int transactionID = c.getTransactionID(username);
            String weight="";
            
            
            DatabaseConnection db = new DatabaseConnection();
            db.dbconnection();
            for(int i=0;i<authentication.monitor.size();i++)
            {
                String[] split = authentication.monitor.get(i).toString().split(",");
                
                int seq = (i+1);
                String cmd = split[0];
                String obj = split[1];
                
            String query = "INSERT INTO OnlineAudit(Username,TransactionID,SequenceNo,CommandType,TargetObject)"
                    + " VALUES('"+username+"' , "+transactionID+" , "+seq+" , '"+cmd+"' , '"+obj+"')";
            
            
            db.getUpdate(query);
            
            int wgt = ProcessQuery.findWeight(cmd, obj);
            weight = weight + wgt+",";
            }
            
            
            
            boolean flg = c.checkWeight(username,weight);
            Transaction t = new Transaction();
            
            
            
            String error="";
            if(flg)
            {
                t.transactionDone();
                String query = "INSERT INTO OriginalData(Username,TransactionID,Weight,Status)"
                        + "VALUES('"+username+"' , "+transactionID+" , '"+weight+"' , 'Valid Transaction')";
                
                db .getUpdate(query);
                error = "This is a Valid Transaction<br />Successfully logout";
            }
            else
            {
                t.transactionCancel();
                String query = "INSERT INTO OriginalData(Username,TransactionID,Weight,Status)"
                        + "VALUES('"+username+"' , "+transactionID+" , '"+weight+"' , 'Malicious Transaction')";
                
                db .getUpdate(query);
                error = "This is a Malicious transaction Detected<br />Successfully logout";
                
            }
            
            request.setAttribute("username", null); 
            request.getSession().setAttribute("username", null);
            
            
                    request.setAttribute("error", error);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
