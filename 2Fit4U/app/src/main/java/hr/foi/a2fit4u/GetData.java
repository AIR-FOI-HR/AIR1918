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

    public Boolean checkUser(String username, String password)
    {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                Log.d("GetData", "Greška sa spajanjem na bazu");

            }
            else{
                Log.d("GetData","Izvršavam upit");
                String query = "SELECT UserName,Password FROM [User] WHERE UserName = '"+ username+"'";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);

                ConnectionResult = "Successful";
                isSucess = true;

                Log.d("GetData", "Upit izvršen");
                if(rs.next())
                {
                    Log.d("GetData","Pronađen korisnik!");
                    String userPassword = rs.getString("Password");
                    if(userPassword.equals(password)){
                        connect.close();
                        return true;
                    }
                }
                connect.close();
                return false;

            }

        }catch (Exception ex){
            Log.d("GetData","Greška! "+ ex.getMessage());
            isSucess = false;
            ConnectionResult = ex.getMessage();
        }
        return false;
    }

    public void registerUser(String username, String email, String password){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                Log.d("GetData", "Greška sa spajanjem na bazu");

            }
            else{
                Log.d("GetData","Izvršavam upit");
                String query = "INSERT INTO [User] (UserName, Email, Password) VALUES ('"+username
                        +"','"+email+"','"+password+"')";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);

                ConnectionResult = "Successful";
                isSucess = true;

                if(rs.rowInserted())
                {
                    Log.d("GetData", "Dodan novi korisnik");
                }
                connect.close();

            }

        }catch (Exception ex){
            isSucess = false;
            ConnectionResult = ex.getMessage();
        }
    }

    public float getWeight(int idUser)
    {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                Log.d("GetData", "Greška sa spajanjem na bazu");

            }
            else{
                Log.d("GetData","Izvršavam upit");
                String query = "SELECT UserName,Password FROM [Weight] WHERE ID_user = '"+ idUser+"'";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);
                rs.afterLast();


                while(rs.previous())
                {
                    connect.close();
                    return Float.parseFloat(rs.getString("Weight"));
                }

                ConnectionResult = "Successful";
                isSucess = true;
                connect.close();

            }

        }catch (Exception ex){
            Log.d("GetData","Greška! "+ ex.getMessage());
            isSucess = false;
            ConnectionResult = ex.getMessage();
        }
        return 0;
    }

    public int getUserID(String username)
    {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                Log.d("GetData", "Greška sa spajanjem na bazu");

            }
            else{
                Log.d("GetData","Izvršavam upit");
                String query = "SELECT User_ID FROM [User] WHERE UserName = '"+ username+"'";
                Statement stat = connect.createStatement();
                ResultSet rs = stat.executeQuery(query);

                if(rs.next())
                {
                    connect.close();
                    return Integer.parseInt(rs.getString("User_ID"));
                }

                ConnectionResult = "Successful";
                isSucess = true;
                connect.close();

            }

        }catch (Exception ex){
            Log.d("GetData","Greška! "+ ex.getMessage());
            isSucess = false;
            ConnectionResult = ex.getMessage();
        }
        return 0;
    }
}
