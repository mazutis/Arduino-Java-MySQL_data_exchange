package SENSORS;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * 14/06/2018
 */
public class View {


    MySQLConnection connection = new MySQLConnection();
    // SEND TO MYSQL TABLE: POSITIONS===================================================================================
    public void sendPositions (String LED01, String LED02, String LED03, String servo180Position1, String servo180Duration1,
                               String servo180Position2, String servo180Duration2, String servo360Speed1,
                               String servo360Duration1, String servo360Speed2, String servo360Duration2) {
        try {
            Connection con = connection.connect();
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE positions SET LED01='"+LED01+"',  LED02='"+LED02+"', LED03='"+LED03+"', "+
                    "servo180Position1='"+servo180Position1+"', servo180Duration1='"+servo180Duration1+"', "+
                    "servo180Position2='"+servo180Position2+"', servo180Duration2='"+servo180Duration2+"', "+
                    "servo360Speed1='"+servo360Speed1+"', servo360Duration1='"+servo360Duration1+"', "+
                    "servo360Speed2='"+servo360Speed2+"', servo360Duration2='"+servo360Duration2+"' " +
                    "WHERE ID = 1");
            con.close();
            System.out.println("Data sent to MySQL.");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
