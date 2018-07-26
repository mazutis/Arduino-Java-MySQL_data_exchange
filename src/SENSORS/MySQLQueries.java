package SENSORS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**MySQL mazutis_db2 TABLES:
 *
 * Table positions:
 * ID | LED01 | LED02 | LED03 | servo180Position1 | servo180Duration1 | servo180Position2 | servo180Duration2 |
 * servo360Speed1 |servo360Duration1 | servo360Speed2 | servo360Duration2 | TimeStamp |
 *
 * Table readings:
 * ID | Temp01 | Temp02 | Humidity01 | Humidity02 | TimeStamp
 */

public class MySQLQueries {
    MySQLConnection connection = new MySQLConnection();

    Connection con;
    Statement st;
    ResultSet rs;

    private StringBuilder sendSettings = new StringBuilder();

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
    String[] getPositions(){
        String positions[] = new String[11];
        try {
            rs = st.executeQuery("SELECT * FROM positions WHERE ID = 1");
            rs.next(); //Checks if the result set contains any values or not

            positions[0] = (rs.getString("LED01"));
            positions[1] = (rs.getString("LED02"));
            positions[2] = (rs.getString("LED03"));
            positions[3] = (rs.getString("servo180Position1"));
            positions[4] = (rs.getString("servo180Position2"));
            positions[5] = (rs.getString("servo180Duration1"));
            positions[6] = (rs.getString("servo180Duration2"));
            positions[7] = (rs.getString("servo360Speed1"));
            positions[8] = (rs.getString("servo360Speed2"));
            positions[9] = (rs.getString("servo360Duration1"));
            positions[10] = (rs.getString("servo360Duration2"));
            System.out.println("Received variables from MySQL.");
        }
        catch (Exception e){
            e.printStackTrace();
            new Log(e);
        }
        return positions;
    }



    //SEND RXTX READINGS TO MYSQL.......................................................................................
    void sendReadings (Double temp1, Double hum1, Double temp2, Double hum2, Double sonic1, Double sonic2) throws Exception{
            st.executeUpdate("INSERT INTO readings (Temp01, Temp02, Humidity01, Humidity02, Sonic01, Sonic02) " +
                    "VALUES ('"+temp1+"' , '"+temp2+"', '"+hum1+"', '"+hum2+"' , '"+sonic1+"', '"+sonic2+"')");

            System.out.println("Data sent to MySQL: "+ temp1+"|"+temp2+"|"+hum1+"|"+hum2+"|"+sonic1+"|"+sonic2);
     }

    //SEND SETTINGS TO MYSQL............................................................................................
    void sendSettings (String setLed01, String setLed02, String setLed03) throws Exception{
        //build query
        sendSettings.replace(0,sendSettings.length(), "UPDATE positions SET "); //reset StringBuilder
            if (!setLed01.equals("null")) sendSettings.append("LED01 = '"+setLed01+"', ");
            if (!setLed02.equals("null")) sendSettings.append("LED02 = '"+setLed02+"', ");
            if (!setLed03.equals("null")) sendSettings.append("LED03 = '"+setLed03+"', ");
        sendSettings.deleteCharAt(sendSettings.length()-2); //delete last "," symbol
        sendSettings.append("WHERE ID = 1");

        System.out.println("SQL: " +sendSettings);
        st.executeUpdate(sendSettings.toString());

        //null pending settings
        CONTROLLER.setMySqlLed01("null");
        CONTROLLER.setMySqlLed02("null");
        CONTROLLER.setMySqlLed03("null");

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