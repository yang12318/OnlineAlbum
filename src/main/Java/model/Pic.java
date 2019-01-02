package model;

public class Pic {
    private int id;
    private String url;
    private int albumid;
    private String title;
    private String description;
    private String publishtime;
    private String filename;

    public Pic() {
    }

    public Pic(int id, String url, int albumid, String title, String description, String publishtime, String filename) {
        this.id = id;
        this.url = url;
        this.albumid = albumid;
        this.title = title;
        this.description = description;
        this.publishtime = publishtime;
        this.filename = filename;
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

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
