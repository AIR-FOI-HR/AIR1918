package hr.foi.a2fit4u;


import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    String IP, DB, DBUserName, DBPassword;
    @SuppressLint("NewApi")
    public  Connection connections(){
        //Spajanje na bazu
        //IP = "flax.arvixe.com"
        IP = "23.91.70.104";
        DB = "2FIT4YOU";
        DBUserName = "AIR";
        DBPassword = "AIR2019";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + IP + ";databaseName="+ DB + ";user=" + DBUserName + ";password=" +DBPassword + ";";
            connection = DriverManager.getConnection(ConnectionURL);

        }catch(SQLException se){
            Log.e("Greška SQL",se.getMessage());}
        catch (ClassNotFoundException e){Log.e("Ne postoji klasa", e.getMessage());}
        catch (Exception ex){Log.e("Exception error", ex.getMessage());}
        return connection;
    }
}
