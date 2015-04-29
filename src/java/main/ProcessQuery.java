/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author Hemant
 */
public class ProcessQuery {

    
    public static ArrayList testProcessQuery(String query)
    {
        ArrayList returnArray = new ArrayList();
        
        
         if(query.length()<6 || !query.contains(" "))
        {
            returnArray.add("NO");
            returnArray.add("First Enter Proper Query then Process");
            return returnArray;
        }
         
         
         String cmdType = analyzeCmdType(query);
         
          if(cmdType.equalsIgnoreCase("select"))
        {
           String sel = analyzeSelect(query);
           if(sel.charAt(0)=='@')
           {
               returnArray.add("YES");
               returnArray.add(sel.substring(1)); // target object
               returnArray.add("select"); //cmd type
           }
           else
           {
               returnArray.add("NO");
               returnArray.add(sel);
           }
            
        }
          
        else if(cmdType.equalsIgnoreCase("insert"))
        {
            String sel = analyzeInsert(query);
            if(sel.charAt(0)=='@')
           {
               returnArray.add("YES");
               returnArray.add(sel.substring(1));
               returnArray.add("insert");
           }
           else
           {
               returnArray.add("NO");
               returnArray.add(sel);
           }
            
        }
        else if(cmdType.equalsIgnoreCase("delete"))
        {
            String sel = analyzeDelete(query);
            
            if(sel.charAt(0)=='@')
           {
               returnArray.add("YES");
               returnArray.add(sel.substring(1));
               returnArray.add("delete");
           }
           else
           {
               returnArray.add("NO");
               returnArray.add(sel);
           }
            
        }
        else if(cmdType.equalsIgnoreCase("update"))
        {
            String sel = analyzeUpdate(query);
              
            if(sel.charAt(0)=='@')
           {
               returnArray.add("YES");
               returnArray.add(sel.substring(1));
               returnArray.add("update");
           }
           else
           {
               returnArray.add("NO");
               returnArray.add(sel);
           }
            
        }
        else
        {
            returnArray.add("NO");
            returnArray.add("Invalid Query Enter Proper Query then Process");
        }
        
         
          
          return returnArray;
    }
    
    
     public static String analyzeCmdType(String query)
    {
        int index = query.indexOf(" ");
        String str = query.substring(0, index);
        //System.out.println("cmd_type : "+str);
        return str;
    }
     
     
     
       public static String analyzeSelect(String query)
    {
        String str="";
        boolean check =query.toLowerCase().contains("from");
        if(!check)
        {
            str ="Invalid Query";
            return str;
        }
        
        
        int pos =query.toLowerCase().indexOf("from");
        if(!(query.charAt(pos-1)+"").equalsIgnoreCase(" "))
        {
            str ="Invalid Query";
            return str;
        }
        
        
        pos = pos+4;
        
        if(query.length()<=pos)
        {
            str = "Invalid Query ";
            return str;
        }
        
        if(!(query.charAt(pos)+"").equalsIgnoreCase(" "))
        {
            str = "Invalid Query";
            return str;
        }
        
        String testQuery = query.substring(pos+1);
        testQuery = testQuery.trim();
        
        
        int index = testQuery.indexOf(" ");
        //JOptionPane.showMessageDialog(this, "c"+testQuery+"  "+index);
        String TargetObject="";
        if(index==-1)
        {
            if(testQuery.equalsIgnoreCase("UserTable") || testQuery.equalsIgnoreCase("CustomerTable") || 
                    testQuery.equalsIgnoreCase("DeptTable") || testQuery.equalsIgnoreCase("EmpTable") ||
                    testQuery.equalsIgnoreCase("OrderTable") || testQuery.equalsIgnoreCase("ProductTable"))
            {
                TargetObject=testQuery;
            }
            else
            {
                str = "Invalid Target Object Not Available";
                return str;
            }
        }
        else
        {
            TargetObject = testQuery.substring(0, index);
            if(testQuery.equalsIgnoreCase("UserTable") || testQuery.equalsIgnoreCase("CustomerTable") || 
                    testQuery.equalsIgnoreCase("DeptTable") || testQuery.equalsIgnoreCase("EmpTable") ||
                    testQuery.equalsIgnoreCase("OrderTable") || testQuery.equalsIgnoreCase("ProductTable"))
            {
                //System.out.println("valid");
            }
            else
            {
                str = "Invalid Target Object Not Available";
                return str;
            }
        }
        
       // System.out.println("Tar_Obj : "+TargetObject);
        
        
        //target object
        str = TargetObject;
        
        return "@"+str;
    }
   
   
       
       
       
