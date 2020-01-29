package hr.foi.a2fit4u;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import hr.foi.a2fit4u.ConnectionHelper;

public class SendData {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSucess = false;

    String datum = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(new Date());

    public void insertNewMeasurements(int userID, int measureID, float vrijednost){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                Log.d("Send data", "Greška prilikom unosa novih mjera");
            }
            else{
                String query = "INSERT INTO [UserMeasure] (ID_User, ID_Measures, Date, Value) VALUES ('"+userID+"','"+measureID+"','"+datum+"','"+vrijednost+"')";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);

                ConnectionResult = "Successful";
                isSucess = true;

                if(rs.rowInserted()){
                    Log.d("Send data", "Unesena nova mjera");
                }

            }
        }catch (Exception ex){
            isSucess = false;
            ConnectionResult = ex.getMessage();
        }
    }

}
