package hr.foi.a2fit4u;

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

    /*public List<Map<String, String>> getData(){
        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String,String>>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                ConnectionResult = "Provjerite internetsku vezu!";
            }
            else{
                String query = "Select UserName from User";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);

                while(rs.next()){
                    Map<String,String> mapa = new HashMap<String, String>();
                    mapa.put("Korisnicko ime", rs.getString("UserName"));
                    data.add(mapa);
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
    }*/
}
