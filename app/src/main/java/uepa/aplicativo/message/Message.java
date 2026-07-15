package uepa.aplicativo.message;
import java.time.LocalDateTime;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.user.Staff;
public class Message {
  private String title;
  private String text;
  private LocalDateTime date;
  private Extracurricular extra;
  private Staff staff;

  public Message(String title, String text, Extracurricular extra, Staff staff ) {
    this.title = title;
    this.text = text;
    this.date = LocalDateTime.now();
    this.extra = extra;
    this.staff = staff;
  }
  public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime dataEnvio) {
        this.date = dataEnvio;
    }
    public Extracurricular getExtra() {
        return extra;
    }
    public void setExtra(Extracurricular extra) {
        this.extra = extra;
    }
    public Staff getStaff(){
        return staff;
    }
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

}
