package SENSORS;

/**
 * Default String format: A21.60,B21.50,C60.30,D66.10,E11,F153, where value symbols:
 * A - temperature1
 * B - temperature2
 * C - humidity1
 * D - humidity2
 * E - ultrasonicDistance1
 * F - ultrasonicDistance2
 * Defaut -999 if no data found
 */

public class StringToVariables {

    public double getTemp1(String serialPortString){
        return getValue(serialPortString, 'A');                                                  //<settings>
    }

    public double getTemp2(String serialPortString){
        return getValue(serialPortString, 'B');                                                  //<settings>
    }

    public double getHum1(String serialPortString){
        return getValue(serialPortString, 'C');                                                  //<settings>
    }

    public double getHum2(String serialPortString){
        return getValue(serialPortString, 'D');                                                  //<settings>
    }

    public double getSonicLength1(String serialPortString){
        return getValue(serialPortString, 'E');                                                  //<settings>
    }

    public double getSonicLength2(String serialPortString){
        return getValue(serialPortString, 'F');                                                  //<settings>
    }

    public int getLED(String ledCommand){
        int value = 0;
            if (ledCommand.equals("LED01ON")||ledCommand.equals("LED02ON")){
                value = 1;
            }
        return value;
    }


    private double getValue(String serialPortString, char valueSymbol){
        double value = -999;// default value -999 for <no data found>
            for (int i = 30; i < serialPortString.length(); i++){//find value between letter '?' and comma            //FIXME very first byes of string missing? double check incoming data? now reading just from symbol i=30
            if (serialPortString.charAt(i) == valueSymbol){
                for (int j = i; j < serialPortString.length(); j++){
                    if (serialPortString.charAt(j) == ',' && serialPortString.charAt(j+1) == ++valueSymbol) {
                        if(!"nan".equals(serialPortString.substring(i+1,j))){ //filtering missing data
                            value = Double.parseDouble(serialPortString.substring(i+1,j));
                        }
                        break;
                    }
                }
                break;
            }
        }
        return value;
    }
}