package model;

public class User {
    private int id;
    private String name;
    private String password;
    private String registerTime;

    public User() {
    }

    public User(int id, String name, String password, String registerTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.registerTime = registerTime;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
