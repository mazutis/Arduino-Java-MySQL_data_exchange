package SENSORS;

import com.fazecast.jSerialComm.SerialPort;

public class CONTROLLER {
    private static  MySQLQueries mySqlQueries;
    private static volatile boolean connected = false; //for Thread control;

    private static StringToVariables strToVar = new StringToVariables();
    private static Xml xml = new Xml();
    private static RxTx rxtx = new RxTx();
    private static SerialPort userPort = SerialPort.getCommPort(xml.getRxTxPort());                                     //<settings>
    private static int readingsInterval = 030_000;  //time[ms] interval to send sensors readings to MySQL               //<settings>
    private static int positionsInterval =001_000;  //time[ms] interval to send positions to ARDUINO                    //<settings>
    private static int blocksToRead = 130;          // blocks size to read received from arduino via RxTx port          //<settings>

    private static double temp1  = -999.00;
    private static double temp2  = -999.00;
    private static double hum1   = -999.00;
    private static double hum2   = -999.00;
    private static double sonic1 = -999.00;
    private static double sonic2 = -999.00;
    private static String setRxTxLed01   = "null";
    private static String setRxTxLed02   = "null";
    private static String setRxTxLed03   = "null";
    private static String setMySqlLed01 = "null";
    private static String setMySqlLed02 = "null";
    private static String setMySqlLed03 = "null";
    private static String servo180Position1 = "0015";  // RANGE 0000-0180
    private static String servo180Position2 = "0015";  // RANGE 0000-0180
    private static String servo180Duration1 = "0000";  // Milliseconds
    private static String servo180Duration2 = "0300";  // Milliseconds
    private static String servo360Speed1    = "1450";  // 1000 - counter clockwise; 2000 - clockwise
    private static String servo360Speed2    = "1450";  // 1450 = Stop
    private static String servo360Duration1 = "0300";  // Milliseconds
    private static String servo360Duration2 = "0300";  // Milliseconds

