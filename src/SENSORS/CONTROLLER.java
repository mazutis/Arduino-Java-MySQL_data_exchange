package SENSORS;

import com.fazecast.jSerialComm.SerialPort;

public class CONTROLLER {
    private MySQLQueries query = new MySQLQueries();
    private StringToVariables strToVar = new StringToVariables();
    private static RxTx rxtx = new RxTx();
    private static SerialPort userPort = SerialPort.getCommPort("COM5");                                    //<settings>
    private static int readingsInterval = 600_000; //time[ms] interval to send sensors readings to MySQL    //<settings>
    private static int positionsInterval =001_000; //time[ms] interval to send positions to ARDUINO         //<settings>
    private static int blocksToRead = 200; // blocks size to read received from arduino via RxTx port       //<settings>

    private double temp1  = -999;
    private double temp2  = -999;
    private double hum1   = -999;
    private double hum2   = -999;
    private double sonic1 = -999;
    private double sonic2 = -999;
    static String led01   = "OFF";
    static String led02   = "OFF";
    static String led03   = "OFF";
    static String servo180Position1 = "0015";  // RANGE 0000-0180
    static String servo180Position2 = "0015";  // RANGE 0000-0180
    static String servo180Duration1 = "0000";  // Milliseconds
    static String servo180Duration2 = "0300";  // Milliseconds
    static String servo360Speed1    = "1450";  // 1000 - counter clockwise; 2000 - clockwise
    static String servo360Speed2    = "1450";  // 1450 = Stop
    static String servo360Duration1 = "0300";  // Milliseconds
    static String servo360Duration2 = "0300";  // Milliseconds


    // MAIN.............................................................................................................
    public static void main(String [] args){
        CONTROLLER controller = new CONTROLLER();
        rxtx.connectToPort(userPort);
        controller.setupArduino.start(); // Thread 0 start
        }


    // SETUP AND START infinite xDataEchange Threads....................................................................
    Thread setupArduino = new Thread() {
        public void run() {
            try {
                sleep(5000);//safetyDelayTime to initialize RxTx connection
                rxtx.sendCommand(userPort, "LED01ON"); // LED01ON == ARDUINO connected to JAVA
                sleep(5000);
                System.out.println("Thread 0 starting..");
                positionsDataExchange.start();  // Thread 1 loop start
                readingsDataExchange.start();   // Thread 2 loop start

            } catch (Exception e) {
                e.printStackTrace();
                new Log(e);
            }
        }
    };


    // EXCHANGE DATA read MySQL send to RxTx............................................................................
    Thread positionsDataExchange = new Thread(){
        public void run() {
            for (; ; ) {
                try {
                    System.out.println("Thread 1 starting..");                                                          // test tracking

                //collect data from MySQL table: positions
                query.getPositions();
                sleep(5000); //safetyDelayTime to receive data from MySQL

                    System.out.print("Updated MySQL positions:") ;                                                      // test tracking
                    System.out.print("|" + led01);
                    System.out.print("|" + led02);
                    System.out.print("|" + led03);
                    System.out.print("|" + servo180Position1);
                    System.out.print("|" + servo180Duration1);
                    System.out.print("|" + servo180Position2);
                    System.out.print("|" + servo180Duration2);
                    System.out.print("|" + servo360Speed1);
                    System.out.print("|" + servo360Duration1);
                    System.out.print("|" + servo360Speed2);
                    System.out.println("|" + servo360Duration2);

                //send data to RxTx
                rxtx.sendCommand(userPort, "LED01" + led01);
                sleep(5000);//safetyDelayTime

                rxtx.sendCommand(userPort, "LED02" + led02);
                sleep(5000);//safetyDelayTime

                rxtx.sendCommand(userPort, "LED03" + led03);
                sleep(5000);//safetyDelayTime

                rxtx.sendCommand(userPort, "SERVO180,"+ servo180Position1 + "," + servo180Duration1+
                                                            ","+ servo180Position2 + "," + servo180Duration2);
                sleep(9000);//safetyDelayTime

                rxtx.sendCommand(userPort, "SERVO360,"+servo360Speed1 + "," +servo360Duration1+
                                                            ","+servo360Speed2 + "," +servo360Duration2);
                sleep(9000);//safetyDelayTime*/

                sleep(positionsInterval);
                } catch (Exception e) {
                    e.printStackTrace();
                    new Log(e);
                }
            }
        }
    };

    // EXCHANGE DATA read RxTx send to MySQL............................................................................
    Thread readingsDataExchange = new Thread(){
        public void run(){
            for (; ; ) {
                try {
                    System.out.println("Thread 2 starting..");                                                          // test tracking

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
                query.sendReadings(temp1, hum1, temp2, hum2, sonic1, sonic2);

                sleep(readingsInterval);
                } catch (Exception e) {
                    e.printStackTrace();
                    new Log(e);
                }
            }
        }
    };
    //..................................................................................................................
}