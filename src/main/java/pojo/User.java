package pojo;/*
 *Author:Litao
 *Created in:
 */

public class User {
    private String uid;
    private String uname;
    private String upassword;
    private String uemail;
    private Integer roleid;

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uemail='" + uemail + '\'' +
                ", roleid=" + roleid +
                '}';
    }

    public User(String uid, String uname, String upassword, String uemail, Integer roleid) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uemail = uemail;
        this.roleid = roleid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public User(String uid, String uname, String upassword, String uemail) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uemail = uemail;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
}
