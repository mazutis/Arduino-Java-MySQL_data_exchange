package SENSORS;

import com.fazecast.jSerialComm.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**DEFAULT READ COM PORT STRING FORMAT:
 * A21.60,B21.50,C60.30,D66.10,E11,F153
 *
 * AVAILABLE WRITE STRING TO COM PORT COMMANDS:
 * LED1ON  LED1OFF  LED2ON  LED2OFF  LED3ONN  LED3OFF
 * SERVO180,0000,0000,0000,0000 <position1,duration1,postition2,duration2>
 * SERVO360,0000,0000,0000,0000 <speed1,duration1,speed2,duration2>
 *
 * <settings> of commands in SENSORS.ino file
 */

public class RxTx {
    static String serialPortString;


    //LIST<Sting> OF AVAILABLE PORTS....................................................................................
    List<String> getPorts(){
        SerialPort ports[] = SerialPort.getCommPorts();
        List<String> portList = new ArrayList<>();
        int portNumber = 1;

        for (SerialPort port : ports) {
            portList.add(portNumber + ". "+ port.getDescriptivePortName());
            portNumber++;
        }
        return portList;
    }


    //CONNECT TO SELECTED PORT..........................................................................................
    public void connectToPort(SerialPort userPort){
        userPort.openPort();
        if (userPort.isOpen()) {
            System.out.println("Connected to: " + userPort.getSystemPortName());
        } else {
            System.out.println("Port not available");
            return;
        }
    }


    //READ DATA FROM PORT CONTINUOUSLY..................................................................................
    public String readPortContinuously(SerialPort userPort) {
        userPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[userPort.bytesAvailable()];
                userPort.readBytes(newData, newData.length);

                serialPortString = new String(newData);
                System.out.print(serialPortString);
            }
        });
        return serialPortString;
    }


    //READ n BLOCKS OF DATA FROM PORT...................................................................................
    public String readPortBlocks(SerialPort userPort, int blocksToRead){
        String readBlocks = "";
        userPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        InputStream in = userPort.getInputStream();
        try
        {
            for (int j = 0; j < blocksToRead; ++j){
                readBlocks = readBlocks + ((char)in.read());
            }
            in.close();
            System.out.println("Data received from " + userPort.getSystemPortName() + " port.");
        } catch (Exception e) {
            e.printStackTrace();
            new Log(e);
        }
        return readBlocks;
    }

    //WRITE TO PORT.....................................................................................................
    public static void sendCommand(SerialPort userPort, String command){
        try
        {
            OutputStream stream = userPort.getOutputStream();
            stream.write(command.getBytes()); // Write to serial
            stream.flush();
            System.out.println("Command to port "+userPort.getSystemPortName()+ " sent: "+command+" "+command.getBytes()); // TEST TRACK
        }
        catch (java.io.IOException IOexception){
            System.out.println("RxTx connection lost..");
            new Log(IOexception);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            new Log(e);
        }

    }
    //..................................................................................................................
    //WRITE TO PORT <including safetyDelayTime, to ensure, that arduino code has been initialized for the first run>....
    public void sendCommandSafeMode(SerialPort userPort, String command){

        Thread sendCommandSafeMode = new Thread() {
            public void run() {
                try
                {
                OutputStream stream = userPort.getOutputStream();
                sleep(3000); //safetyDelayTime to initialize arduino code before commands is sent        <settings>
                stream.write(command.getBytes()); // Write to serial
                stream.flush();
                System.out.println("Data to port "+userPort.getSystemPortName()+ " sent. "+command+" "+command.getBytes());
                }
                catch (java.io.IOException IOexception){
                    System.out.println("RxTx connection lost..");
                    new Log(IOexception);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    new Log(e);

                }
            }
        };
        sendCommandSafeMode.start();
    }


    //DISCONNECT FROM SELECTED PORT.....................................................................................
    public void closePort(SerialPort userPort){
        userPort.closePort();
    }




}