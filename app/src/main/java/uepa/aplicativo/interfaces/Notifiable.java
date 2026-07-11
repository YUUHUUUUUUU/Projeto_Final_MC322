package uepa.aplicativo.interfaces;

import uepa.aplicativo.user.Student;

public interface Notifiable {
    void addtoNotify(Student s);
    void removefromNotify(Student s);
    boolean isFollowedBy(Student s);
}
