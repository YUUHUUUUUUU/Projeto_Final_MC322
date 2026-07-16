package uepa.aplicativo.user;
import uepa.aplicativo.constants.Role;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

public class Staff extends User implements notify{
    private Role role;
    private Extracurricular e;


    public Staff(String email, String name, Extracurricular extracurricular, String photopath){
        super(email,name,photopath);
    }

    @Override
    public void notifyListeners(String title, String text){
        Message m = new Message(title, text, e, this);
        for (int i = 0; i < e.getUsersListeners().size(); i++) {
            e.getUsersListeners().get(i).receiveMessage(m);
        }
    }

    public void editExtra(Extracurricular e){
        
    }
}
