package hr.foi.a2fit4u;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSucess = false;

    public Map<String, String> getData(){
        Map<String, String> data = new HashMap<String, String>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                Log.d("DARIO", "Greška sa spajanjem na bazu");
                ConnectionResult = "Provjerite internetsku vezu!";
            }
            else{
                Log.d("DARIO","Izvršavam upit");
                String query = "SELECT UserName FROM [User]";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);

                while(rs.next()){
                    String user = rs.getString("UserName");
                    Log.d("DARIO", user);
                    data.put("Korisnicko ime", user);

                }
                ConnectionResult = "Successful";
                isSucess = true;
                connect.close();
            }

        }catch (Exception ex){
            isSucess = false;
            ConnectionResult = ex.getMessage();
        }
        return data;
    }


}
