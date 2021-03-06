package com.example.core.managers;

import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;

import com.example.core.db.User;
import com.example.core.items.HipItem;
import com.example.core.items.MeasurementItem;
import com.example.core.items.NFCItem;
import com.example.core.items.NeckItem;
import com.example.core.items.WaistItem;
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
    public void InsertData(int dataType, String value, String name)
    {
        CheckConnection();

        try
        {
            if(connection==null)
            {
                Log.d("DBManager", "Greška prilikom spajanja na bazu");
            }
            else
            {
                String query = "INSERT INTO Nfc (ID_User, Name, NfcTag) VALUES ('"+AccountManager.getInstance().getUser().getId() + ""
                        +"','"+name+"','"+value+"')";
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                if(rs.rowInserted())
                {
                    Log.d("DBManager", "Uspješno dodan uređaj");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NFCItem> getNFCData()
    {
        List<NFCItem> nfcItemList = new ArrayList<>();
        CheckConnection();

        try
        {
            if(connection==null)
            {
               Log.d("DBManager", "Greška prilikom spajanja na bazu");
            }
            else
            {
                String query = "SELECT * FROM Nfc WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "'";
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                while(rs.next())
                {
                    String name = rs.getString("Name");
                    String tag = rs.getString("NfcTag");

                    NFCItem newItem = new NFCItem(name,tag);

                    nfcItemList.add(newItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return nfcItemList;
    }

    public void DeleteData(String tag)
    {
        CheckConnection();

        String[] cleanTag = tag.split(" ");


        try
        {
            if(connection==null)
            {
                Log.d("DBManager","Greška");
            }
            else
            {
                Log.d("DBManager","Brisanje: TAG " + cleanTag[1]);
                String query = "DELETE FROM Nfc WHERE NfcTag = '" + cleanTag[1] + "'";
                Log.d("DBManager ","Query: "+query);
                Statement statement = connection.createStatement();

                if(statement.execute(query))
                {
                    Log.d("DBManager","UPIT?");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public List<MeasurementItem> getMeasurementData(){
        List<MeasurementItem> measurementItemList = new ArrayList<>();


        try {

            if(connection == null)
            {
                Log.d("DBManager","Greška prilikom spajanja na bazu");
            }
            else{
                String queryWaist = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_WAIST + "'";
                String queryHip = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_HIPS + "'";
                String queryNeck = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_NECK + "'";

                Statement statWaist = connection.createStatement();
                ResultSet rsWaist = statWaist.executeQuery(queryWaist);


                Log.d("DBManager", "Upit izvršen");
                while (rsWaist.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String waistValue = rsWaist.getString("Value");
                    String date = rsWaist.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + waistValue);

                    MeasurementItem measurementItem = new MeasurementItem("Waist",waistValue,date);

                    measurementItemList.add(measurementItem);
                }

                Statement statHips = connection.createStatement();
                ResultSet rsHip = statHips.executeQuery(queryHip);


                while (rsHip.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String hipValue = rsHip.getString("Value");
                    String date = rsHip.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + hipValue);

                    MeasurementItem measurementItem = new MeasurementItem("Hips",hipValue,date);

                    measurementItemList.add(measurementItem);
                }

                Statement statNeck = connection.createStatement();
                ResultSet rsNeck = statNeck.executeQuery(queryNeck);

                while (rsNeck.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String neckValue = rsNeck.getString("Value");
                    String date = rsNeck.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + neckValue);

                    MeasurementItem measurementItem = new MeasurementItem("Neck",neckValue,date);

                    measurementItemList.add(measurementItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return measurementItemList;

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

    public boolean checkEntity(int entityType, String value)
    {
        CheckConnection();

        try
        {
            if(connection==null)
            {
                Log.d("DBManager","Greška prilikom spajanja na bazu");
            }
            else
            {
                String query = "";
                switch (entityType)
                {
                    case Constants.DATA_TYPE_NFC:
                        query = "SELECT * FROM Nfc WHERE NfcTag = '" + value + "'";
                        break;
                }

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if(resultSet.next())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void closeDB()
    {
        try{
            connection.close();
        } catch (SQLException ex) {
            Log.d("DBManager", ex.getMessage());
        }
    }

    public List<WaistItem> getWaistData() {
        List<WaistItem> waistItemList = new ArrayList<>();

        try {

            if(connection == null)
            {
                Log.d("DBManager","Greška prilikom spajanja na bazu");
            }
            else{
                String query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_WAIST + "'";

                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                Log.d("DBManager", "Upit izvršen");
                while (rs.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String waist = rs.getString("Value");
                    String date = rs.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + waist);

                    WaistItem waistItem = new WaistItem(waist,date);

                    waistItemList.add(waistItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return waistItemList;
    }

    public List<HipItem> getHipData() {
        List<HipItem> hipItemList = new ArrayList<>();

        try {

            if(connection == null)
            {
                Log.d("DBManager","Greška prilikom spajanja na bazu");
            }
            else{
                String query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_HIPS + "'";

                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                Log.d("DBManager", "Upit izvršen");
                while (rs.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String hips = rs.getString("Value");
                    String date = rs.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + hips);

                    HipItem hipItem = new HipItem(hips,date);

                    hipItemList.add(hipItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hipItemList;
    }

    public List<NeckItem> getNeckData() {
        List<NeckItem> neckItemList = new ArrayList<>();

        try {

            if(connection == null)
            {
                Log.d("DBManager","Greška prilikom spajanja na bazu");
            }
            else{
                String query = "SELECT * FROM UserMeasure WHERE ID_User = '" + AccountManager.getInstance().getUser().getId() + "' " +
                        "AND ID_Measures = '" + Constants.DATA_TYPE_NECK + "'";

                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery(query);

                Log.d("DBManager", "Upit izvršen");
                while (rs.next()) {

                    Log.d("DBManager", "Pronađen korisnik!");

                    String neck = rs.getString("Value");
                    String date = rs.getString("Date");

                    Log.d("DBManager", "Date: " + date + " Value: " + neck);

                    NeckItem neckItem = new NeckItem(neck,date);

                    neckItemList.add(neckItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return neckItemList;
    }
}
