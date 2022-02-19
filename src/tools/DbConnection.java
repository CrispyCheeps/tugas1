package tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private Connection conn;
    private boolean connectionStatus;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //it returns com.mysql.jdbsc.driver class
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/latihan1" +
                    //Toavoid timeStamp
                    "?zeroDateTimeBehavior=convertToNull", "root", "password");
            //penambahan connection status utk validasi koneksi
            this.connectionStatus = true;
            System.out.println("Successfully connected to the database");
        //Printing the exception
        } catch (Exception e) {
            //penambahan connection status utk validasi koneksi
            this.connectionStatus = false;
            System.out.println("\n\nError :" + e.getMessage());
        }
        return conn;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }
}
