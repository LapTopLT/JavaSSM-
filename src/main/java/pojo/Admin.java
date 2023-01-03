package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Admin {
    String uid;
    String aid;

    @Override
    public String toString() {
        return "Admin{" +
                "uid='" + uid + '\'' +
                ", aid='" + aid + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Admin() {
    }

    public Admin(String uid, String aid) {
        this.uid = uid;
        this.aid = aid;
    }
}
