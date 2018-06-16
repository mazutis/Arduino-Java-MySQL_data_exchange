package SENSORS;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Log {

    public Log(Exception ex){
        this(getTime(), ex);
    }

    public Log(String time, Exception ex){
        try{
            FileOutputStream fos = new FileOutputStream(new File("log.txt"), true);

            PrintStream ps = new PrintStream(fos);
            ps.println(time);
            ex.printStackTrace(ps);
            fos.flush();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static String getTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter shortM = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String dateTime = shortM.format(localDateTime);
        return dateTime;
    }
}
