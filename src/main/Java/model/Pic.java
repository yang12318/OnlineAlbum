package model;

public class Pic {
    private int id;
    private String url;
    private String md5;

    public Pic() {
    }

    public Pic(int id, String url, String md5) {
        this.id = id;
        this.url = url;
        this.md5 = md5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Pic) {
            Pic pic = (Pic) obj;
            return id == pic.getId() && md5.equals(pic.getMd5());
        }
        return false;
    }
}
