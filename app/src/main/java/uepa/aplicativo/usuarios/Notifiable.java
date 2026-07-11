package uepa.aplicativo.usuarios;
public interface Notifiable {
    void addtoNotify(Student s);
    void removefromNotify(Student s);
    boolean isFollowedBy(Student s);
}
