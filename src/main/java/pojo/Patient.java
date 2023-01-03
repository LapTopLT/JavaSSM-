package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Patient {
    String pid;
    String uid;
    String pname;
    String psex;
    String page;
    String pnumber;
    String pallergic;

    @Override
    public String toString() {
        return "Patient{" +
                "pid='" + pid + '\'' +
                ", uid='" + uid + '\'' +
                ", pname='" + pname + '\'' +
                ", psex='" + psex + '\'' +
                ", page='" + page + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", pallergic='" + pallergic + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPsex() {
        return psex;
    }

    public void setPsex(String psex) {
        this.psex = psex;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPallergic() {
        return pallergic;
    }

    public void setPallergic(String pallergic) {
        this.pallergic = pallergic;
    }

    public Patient() {
    }

    public Patient(String pid, String uid, String pname, String psex, String page, String pnumber, String pallergic) {
        this.pid = pid;
        this.uid = uid;
        this.pname = pname;
        this.psex = psex;
        this.page = page;
        this.pnumber = pnumber;
        this.pallergic = pallergic;
    }
}
