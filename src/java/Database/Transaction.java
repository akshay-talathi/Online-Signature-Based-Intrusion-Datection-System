/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;

/**
 *
 * @author Hemant
 */
public class Transaction {
 
    DatabaseConnection db;
    public Transaction()
    {
        db = new DatabaseConnection();
        db.dbconnection();
        db.setCommit();
    }
    
     public boolean isValidResultSet(String query)
     {
         
         try
         {
             ResultSet r =db.getResultSet(query);
             return true;
         }
         catch(Exception e)
         {
             return false;
         }
     }
     
     public int getUpdateTransaction(String query)
     {
         int i =0;
         try
         {
             i = db.getTableUpdate(query);
         }
         catch(Exception e)
         {
             i=-1;
         }
         return i;
     }
     
     
     public void transactionDone()
     {
         db.doneCommit();
     }
     
     
     public void transactionCancel()
     {
         db.rollBack();
     }
}
