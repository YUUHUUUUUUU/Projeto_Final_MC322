package src;
public enum Role {
    ADMIN("Admin",true,true),
    STAFF("Moderator",true,false),
    STUDENT("Estudante",false,false);

    private final String typeUser;
    private final boolean announcement;
    private final boolean alterSite;


    Role(String typeUser, boolean announcement, boolean alterSite){
    this.typeUser = typeUser;
    this.announcement=announcement;
    this.alterSite=alterSite;
    }

    public String getTypeUser(){
        return typeUser;
    }

    public boolean isStaff(){
        return announcement;
    }

    public boolean isAdmin(){
        return alterSite;
    }

}
