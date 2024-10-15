package com.mycompany;

import com.mycompany.databaseControl.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QuickAdd {
    Connection DBC;
    Statement SQLStatement;
//    ResultSet rSet;
//    PreparedStatement pStatement;
    
    QuickAdd(){
        this.connect();
    }
    // --------------------------------

    private void connect() {
        DBC = Connect.initDBConnection();
        try {
            SQLStatement = DBC.createStatement();
            System.out.println("\n<SQL Statment ready>");
        } catch (SQLException SQLEx) {
            System.out.println(SQLEx.getMessage());
        }
    }

    public void wildCard(String sqlOp) {
        System.out.println("\n");
        System.out.println(sqlOp);
        try {
            SQLStatement.executeUpdate(sqlOp);
            System.out.println("<SQL Statement successful>");
        } catch (SQLException SQLEx) {
            System.out.println(SQLEx.getMessage());
        }
    }

  

    // -----------------------------------------
    // TESTING
//    public static void main(String[] args) {
//        QuickAdd obj = new QuickAdd();
//        
//String table1 = "CREATE TABLE Cars("
//            + "car_name VARCHAR(30), "
//            + "num_of_votes INTEGER"
//            + ")";
//
//        String op1 = "INSERT INTO Cars VALUES('car34', 10);";
//
////        obj.wildCard(table1);
//        obj.wildCard(op1);
//        
//        System.out.println("\n<END OF TEST>");
//    }
}
