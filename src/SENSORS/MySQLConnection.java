package SENSORS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** CONNECTION TO MySQL: 000.000.00.144 | www.000000.com
  * CONNECTION ALLOWED ONLY FROM 00.000.000.215
  * add to project mysql-connector-java-x.x.xx-bin.jar libraries
  */

public class MySQLConnection{

    /** Connection to database info stored in key.xml file     */
    private static Connection conn;
            static String url  = Xml.url;           // "mysql://xxxxxx.com:3306/xxxxx";
    private static String user = Xml.user;          // "user_name";
    private static String pass = Xml.password;      // "password";
    private static String SSL  = Xml.ssl;           // "true";

    private static String fullURL = "jdbc:" + url + "?useSSL="+SSL +"&serverTimezone=UTC";
    // Template: jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

    // .................................................................................................................
    public static Connection connect() throws SQLException{
        //System.out.println("Connecting to " + url);                                                                   // test tracking
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
            new Log(cnfe);
        }
        conn = DriverManager.getConnection(fullURL,user,pass);
        return conn;
    }
    //..................................................................................................................

}
