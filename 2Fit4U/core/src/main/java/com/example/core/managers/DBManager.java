package com.example.core.managers;

import android.os.StrictMode;
import android.util.Log;

import com.example.core.db.User;
import com.example.core.items.WeightItem;
import com.example.core.util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.core.util.*;

public class DBManager {
    private static final DBManager dbInstance = new DBManager();

    public static DBManager getInstance() { return dbInstance;}

    private Connection connection;

    private DBManager()
    {
        Connect();
    }

    private void Connect()
    {
        //TODO
        // spoji se na bazu ovdje
        String IP = "23.91.70.104";
        String DB = "2FIT4YOU";
        String DBUserName = "AIR";
        String DBPassword = "AIR2019";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + IP + ";databaseName="+ DB + ";user=" + DBUserName + ";password=" +DBPassword + ";";
            connection = DriverManager.getConnection(ConnectionURL);
            Log.d("ConnectionHelper", "BAZA JE SPOJENA");

        }catch(SQLException se){
            Log.e("Greška SQL",se.getMessage());}
        catch (ClassNotFoundException e){Log.e("Ne postoji klasa", e.getMessage());}
        catch (Exception ex){Log.e("Exception error", ex.getMessage());}
    }

    private void CheckConnection()
    {
        try {
            if(connection.isClosed()){
                Connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(int dataType, float value )
    {
        int userID = AccountManager.getInstance().getUser().getId();

        CheckConnection();

        try{
            if(connection == null){
                Log.d("Send data", "Greška prilikom unosa novih mjera");
            }
            else{
                Log.d("DBManager", "Izvrsavam unos...");
                String query = "INSERT INTO UserMeasure (ID_User, ID_Measures, Value) VALUES ('"+userID+"','"+dataType+"','"+value+"')";
                Log.d("DBManager", "Query: " + query);
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                if(rs.rowInserted()){
                    Log.d("Send data", "Unesena nova mjera");
                }

            }
        }catch (Exception ex){
            Log.d("DBManager", "Unos: " + ex.getMessage());
        }
    }

    //NFC insert
    public void InsertData(int dataType, String value)
    {
        CheckConnection();
    }

    //Register user
    public void InsertData(String username, String email, String password)
    {
        CheckConnection();

        try {
            if(connection == null){
                Log.d("DBManager", "Greška sa spajanjem na bazu");

            }
            else{
                Log.d("GetData","Izvršavam upit");
                String query = "INSERT INTO [User] (UserName, Email, Password) VALUES ('"+username
                        +"','"+email+"','"+password+"')";
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                if(rs.rowInserted())
                {
                    Log.d("DBManager", "Dodan novi korisnik");
                }

            }

        }catch (Exception ex){
            Log.d("DBManager", ex.getMessage());
        }

    }

    public String getData(int dataType) {
        CheckConnection();
        //TODO
        // switch ovdje
        // 1 - invalid username
        // 2 - invalid password
        try {
            if (connection == null) {
                Log.d("DBManager", "Greška sa spajanjem na bazu");

            } else {
                Log.d("DBManager", "Izvršavam upit");
                String query = "";
                switch (dataType)
                {
                    case Constants.DATA_TYPE_STARTING_WEIGHT:
                        query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                                "AND ID_Measures = '" + Constants.DATA_TYPE_WEIGHT + "'";
                        break;
                    case Constants.DATA_TYPE_STARTING_NECK:
                        query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                                "AND ID_Measures = '" + Constants.DATA_TYPE_NECK + "'";
                        break;
                    case Constants.DATA_TYPE_STARTING_HIPS:
                        query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                                "AND ID_Measures = '" + Constants.DATA_TYPE_HIPS + "'";
                        break;
                    case Constants.DATA_TYPE_STARTING_WAIST:
                        query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                                "AND ID_Measures = '" + Constants.DATA_TYPE_WAIST + "'";
                        break;
                    default:
                        query = "SELECT TOP 1 * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                                "AND ID_Measures = '" + dataType + "' ORDER BY UserMeasure_ID DESC";

                }
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                Log.d("DBManager", "Upit izvršen");
                if (rs.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    return rs.getString("Value");
                }
                //connection.close();


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No entry";
    }

    public List<WeightItem> getData()
    {
        List<WeightItem> weightItemList = new ArrayList<>();

        try {

            if(connection == null)
            {
                Log.d("DBManager","Greška prilikom spajanja na bazu");
            }
            else{
                String query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_WEIGHT + "'";

                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                Log.d("DBManager", "Upit izvršen");
                while (rs.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String weight = rs.getString("Value");
                    String date = rs.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + weight);

                    WeightItem newWeightItem = new WeightItem(weight,date);

                    weightItemList.add(newWeightItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weightItemList;
    }

        //Get user
    public User getData(String username, String password)
    {
        User user = null;
        CheckConnection();
        try {

            if(connection == null){
                Log.d("DBManager", "Greška sa spajanjem na bazu");

            }
            else{
                Log.d("DBManager","Izvršavam upit");
                String query = "SELECT * FROM [User] WHERE UserName = '"+ username+"'";
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                Log.d("DBManager", "Upit izvršen");
                if(rs.next())
                {

                    Log.d("DBManager","Pronađen korisnik!");
                    String userPassword = rs.getString("Password");
                    if(userPassword.equals(password)){
                        Log.d("DBManager","test 1");
                        user = new User();
                        Log.d("DBManager","test 2");
                        user.setId(Integer.parseInt(rs.getString("User_ID")));
                        Log.d("DBManager","test 3");
                        user.setUsername(rs.getString("UserName"));
                        Log.d("DBManager","test 4");
                        user.setEmail(rs.getString("Email"));
                        Log.d("DBManager","test 5");
                        //user.setActivity(Integer.parseInt(rs.getString("Activity")));
                        Log.d("DBManager","test 6");
                        //user.setCalories(Integer.parseInt(rs.getString("Calories")));
                        Log.d("DBManager","test 7");
                        user.setGender(rs.getString("Sex"));
                        Log.d("DBManager","test 8");
                        user.setCreationDate(new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(rs.getDate("CreationDate")));

                        Log.d("DBManager","test 9");
                        return user;
                    }
                }
                //connection.close();
                return null;

            }

        }catch (Exception ex){
            Log.d("DBManager","Greška! "+ ex.getMessage());
        }
        return null;
    }

    public void closeDB()
    {
        try{
            connection.close();
        } catch (SQLException ex) {
            Log.d("DBManager", ex.getMessage());
        }
    }

}
