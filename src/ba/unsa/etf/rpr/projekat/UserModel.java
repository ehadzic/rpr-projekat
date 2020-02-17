package ba.unsa.etf.rpr.projekat;

import java.util.ArrayList;

public class UserModel {
    private ArrayList<Login> userList;
    private EIndexDAO database;

    public UserModel() {
        database = EIndexDAO.getInstance();
        userList = database.getUsersLogin();
    }

    public ArrayList<Login> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<Login> userList) {
        this.userList = userList;
    }
}
