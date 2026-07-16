package uepa.aplicativo.DataManager;

import java.util.ArrayList;
import java.util.List;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.user.User;

public class Data {

    private List<User> userList;
    private List<Extracurricular> extracurricularList;

    public Data() {
        userList = new ArrayList<>();
        extracurricularList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Extracurricular> getExtracurricularList() {
        return extracurricularList;
    }

    public void addUser(User user) {
        if(user != null){
            userList.add(user);
        }
    }

    public void addExtracurricular(Extracurricular extra) {
        if(extra != null) {
            extracurricularList.add(extra);
        }
    }
}
