package uepa.aplicativo.user;
import uepa.aplicativo.constants.Role;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

public class Staff extends User implements notify{
    private Role role;
    private Extracurricular e;

    public Staff(String email,String name){
        super(email,name);
    }
    @Override
    public void notifyListeners(Message m){
        for (int i = 0; i < e.getUsersFollowing().size(); i++) {
            e.getUsersFollowing().get(i).receiveMessage(m);
        }
    }

    public void editExtra(Extracurricular e){

    }
}
