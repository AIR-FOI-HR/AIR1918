#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
 
const char* ssid = "ExperA_1";
const char* password = "ExperA042";

int weight = 0;
String tag = "12341234";


 
void setup () {
 
  Serial.begin(115200);
  WiFi.begin(ssid, password);

  Serial.println("");
  Serial.println("Connected");
  Serial.println(ssid);
 
  while (WiFi.status() != WL_CONNECTED) {

  delay(1000);
  Serial.println("Connecting....");
 
  }

  Serial.print("Connected to ");
  Serial.println(ssid);
 
}
 
void loop() {

 if (Serial.available() > 0) 
 {
  
 
    Serial.println("Cekam...");
    byte b1 = Serial.read();
    byte b2 = Serial.read();

    weight = b1 * 256 + b2;
    
    Serial.println(weight);
    delay(1000);
    
 

  if(weight != 0 && tag != "")
  {
    Serial.print("Dobio sam!");
    Serial.println(weight);
    


    if (WiFi.status() == WL_CONNECTED && tag != "" && weight != 0) { //Check WiFi connection status
   Serial.println("Saljem...");
    HTTPClient http;  //Declare an object of class HTTPClient
     
    http.begin("http://www.pamtim.com/Weight/Insert/?Data=" + tag + "-" + String(weight) + "-1341996");  //Specify request destination
    int httpCode = http.GET();                                                                  //Send the request

    delay(3000);

    Serial.println(httpCode);
     
    if (httpCode > 0) { //Check the returning code
     
      String payload = http.getString();   //Get the request response payload
      Serial.println(payload);                     //Print the response payload
       Serial.println("Poslano!");
    }
    else{
      Serial.println("Zajeb...");
    }
   
    http.end();   //Close connection

    

    weight = 0;
   Serial.println("Obrisani podaci!");
    }   
  }
 }
   
}
