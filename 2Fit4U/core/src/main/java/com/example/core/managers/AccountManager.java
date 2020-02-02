package com.example.core.managers;

import com.example.core.db.User;

public class AccountManager {

    private static final AccountManager amInstance = new AccountManager();
    private static User ActiveUser;

    public static AccountManager getInstance() {return amInstance;}

    private AccountManager()
    {

    }

    public User getUser()
    {
        return ActiveUser;
    }

    public void registerUser(String username, String email, String password)
    {
        DBManager.getInstance().InsertData(username,email,password);
    }

    public boolean checkUser(String username, String password)
    {
        User user = DBManager.getInstance().getData(username,password);
        //if null throw toast, invalid user al u mjestu gdje se to poziva

        if(user==null){
            return false;
        }
        else
        {
            ActiveUser = user;
            return true;
        }
    }

    //TODO
    // sendPassword
    public void sendPassword()
    {

    }

    public boolean checkFirstTimeUser()
    {
        return true;
    }

    public void logOut()
    {
        DBManager.getInstance().closeDB();
        ActiveUser = null;
    }


}
