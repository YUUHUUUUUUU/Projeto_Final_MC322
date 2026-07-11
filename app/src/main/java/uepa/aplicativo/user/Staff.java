package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.constantes.*;
import uepa.aplicativo.extracurricular.Extracurricular;

import java.util.ArrayList;

public class Staff extends User{
    private Role role;
    private List<Extracurricular> extras = new ArrayList<Extracurricular>();

    Staff(String email,String name){
        super(email,name);
    }

    public void announcement(Extracurricular e){

    }

    public void editExtra(Extracurricular e){

    }
}
