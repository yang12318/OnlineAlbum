package model;

public class Album {
    private int id;
    private String name;
    private int userid;
    private String date;
    private String username;
    private String description;
    private int count;

    public Album() {
    }

    public Album(int id, String name, int userid, String date, String username, String description, int count) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.username = username;
        this.userid = userid;
        this.description = description;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
