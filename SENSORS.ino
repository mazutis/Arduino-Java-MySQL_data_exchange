#include <DHT.h>;                 // Temp and Humidity sensor library
#include <Servo.h>;               // SERVOs library

//Constants
#define DHTPIN1 10                // PIN SENSOR1 is connected to                                <Settings>
#define DHTPIN2 11                // PIN SENSOR2 is connected to                                <Settings>
#define DHTTYPE DHT22             // DHT 22  (AM2302)
#define LED01Pin 2                // PIN LED1 is connected to (RED)                             <Settings>
#define LED02Pin 3                // PIN LED2 is connected to (WHITE)                           <Settings>
#define LED03Pin 4                // PIN LED3 is connected to (BLUE)                            <Settings>
#define sonic1TrigPin 8           // PIN SONIC1 trig                                            <Settings>
#define sonic1EchoPin 9           // PIN SONIC1 echo                                            <Settings>
#define Servo180Pin A1            // SERVO180 Pin                                               <Settings>
#define Servo360Pin A2            // SERVO360 Pin                                               <Settings>

DHT dht1(DHTPIN1, DHTTYPE);       // Initialize DHT sensor 1 for normal 16mhz Arduino
DHT dht2(DHTPIN2, DHTTYPE);       // Initialize DHT sensor 2 for normal 16mhz Arduino
Servo SERVO180;
Servo SERVO360;

//Variables
float hum1, hum2;                 // Stores humidity value
float temp1, temp2;               // Stores temperature value
long sonic1Duration, sonic1Length;

//========================================================================================================
void setup()//Run program once
{
  dht1.begin();                   // Initialize SENSOR1
  dht2.begin();                   // Initialize SENSOR2
  pinMode (LED01Pin, OUTPUT);     // Initialize LED1 output
  pinMode (LED02Pin, OUTPUT);     // Initialize LED2 output
  pinMode (LED03Pin, OUTPUT);     // Initialize LED3 output
  pinMode (sonic1TrigPin, OUTPUT);// Initialize SONIC1 trig
  pinMode (sonic1EchoPin, INPUT); // Initialize SONIC1 echo

  checkNgo();                     // Send signal to LEDs and SERVOs once 
  Serial.begin(9600);             // Output bitrate                                            <settings>                                                              
}


void loop()//Loop program
{
    getSonic();  
    getHumidity();
    getTemp();
  
    serialInputAction();
    setOutputString();
    delay(250);
}

