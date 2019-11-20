int weight = 50;
String data;
const int buttonPin = 8;

void setup() {
  
  Serial.begin(115200);
  pinMode(buttonPin, INPUT);   
  
}

void loop() {


  if (digitalRead(buttonPin) == HIGH) {
    
    
  
        weight += 1;
        Serial.write(weight / 256);
        Serial.write(weight % 256);
        
    delay(3000);
  }
  
  




}
