package model;

public class Comment {
    private int id;
    private int userid;
    private int picid;
    private String username;
    private String content;
    private String date;

    public Comment(){
    }

    public Comment(int id, int userid, int picid, String username, String content, String date){
        this.id = id;
        this.userid = userid;
        this.picid = picid;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPicid() {
        return picid;
    }

    public void setPicid(int picid) {
        this.picid = picid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