    CONTROLLER(){
        System.out.println("Starting controller..");
        connect.start();
        Thread1.start();                    // Thread 1 send data to RxTx
        Thread2.start();                    // Thread 2 send data to MySQL
    }


//THREAD CONNECT AND START
    //Initialize connection and start Thread series
    private static Thread connect = new Thread(){

        public void run() {

            while (true) {//Connection reset point
                try {//Try to connect for the first time
                    while (!connected) { //Retry to connect
                        if (rxtx.connectToPort(userPort)) { //Connect to MySQL else repeat cycle
                            if (MySQLConnection.testConnection()) {
                                mySqlQueries = new MySQLQueries();

                                connected = true;
                            } else {
                                System.out.println("No MySQL connection.");
                                for (int i = 10; i >= 0; i--) { //reconnecting after i seconds
                                    System.out.print("\rreconnecting in " + i + "s");
                                    sleep(1000);
                                }
                                System.out.println("\r");
                                new Log("No MySQL connection. Reconnecting...");
                            }
                        } else { //Try to connect to RxTx else repeat cycle
                            System.out.println("No RxTX connection");
                            for (int i = 10; i >= 0; i--) { //reconnecting after i seconds
                                System.out.print("\rreconnecting in " + i + "s");
                                sleep(1000);
                            }
                            System.out.println("\r");
                            new Log("No RxTx connection. Reconnecting...");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Log(e);
                }
                while(connected){
                    //watching connection
                }
            }
        }

    };


//THREAD 1
    // EXCHANGE DATA read MySQL send to RxTx............................................................................
    private static Thread Thread1 = new Thread(){
        public void run() {
            while (true) {
                while(!connected){
                    //wait till connected
                }
                while (connected) {
                    try {
                        System.out.println("Thread 1: MySql to RxTx..");                                                               // test tracking


                        //collect data from MySQL table: positions
                        sleep(5000); //safetyDelayTime to receive data from MySQL

                        String positions[] = mySqlQueries.getPositions();

                        setRxTxLed01           = positions[0];
                        setRxTxLed02           = positions[1];
                        setRxTxLed03               = positions[2];
                        servo180Position1   = positions[3];
                        servo180Duration1   = positions[4];
                        servo180Position2   = positions[5];
                        servo180Duration2   = positions[6];
                        servo360Speed1      = positions[7];
                        servo360Duration1   = positions[8];
                        servo360Speed2      = positions[9];
                        servo360Duration2   = positions[10];

                        System.out.print("Updated MySQL positions:");                                                   // test tracking
                        System.out.print("|" + setRxTxLed01);
                        System.out.print("|" + setRxTxLed02);
                        System.out.print("|" + setRxTxLed03);
                        System.out.print("|" + servo180Position1);
                        System.out.print("|" + servo180Duration1);
                        System.out.print("|" + servo180Position2);
                        System.out.print("|" + servo180Duration2);
                        System.out.print("|" + servo360Speed1);
                        System.out.print("|" + servo360Duration1);
                        System.out.print("|" + servo360Speed2);
                        System.out.println("|" + servo360Duration2);

                        //send data to RxTx
                        rxtx.sendCommand(userPort, "LED01" + setRxTxLed01);
                        sleep(5000);//safetyDelayTime

                        rxtx.sendCommand(userPort, "LED02" + setRxTxLed02);
                        sleep(5000);//safetyDelayTime

                        rxtx.sendCommand(userPort, "LED03" + setRxTxLed03);
                        sleep(5000);//safetyDelayTime

                        rxtx.sendCommand(userPort, "SERVO180," + servo180Position1 + "," + servo180Duration1 +
                                "," + servo180Position2 + "," + servo180Duration2);
                        sleep(9000);//safetyDelayTime

                        rxtx.sendCommand(userPort, "SERVO360," + servo360Speed1 + "," + servo360Duration1 +
                                "," + servo360Speed2 + "," + servo360Duration2);
                        sleep(9000);//safetyDelayTime*/

                        sleep(positionsInterval);

                    } catch (java.io.IOException IOexception) {
                        System.out.println("RxTx connection lost..");
                        new Log(IOexception);
                        connected = false;

                    } catch (Exception e) {
                        e.printStackTrace();
                        new Log(e);
                        connected = false;
                    }
                }
            }


        }
    };

//THREAD 2
    // EXCHANGE DATA read RxTx send to MySQL............................................................................
    static Thread Thread2 = new Thread(){
        public void run() {
            while (true) {
                while (!connected) {
                    //wait till connected
                }
                while (connected) {
                    try {
                        System.out.println("Thread 2: RxTX to MySQL.. ");                                                          // test tracking

                        //read data from RxTx
                        String serialPortString = rxtx.readPortBlocks(userPort, blocksToRead);
                        sleep(2000); //safetyDelayTime to read rxtx data blocks
                        System.out.println(serialPortString);                                                                   // test tracking

                        //analyse data, update variables with new data
                        temp1 = strToVar.getTemp1(serialPortString);
                        temp2 = strToVar.getTemp2(serialPortString);
                        hum1 = strToVar.getHum1(serialPortString);
                        hum2 = strToVar.getHum2(serialPortString);
                        sonic1 = strToVar.getSonicLength1(serialPortString);
                        sonic2 = strToVar.getSonicLength2(serialPortString);

                        //send data to MySQL table readings
                        sleep(5000);//safetyDelayTime
                        // table: readings
                        mySqlQueries.sendReadings(temp1, hum1, temp2, hum2, sonic1, sonic2);
                        //table: positions
                        if (getMySqlPending()){
                            mySqlQueries.sendSettings(setMySqlLed01, setMySqlLed02, setMySqlLed03);
                        }


                        sleep(readingsInterval);

                    }catch (Exception e) {
                        System.out.println("MySQL connection lost..");
                        e.printStackTrace();
                        new Log(e);
                        connected = false;
                    }
                }
            }
        }
    };

    //SET & GET parameters..............................................................................................
    public static String getTemp1(){
        String t1 = "n/a";
        if (CONTROLLER.temp1 != -999.0){
            t1 = Double.toString(CONTROLLER.temp1);
        }
        return t1;
    }
    //
    public static String getTemp2(){
        String t2 = "n/a";
        if (CONTROLLER.temp2 != -999.0){
            t2 = Double.toString(CONTROLLER.temp2);
        }
        return t2;
    }
    //
    public static String getHum1(){
        String h1 = "n/a";
        if (CONTROLLER.hum1 != -999.0){
            h1 = Double.toString(CONTROLLER.hum1);
        }
        return h1;
    }
    //
    public static String getHum2(){
        String h2 = "n/a";
        if (CONTROLLER.hum2 != -999.0){
            h2 = Double.toString(CONTROLLER.hum2);
        }
        return h2;
    }
    //
    public static String getSonic1(){
        String s1 = "n/a";
        if (CONTROLLER.sonic1 != -999.0){
            s1 = Double.toString(CONTROLLER.sonic1);
        }
        return s1;
    }
    //
    public static String getSonic2(){
        String s2 = "n/a";
        if (CONTROLLER.sonic2 != -999.0){
            s2 = Double.toString(CONTROLLER.sonic2);
        }
        return s2;
    }
    //............................................................
    public static String getLED01(){
        return CONTROLLER.setRxTxLed01;
    }
    public static void setLED01(String setLed01){
        CONTROLLER.setRxTxLed01 = setLed01;
    }
    public static void setMySqlLed01(String setLed01){
        CONTROLLER.setMySqlLed01 = setLed01;
    }
    //
    public static String getLED02(){
        return CONTROLLER.setRxTxLed02;
    }
    public static void setLED02(String setLed02){
        CONTROLLER.setRxTxLed02 = setLed02;
    }
    public static void setMySqlLed02(String setLed02){
        CONTROLLER.setMySqlLed02 = setLed02;
    }
    //
    public static String getLED03(){
        return CONTROLLER.setRxTxLed03;
    }
    public static void setLED03(String setLed03){
        CONTROLLER.setRxTxLed03 = setLed03;
    }
    public static void setMySqlLed03(String setLed03){
        CONTROLLER.setMySqlLed03 = setLed03;
    }
    //
    public static String getServo180Position1(){
        return CONTROLLER.servo180Position1;
    }
    public static void setServo180Position1(String servo180Position1){
        CONTROLLER.servo180Position1 = servo180Position1;
    }
    //
    public static String getServo180Position2(){
        return CONTROLLER.servo180Position2;
    }
    public static void setServo180Position2(String servo180Position2){
        CONTROLLER.servo180Position2 = servo180Position2;
    }
    //
    public static String getServo180Duration1(){
        return CONTROLLER.servo180Duration1;
    }
    public static void setServo180Duration1(String servo180Duration1){
        CONTROLLER.servo180Duration1 = servo180Duration1;
    }
    //
    public static String getServo180Duration2(){
        return CONTROLLER.servo180Duration2;
    }
    public static void setServo180Duration2(String servo180Duration2){
        CONTROLLER.servo180Duration2 = servo180Duration2;
    }
    //
    public static String getServo360Speed1(){
        return CONTROLLER.servo360Speed1;
    }
    public static void setServo360Speed1(String servo360Speed1){
        CONTROLLER.servo360Speed1 = servo360Speed1;
    }
    //
    public static String getServo360Speed2(){
        return CONTROLLER.servo360Speed2;
    }
    public static void setServo360Speed2(String servo360Speed2){
        CONTROLLER.servo360Speed2 = servo360Speed2;
    }
    //
    public static String getServo360Duration1(){
        return CONTROLLER.servo360Duration1;
    }
    public static void setServo360Duration1(String servo360Duration1){
        CONTROLLER.servo360Duration1 = servo360Duration1;
    }
    //
    public static String getServo360Duration2(){
        return CONTROLLER.servo360Duration2;
    }
    public static void setServo360Duration2(String servo360Duration2){
        CONTROLLER.servo360Duration2 = servo360Duration2;
    }
    //
    public static int getReadingsInterval(){
        return CONTROLLER.readingsInterval;
    }
    public static void setReadingsInterval(int readingsInterval){
        CONTROLLER.readingsInterval = readingsInterval;
    }
    //
    public static int getPositionsInterval(){
        return CONTROLLER.positionsInterval;
    }
    public static void setPositionsInterval(int positionsInterval){
        CONTROLLER.positionsInterval = positionsInterval;
    }
    //
    public static int getBlocksToRead(){
        return CONTROLLER.blocksToRead;
    }
    public static void setBlocksToRead(int blocksToRead){
        CONTROLLER.blocksToRead = blocksToRead;
    }


    public static boolean getMySqlPending(){
        boolean pending = false;
        if (!setMySqlLed01.equals("null") | !setMySqlLed02.equals("null") | !setMySqlLed03.equals("null")){
            pending = true;
        }
        return pending;
    }
    //............................................................

    //..................................................................................................................
}