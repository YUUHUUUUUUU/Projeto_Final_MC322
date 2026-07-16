package uepa.aplicativo.DataManager;

import java.util.ArrayList;
import java.util.List;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;
import uepa.aplicativo.user.User;

public class Data {

    private List<User> userList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();
    private List<Extracurricular> extracurricularList = new ArrayList<>();

    public Data(List<Student> studentList, List<Staff> staffList) {
        this.studentList = studentList;
        this.staffList = staffList;
        
        for(Student s : studentList) {
            userList.add(s);
        }
        for(Staff s : staffList) {
            userList.add(s);
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }
    public List<User> getUserList() {
        return userList;
    }

    public List<Extracurricular> getExtracurricularList() {
        return extracurricularList;
    }

    public void setExtracurricularList(List<Extracurricular> extracurricularList) {
        this.extracurricularList = extracurricularList;
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
