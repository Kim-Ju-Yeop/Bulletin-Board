package model;

import java.util.Date;

public class BulletinGoodModel {
    public int bulletinId;
    public String writerId;
    public Date hitTime;

    public BulletinGoodModel(int bulletinId, String writerId, Date hitTime) {
        this.bulletinId = bulletinId;
        this.writerId = writerId;
        this.hitTime = hitTime;
    }
}
