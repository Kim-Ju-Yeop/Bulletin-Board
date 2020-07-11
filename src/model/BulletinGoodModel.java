package model;

import java.sql.Timestamp;

public class BulletinGoodModel {
    public int bulletinId;
    public String writerId;
    public Timestamp hitTime;

    public BulletinGoodModel(int bulletinId, String writerId, Timestamp hitTime) {
        this.bulletinId = bulletinId;
        this.writerId = writerId;
        this.hitTime = hitTime;
    }
}
