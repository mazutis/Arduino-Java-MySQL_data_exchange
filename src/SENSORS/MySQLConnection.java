package SENSORS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** CONNECTION TO MySQL: 000.000.00.144 | www.000000.com
  * CONNECTION ALLOWED ONLY FROM 00.000.000.215
  * add to project mysql-connector-java-x.x.xx-bin.jar libraries
  */

public class MySQLConnection{
    private final static Xml xml = new Xml();
    /** Connection to database info stored in key.xml file     */
    private static Connection conn;
    private final static String url  = xml.getURL();
    private final static String user = xml.getUser();
    private final static String pass = xml.getPassword();
    private final static String SSL  = xml.getSSL();

    private static String fullURL = "jdbc:" + url + "?useSSL="+SSL +"&serverTimezone=UTC";
    // Template: jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

    //CONNECT to MySQL..................................................................................................
    static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
            new Log(cnfe);
        }
        conn = DriverManager.getConnection(fullURL,user,pass);
        return conn;
    }

    //TEST MySQL CONNECTION.............................................................................................
    static boolean testConnection(){
        boolean connected = false;
        try {
            DriverManager.getConnection(fullURL, user, pass);
            connected = true;
        }catch(Exception e){
            e.printStackTrace();
            new Log(e);
        }
        return connected;
    }
    //..................................................................................................................

}