//========================================================================================================
void checkNgo(){//Send signal to LED and SERVOS once 
    digitalWrite(LED01Pin, HIGH);
    delay(200);
    digitalWrite(LED01Pin, LOW);
    
    digitalWrite(LED02Pin, HIGH);
    delay(200);
    digitalWrite(LED02Pin, LOW);
    
    digitalWrite(LED03Pin, HIGH);
    delay(200);
    digitalWrite(LED03Pin, LOW);

    setServo180(0060,0300,0010,0300);
    setServo360(1250,0200,1650,0200);
}
//========================================================================================================
void getHumidity(){//Read humidity and store it to variables
    hum1 = dht1.readHumidity();
    hum2 = dht2.readHumidity();
}
//========================================================================================================
void getTemp(){//Read temperature and store it to variables
    temp1 = dht1.readTemperature();
    temp2 = dht2.readTemperature();
}
//========================================================================================================
void getSonic(){//Read distance and store it to variables
 
  digitalWrite(sonic1TrigPin, LOW);
  delayMicroseconds(5);
  digitalWrite(sonic1TrigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(sonic1TrigPin, LOW);
 
  pinMode(sonic1EchoPin, INPUT);
  sonic1Duration = pulseIn(sonic1EchoPin, HIGH);
 
  // convert the time into a distance
  sonic1Length = (sonic1Duration/2) / 29.1;
}

//========================================================================================================
void setServo180(int position1, int duration1, int position2,  int duration2){//move SERVO180 to position1
    SERVO180.attach(Servo180Pin);                                             //and to position2
    SERVO180.write(position1);
    delay(duration1);
    SERVO180.write(position2);
    delay(duration2);
    SERVO180.detach();
}

//========================================================================================================
void setServo360(int speed1, int duration1, int speed2, int duration2 ){//move SERVO360 at speed and duration
    SERVO360.attach(Servo360Pin);
    SERVO360.writeMicroseconds(speed1);
    delay(duration1); 
    SERVO360.writeMicroseconds(speed2);
    delay(duration2);
    SERVO360.detach();
}
//========================================================================================================
void serialInputAction(){//Read serial and take action up on new data
    String serialInput = Serial.readString();     // Read from serial

    //SERVO180 & SERVO360 Variables
    String shortSerialInput = serialInput;//separate input command from parameters     
    String parameter1;
    String parameter2; 
    String parameter3;
    String parameter4;  
    if (serialInput.length()>8){          
      shortSerialInput = serialInput.substring(0,8);  // separate input parameters              <settings>
      parameter1 = serialInput.substring(9,13);       // separate input parameters              <settings>
      parameter2 = serialInput.substring(14,18);      // separate input parameters              <settings>
      parameter3 = serialInput.substring(19,23);      // separate input parameters              <settings>
      parameter4 = serialInput.substring(24,28);      // separate input parameters              <settings>
    }

    //LED01 ON / OFF                       
    if(serialInput == "LED01ON"){     // input: <LED01ON>                                       <Settings>
    digitalWrite(LED01Pin, HIGH);          
    }
    if(serialInput == "LED01OFF"){    // input: <LED01OFF>                                      <Settings>    
    digitalWrite(LED01Pin, LOW);          
    }

    //LED02 ON / OFF
    if(serialInput == "LED02ON"){     // input: <LED02ON>                                       <Settings>       
    digitalWrite(LED02Pin, HIGH); 
    }
    if(serialInput == "LED02OFF"){    // input: <LED02OFF>                                      <Settings>               
    digitalWrite(LED02Pin, LOW); 
    }

    //LED03 ON / OFF
    if(serialInput == "LED03ON"){     // input: <LED03ON>                                       <Settings>              
    digitalWrite(LED03Pin, HIGH);  
    }
    if(serialInput == "LED03OFF"){    // input: <LED03OFF>                                      <Settings>               
    digitalWrite(LED03Pin, LOW);   
    }
    
    //SERVO180                            
    if(shortSerialInput == "SERVO180"){   // short input: <SERVO180>                            <settings>
      int position1 = parameter1.toInt(); // full input: <SERVO180,0000,0000,0000,0000>         <settings>
      int duration1 = parameter2.toInt();
      int position2 = parameter3.toInt();
      int duration2 = parameter4.toInt();
      setServo180(position1, duration1, position2, duration2);
    }


    //SERVO360                                            
    if(shortSerialInput == "SERVO360"){   // short input: <SERVO360>                            <settings>
      int speed1 = parameter1.toInt();    // full input: <SERVO180,0000,0000,0000,0000>         <settings>
      int duration1 = parameter2.toInt();
      int speed2 = parameter3.toInt();
      int duration2 = parameter4.toInt();
      setServo360(speed1, duration1, speed2, duration2 );
    }
  
}
//========================================================================================================
void setOutputString(){//Sending data to serial (Comma-separated values)         
    Serial.print("A");                                      
    Serial.print(temp1);                                                                                   
    Serial.print(",B");
    Serial.print(temp2);
    Serial.print(",C");
    Serial.print(hum1);
    Serial.print(",D");
    Serial.print(hum2);
    Serial.print(",E");
    Serial.print(sonic1Length);
    Serial.print(",F-999");
    //Serial.print(sonic2Length); -NOT AVAILABLE
    Serial.println(",G");
    Serial.flush();                      //Waits for the transmission of outgoing serial data to complete
    //Serial.println();
}
//========================================================================================================
