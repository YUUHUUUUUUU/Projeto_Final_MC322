package uepa.aplicativo.message;
import java.time.LocalDateTime;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.user.Staff;
public class Message {
    private String title;
    private String text;
    private String creatorName;

    public Message(String title, String text, String creatorName) {
        this.title = title;
        this.text = text;
        this.creatorName = creatorName;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setTitle(String title) throws Exception{
        if(title.length() > 20){
            throw new Exception("Title can not exceed 20 characters");
        }
        this.title = title;
    }

    public void setText(String text) throws Exception{
        if(text.length() > 200) {
            throw new Exception("Message text can not exceed 200 characters");
        }
        this.text = text;
    }

    

}
