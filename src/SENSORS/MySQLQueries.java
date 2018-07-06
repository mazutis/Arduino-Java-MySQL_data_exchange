package SENSORS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**MySQL mazutis_db2 TABLES:
 *
 * positions:
 * ID | LED01 | LED02 | LED03 | servo180Position1 | servo180Duration1 | servo180Position2 | servo180Duration2 |
 * servo360Speed1 |servo360Duration1 | servo360Speed2 | servo360Duration2 | TimeStamp |
 *
 * readings:
 * ID | Temp01 | Temp02 | Humidity01 | Humidity02 | TimeStamp
 */

public class MySQLQueries {
    MySQLConnection connection = new MySQLConnection();

    Connection con;
    Statement st;
    ResultSet rs;

    //INITIALIZE CONNECTION.............................................................................................
    MySQLQueries(){
        try{
            con = connection.connect();
            st = con.createStatement();
            System.out.println("Connected to " + Xml.getURL());
        }catch (Exception e){
            e.printStackTrace();
            new Log(e);
        }
    }



    //READ FROM SQL table: positions....................................................................................
    public void getPositions(){
        try {
            rs = st.executeQuery("SELECT * FROM positions WHERE ID = 1");
            rs.next(); //Checks if the result set contains any values or not

            CONTROLLER.led01 = rs.getString("LED01");
            CONTROLLER.led02 = rs.getString("LED02");
            CONTROLLER.led03 = rs.getString("LED03");
            CONTROLLER.servo180Position1 = rs.getString("servo180Position1");
            CONTROLLER.servo180Position2 = rs.getString("servo180Position2");
            CONTROLLER.servo180Duration1 = rs.getString("servo180Duration1");
            CONTROLLER.servo180Duration2 = rs.getString("servo180Duration2");
            CONTROLLER.servo360Speed1    = rs.getString("servo360Speed1");
            CONTROLLER.servo360Speed2    = rs.getString("servo360Speed2");
            CONTROLLER.servo360Duration1 = rs.getString("servo360Duration1");
            CONTROLLER.servo360Duration2 = rs.getString("servo360Duration2");
            System.out.println("Data from MySQL table positions received, variables updated.");
        }
        catch (Exception e){
            e.printStackTrace();
            new Log(e);
        }
    }


    //SEND TO MYSQL TABLE: READINGS.....................................................................................
    public void sendReadings (Double temp1, Double hum1, Double temp2, Double hum2, Double sonic1, Double sonic2) {
        try {

            st.executeUpdate("INSERT INTO readings (Temp01, Temp02, Humidity01, Humidity02, Sonic01, Sonic02) " +
                    "VALUES ('"+temp1+"' , '"+temp2+"', '"+hum1+"', '"+hum2+"' , '"+sonic1+"', '"+sonic2+"')");

            System.out.println("Data sent to MySQL: "+ temp1+"|"+temp2+"|"+hum1+"|"+hum2+"|"+sonic1+"|"+sonic2);
        }catch (Exception e) {
            e.printStackTrace();
            new Log(e);
        }
    }


    // CLOSE MySQL CONNECTION...........................................................................................
    public void closeMySQLConnection(){
        try{
            con.close();
            System.out.println("MySQL connection closed.");
        }catch (Exception e){
            e.printStackTrace();
            new Log(e);
        }

    }

}