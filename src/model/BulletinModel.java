package model;

public class BulletinModel {
    public int id;
    public int hit;
    public String title;
    public String content;
    public String writer;

    public BulletinModel(int id, int hit, String title, String content, String writer) {
        this.id = id;
        this.hit = hit;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
