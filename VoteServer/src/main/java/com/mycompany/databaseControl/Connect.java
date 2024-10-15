
package com.mycompany.databaseControl; 

import java.sql.*; 

public class Connect {
    
    public static Connection initDBConnection(){
        Connection dbc = null; 
        String DBURL = "jdbc:derby://localhost:1527/CarVote";
        String username = "root";
        String password = "root";
        
        System.out.println("\nAttempting to establish connection to database...");
        try{
            dbc = DriverManager.getConnection(DBURL, username, password);
            System.out.println("<Connection Established>");
        }
        catch(SQLException SQLEx){
            System.out.println(SQLEx.getMessage());
        }
        return dbc; 
    }
}
