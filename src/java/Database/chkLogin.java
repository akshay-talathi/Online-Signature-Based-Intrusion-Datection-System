/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author test
 */
public class chkLogin 
{
    DatabaseConnection db;
    public chkLogin()
    {
     db=new DatabaseConnection();
     db.dbconnection();
    }

     public boolean isAuthenticate(String userid,String password)
    {
        boolean flag=false;
        try 
        {
            String query="SELECT * FROM login WHERE uname=? AND password=?";
            PreparedStatement ps=db.dbconnection().prepareStatement(query);
            ps.setString(1, userid);     
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                flag=true;
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
  
     public boolean isTrained(String username)
     {
         boolean flag = false;
         try
         {
             String query = "select count(*) from trainingdata where username='"+username+"'";
             ResultSet rs = db.getResultSet(query);
             int val = 0;
             if(rs.next())
                 val = rs.getInt(1);
             
             if(val>=5)
                 flag= true;
             else
                 flag = false;
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return flag;
     }
    
     
      public int getTransactionID(String name)
    {
        int id=0;
        try
        {
            ResultSet rs = db.getResultSet("SELECT MAX(TransactionID) FROM OriginalData WHERE Username='"+name+"'");
            if(rs.next())
            {
                id = rs.getInt(1);
                id = id + 1;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return id;
    }
 
      
      
      public boolean checkWeight(String username,String weight)
      {
          boolean flag=false;
          try
          {
              String[] split = weight.split(",");
              
              int[] first = new int[split.length];
              for(int i=0;i<first.length;i++)
              {
                  first[i] = Integer.parseInt(split[i]);
              }
              java.util.Arrays.sort(first);
              
              String query = "select weight from trainingData where username = '"+username+"'";
              ResultSet rs = db.getResultSet(query);
              while(rs.next())
              {
                  String str = rs.getString("weight");
                  String[] split1 = str.split(",");
                  
                  int[] second = new int[split1.length];
                  for(int i=0;i<second.length;i++)
                  {
                      second[i] = Integer.parseInt(split1[i]);
                  }
                  java.util.Arrays.sort(second);
                  
                  
                  int count=0;
                  if(first.length == second.length)
                  {
                      for(int i=0;i<first.length;i++)
                      {
                          if(first[i] == second[i])
                          {
                              count++;
                          }
                          else
                              break;
                          
                      }
                      
                      if(count == first.length)
                      {
                        flag = true;
                        break;
                      }
                  }
                  
              }
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          return flag;
      }
}
