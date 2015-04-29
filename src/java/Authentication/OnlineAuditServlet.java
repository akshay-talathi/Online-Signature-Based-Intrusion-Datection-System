/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import Database.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.ProcessQuery;

/**
 *
 * @author Hemant
 */
public class OnlineAuditServlet extends HttpServlet {

    
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            String Query = request.getParameter("Query");
            Query = Query.trim();
            
            ArrayList list = ProcessQuery.testProcessQuery(Query);
            
            if(list.get(0).toString().equals("YES"))
            {
                String tarType = list.get(1).toString();
                String cmdType = list.get(2).toString();
                
                Transaction trn = new Transaction();
                if(cmdType.equalsIgnoreCase("select"))
                {
                    if(trn.isValidResultSet(Query))
                    {
                        authentication.monitor.add(cmdType+","+tarType);
                        
                        request.setAttribute("result", "valid");
                        request.setAttribute("Query", Query);
                        
                        request.setAttribute("valid", " Row are Selected");
                        request.getRequestDispatcher("OnlineAudit.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("invalid", "Invalid Query Enter Proper Query");
                        request.getRequestDispatcher("OnlineAudit.jsp").forward(request, response);
                    }
                }
                else
                {
                    int i = trn.getUpdateTransaction(Query);
                    if(i != -1)
                    {
                        authentication.monitor.add(cmdType+","+tarType);
                        
                        String valid="";
                        if(cmdType.equalsIgnoreCase("insert"))
                                valid = i+" Row Inserted Successfully";
                        else if(cmdType.equalsIgnoreCase("update"))
                                valid = i+" Row Update Successfully";
                        else if(cmdType.equalsIgnoreCase("delete"))
                                valid = i+" Row Deleted Successfully";
                        
                        
                        request.setAttribute("valid", valid);
                        request.getRequestDispatcher("OnlineAudit.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("invalid", "Invalid Query Enter Proper Query");
                        request.getRequestDispatcher("OnlineAudit.jsp").forward(request, response);
                    }
                }
            }
            else
            {
                String invalid = list.get(1).toString();
                request.setAttribute("invalid", invalid);
                request.getRequestDispatcher("OnlineAudit.jsp").forward(request, response);
            }
            
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
