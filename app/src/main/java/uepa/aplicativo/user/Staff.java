package uepa.aplicativo.user;
import uepa.aplicativo.constants.Role;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

public class Staff extends User implements notify{
    private Extracurricular e;


    public Staff(String email, String name, Extracurricular extracurricular, String photopath){
        super(email,name,photopath);
        e=extracurricular;
        setRole(Role.STAFF);
    }

    @Override
    public void notifyListeners(String title, String text){
        Message m = new Message(title, text, e, this);
        for (int i = 0; i < e.getUsersListeners().size(); i++) {
            e.getUsersListeners().get(i).receiveMessage(m);
        }
    }

    // public void editExtraName(String newName){
    //     e.setName(newName);
    // }

    // public void editExtraDesc(String newDesc){
    //     e.setDescription(newDesc);
    // }

    // public void editExtraOpen(boolean newOpen){
    //     e.setOpenToWork(newOpen);
    // }

    // public void editExtraLink(String newHyper){
    //     e.setHyperLink(newHyper);
    // }

    // public void editExtraInstitute(String newInst){
    //     e.setInstitute(newInst);
    // }

    // public void editExtraBanner(String newPath){
    //     e.setBannerPath(newPath);
    // }

    // public void editExtraLogo(String newPath){
    //     e.setLogoPath(newPath);
    // }
}