       public static String analyzeInsert(String query)
    {
        String str="";
        boolean check =query.toLowerCase().contains("into");
        if(!check)
        {
            str = "Invalid Query";
            return str;
        }
        
        
        int pos =query.toLowerCase().indexOf("into");
        if(!(query.charAt(pos-1)+"").equalsIgnoreCase(" "))
        {
            str = "Invalid Query";
            return str;
        }
        
        
        pos = pos+4;
        
        if(query.length()<=pos)
        {
            str = "Invalid Query ";
            return str;
        }
        
        if(!(query.charAt(pos)+"").equalsIgnoreCase(" "))
        {
            str = "Invalid Query";
            return str;
        }
        
        String testQuery = query.substring(pos+1);
        testQuery = testQuery.trim();
        
        int indexval = testQuery.toLowerCase().indexOf("values");
        if(indexval==-1)
        {
            str = "Invalid Syntax";
            return str;
        }
        
        
        
        
        String Object="";
        int index2 = testQuery.indexOf("(");
        
        if(index2>indexval)
        {
            int index = testQuery.indexOf(" ");
            if(index==-1)
            {
                str = "Invalid Query";
                return str;
            }
            else
            {
                Object=testQuery.substring(0, index);
            }
        }
        else
        {
            Object = testQuery.substring(0, index2);
            
        }
        
        //JOptionPane.showMessageDialog(this, "c"+testQuery+"  "+index);
        String TargetObject="";
        
        if(Object.equalsIgnoreCase("UserTable") || Object.equalsIgnoreCase("CustomerTable") || 
                Object.equalsIgnoreCase("DeptTable") || Object.equalsIgnoreCase("EmpTable") ||
                Object.equalsIgnoreCase("OrderTable") || Object.equalsIgnoreCase("ProductTable"))
            {
                TargetObject=Object;
            }
            else
            {
                str = "Invalid Target Object Not Available";
                return str;
            }
        
        //asign target object
        str = TargetObject;
        //System.out.println("Tar_Obj : "+TargetObject);
        
        return "@"+str;
       
    }
       
       
       
       
    public static String analyzeDelete(String query)
    {
        String[] split = query.split(" ");
        String str="";
        
        if(split.length<3)
        {
            str = "Invalid Query";
            return str;
        }
        
        if(!split[1].equalsIgnoreCase("from"))
        {
            str = "Invalid Syntax";
            return str;
        }
        
        String table = split[2];
        String TargetObject="";
        
        if(table.equalsIgnoreCase("UserTable") || table.equalsIgnoreCase("CustomerTable") || 
                table.equalsIgnoreCase("DeptTable") || table.equalsIgnoreCase("EmpTable") ||
                table.equalsIgnoreCase("OrderTable") || table.equalsIgnoreCase("ProductTable"))
            {
                TargetObject=table;
            }
            else
            {
                str = "Invalid Target Object Not Available";
                return str;
            }
        
        //target object 
        str  = TargetObject;
        //System.out.println("Tar_Obj : "+TargetObject);
        
      return "@"+str;
    
    }
    
    
    
    
     public static String analyzeUpdate(String query)
    {
        String[] split = query.split(" ");
     
        String str="";
        if(split.length<3)
        {
            str = "Invalid Query";
            return str;
        }
        
         if(!split[2].equalsIgnoreCase("set"))
        {
            str = "Invalid Syntax";
            return str;
        }
        
        String table = split[1];
        String TargetObject="";
        
        if(table.equalsIgnoreCase("UserTable") || table.equalsIgnoreCase("CustomerTable") || 
                table.equalsIgnoreCase("DeptTable") || table.equalsIgnoreCase("EmpTable") ||
                table.equalsIgnoreCase("OrderTable") || table.equalsIgnoreCase("ProductTable"))
            {
                TargetObject=table;
            }
            else
            {
                str = "Invalid Target Object Not Available";
                return str;
            }
        
        
          str = TargetObject;
        //System.out.println("Tar_Obj : "+TargetObject);
          
          return "@"+str;  
    } 
        
     
     
     public static int findWeight(String cmd,String obj)
    {
        int weight=0;
        
        if(cmd.equalsIgnoreCase("select"))
        {
            weight = 1;
        }
        else if(cmd.equalsIgnoreCase("insert"))
        {
            weight = 2;
        }
        else if(cmd.equalsIgnoreCase("update"))
        {
            weight = 3;
        }
        else if(cmd.equalsIgnoreCase("delete"))
        {
            weight = 4;
        }
        
        
        if(obj.equalsIgnoreCase("UserTable"))
        {
            weight = weight + 10;
        }
        else if(obj.equalsIgnoreCase("CustomerTable"))
        {
            weight = weight + 20;
        }
        else if(obj.equalsIgnoreCase("DeptTable"))
        {
            weight = weight + 30;
        }
        else if(obj.equalsIgnoreCase("EmpTable"))
        {
            weight = weight + 40;
        }
        else if(obj.equalsIgnoreCase("OrderTable"))
        {
            weight = weight + 50;
        }
        else if(obj.equalsIgnoreCase("ProductTable"))
        {
            weight = weight + 60;
        }
        
        return weight;
    }
        
}
