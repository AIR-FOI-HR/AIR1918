#include <LiquidCrystal.h>

LiquidCrystal lcd(8, 7, 6, 5, 4, A5);

#include <SPI.h>
#include <MFRC522.h>
#include "HX711.h"

#define SS_PIN 10
#define RST_PIN 9
MFRC522 mfrc522(SS_PIN, RST_PIN);

#define calibration_factor -23000.0 //Vrijednost za kalibraciju vage
#define DOUT  3
#define CLK  2

HX711 scale;

String NFC = "";
bool posalji = false;
float tezina = 00.0;
String podaci = "";

bool izmjerenaTezina = false;

float x, x1, x2, x3, x4;

void setup() {

  Serial.begin(115200);

  lcd.begin(16, 2);

  SPI.begin();      
  mfrc522.PCD_Init();   

  scale.begin(DOUT, CLK);
  scale.set_scale(calibration_factor); 
  scale.tare(); //Rasetiraj vagu na nulu sa pretpostavkom da na vaki nema težine prilikom pokretanja

}

void loop() {

  if (izmjerenaTezina == false)
  {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.write("Stanite na vagu");
  }

ponovi:

  if (scale.get_units() > 5 && izmjerenaTezina == false) {

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.write("Mjerim");
    
    x1 = scale.get_units();
    delay(500);
    if (x1 < ((scale.get_units()) - 1) || x1 > ((scale.get_units()) + 1)) goto ponovi;
    x1 = scale.get_units();
    
    delay(500);
    if (x1 < ((scale.get_units()) - 1) || x1 > ((scale.get_units()) + 1)) goto ponovi;
    x1 = scale.get_units();

    delay(500);
    if (x1 < ((scale.get_units()) - 1) || x1 > ((scale.get_units()) + 1)) goto ponovi;
    x1 = scale.get_units();

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.write("Mjerim---");

    //Serial.print(x1, 1);
    //Serial.print(" kg");
    //Serial.println();
    delay(700);
    if (scale.get_units() < (x1 - 1) || scale.get_units() > (x1 + 1)) goto ponovi;
    x2 = scale.get_units();

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.write("Mjerim------");
    
    //Serial.print(x2, 1);
    //Serial.print(" kg");
    //Serial.println();
    delay(700);
    if (scale.get_units() < (x2 - 1) || scale.get_units() > (x2 + 1)) goto ponovi;
    x3 = scale.get_units();

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.write("Mjerim---------");
    
    //Serial.print(x3, 1);
    //Serial.print(" kg");
    //Serial.println();
    delay(700);
    if (scale.get_units() < (x3 - 1) || scale.get_units() > (x3 + 1)) goto ponovi;
    x4 = scale.get_units();

    x = (x1 + x2 + x3 + x4) / 4;

    //Serial.print("Tezina: ");
    //Serial.print(scale.get_units(), 1); //scale.get_units() returns a float
    //Serial.println(" kg"); //You can change this to kg but you'll need to refactor the calibration_factor
    //Serial.println();

    tezina = scale.get_units();
    
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print((String)tezina + " kg");
    lcd.setCursor(0, 2);
    lcd.write("Prisloni NFC!");
    
    izmjerenaTezina = true;
  }

  if (scale.get_units() < 3 && izmjerenaTezina == true) {
    tezina = 0.0;
    izmjerenaTezina = false;
    NFC = "";
  }

  if (izmjerenaTezina == true && NFC == "")
  {

    // Look for new cards
    if ( ! mfrc522.PICC_IsNewCardPresent())
    {
      return;
    }
    // Select one of the cards
    if ( ! mfrc522.PICC_ReadCardSerial())
    {
      return;
    }

    //Show UID on serial monitor
    
    String content = "";
    byte letter;
    for (byte i = 0; i < mfrc522.uid.size; i++)
    {
      //Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
      //Serial.print(mfrc522.uid.uidByte[i], HEX);
      content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
      content.concat(String(mfrc522.uid.uidByte[i], HEX));
      content.replace(" ", "");
    }

    NFC = content;

    lcd.setCursor(0, 2);
    lcd.print("NFC: " + NFC + "                  ");

    posalji = true;
  }

  if (izmjerenaTezina == true && NFC != "" && posalji == true)
  {
    podaci = NFC + "-weight-" + tezina;

    posaljiPodatke();
  }
}

void posaljiPodatke()
{
  Serial.println(podaci);
  posalji = false;
}
