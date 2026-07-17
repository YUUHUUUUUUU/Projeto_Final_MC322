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

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
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

    public Data addUser(User user) {
        if(user != null){
            userList.add(user);
            writeUserData(this);
        }

        return loadData(this);
    }

    public Data addExtracurricular(Extracurricular extra) {
        if(extra != null) {
            extracurricularList.add(extra);
            writeExtracurricularData(this);
        }

        return loadData(this);
    }

    public static void writeUserData(Data data) {
        xmlWriter.writeUsers("/src/main/resources/xml/user.xml", data);
    }

    public static void writeExtracurricularData(Data data) {
        xmlWriter.writeExtracurriculars("/src/main/resources/xml/extra.xml", data);
    }

    public static Data loadData(Data data) {
        data = xmlReader.readUsers("/src/main/resources/xml/user.xml", data);
        data = xmlReader.readExtracurriculars("/src/main/resources/xml/extra.xml", data);

        return data;
    }

    public boolean verifyUserExistence(User user, Data data) throws Exception {
        if(data == null) {
            throw new Exception("Failed to load xml");
        }

        for(User u : data.getUserList()) {
            System.out.println(user.getEmail());
            System.out.println(u.getEmail());
            if(user.getEmail().equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
