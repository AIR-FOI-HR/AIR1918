#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
 
const char* ssid = "FTezak";
const char* password = "12341234";

String data="";
char rc;

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

 

 if (Serial.available()) 
 {
  delay(20);
  while(Serial.available())
{
  rc = Serial.read();
  data.concat(rc);
}
Serial.println(data);

 }
 
  if(data != "")
  {
    Serial.print("Dobio sam!");
    Serial.println(data);
    
    if (WiFi.status() == WL_CONNECTED && data != "") { 
   Serial.println("Saljem...");
    HTTPClient http;  
     
    http.begin("http://pamtim.com/Weight/insert/?data=" + data);  

    int httpCode = http.GET();  // pošalji zahtjev

    delay(3000);

    Serial.println(httpCode);
     
    if (httpCode > 0) { 
     
      String payload = http.getString();   
      Serial.println(payload);                     
       Serial.println("Poslano!");
    }
    else{
      Serial.println("Greška");
    }
   
    http.end();   //Zatvori konekciju

    data = "";
   Serial.println("Obrisani podaci!");
    }   
  }
 }
   

