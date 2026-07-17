package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.constants.Role;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

public class Staff extends User implements notify{
    private Extracurricular e;

    public Staff(String name, String email, String password, String photoPath){
        super(name, email, password, photoPath);
        role = Role.STAFF;
    }

    public Staff(String name, String email, String password, String photoPath, List<Message> mailBox, String idString){
        super(name, email, password, photoPath, mailBox, idString);
        role = Role.STAFF;
    }

    @Override
    public void notifyListeners(String title, String text){
        Message m = new Message(title, text, getName());
        for (int i = 0; i < e.getUsersListeners().size(); i++) {
            e.getUsersListeners().get(i).receiveMessage(m);
        }
    }
}
