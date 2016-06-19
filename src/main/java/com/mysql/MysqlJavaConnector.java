package com.mysql;

/**
 * Created by lilianngweta on 6/16/16.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class MysqlJavaConnector {

    public static Connection conn = null;

//    public static void main(String[] args) {
//         loadDriver();
//
//        System.out.println(getConnection());
//    }
    private static void loadDriver() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }


    public static Connection getConnection() {
        try

        {
          if(conn==null) {
              loadDriver();
              conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managelist?user=root&password=DO54LIMA");
          }
            // Do something with the Connection


        } catch (SQLException ex)

        {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return conn;

    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn=null;

    }
}
