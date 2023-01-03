package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Pharmacy {
    String preid;
    String medid;
    String medname;
    int amount;

    @Override
    public String toString() {
        return "Pharmacy{" +
                "preid='" + preid + '\'' +
                ", medid='" + medid + '\'' +
                ", medname='" + medname + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getPreid() {
        return preid;
    }

    public void setPreid(String preid) {
        this.preid = preid;
    }

    public String getMedid() {
        return medid;
    }

    public void setMedid(String medid) {
        this.medid = medid;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Pharmacy() {
    }

    public Pharmacy(String preid, String medid, String medname, int amount) {
        this.preid = preid;
        this.medid = medid;
        this.medname = medname;
        this.amount = amount;
    }
}
