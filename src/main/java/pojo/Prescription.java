package pojo;/*
 *Author:Litao
 *Created in:
 */

import java.util.Date;

public class Prescription {
    String regid;
    String preid;
    String predesc;
    String pid;
    String did;
    String prestate;
    Date predate;
    double totalPrice;

    @Override
    public String toString() {
        return "Prescription{" +
                "regid='" + regid + '\'' +
                ", preid='" + preid + '\'' +
                ", predesc='" + predesc + '\'' +
                ", pid='" + pid + '\'' +
                ", did='" + did + '\'' +
                ", prestate='" + prestate + '\'' +
                ", predate=" + predate +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Prescription(String regid, String preid, String predesc, String pid, String did, String prestate, Date predate, double totalPrice) {
        this.regid = regid;
        this.preid = preid;
        this.predesc = predesc;
        this.pid = pid;
        this.did = did;
        this.prestate = prestate;
        this.predate = predate;
        this.totalPrice = totalPrice;
    }



    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getPreid() {
        return preid;
    }

    public void setPreid(String preid) {
        this.preid = preid;
    }

    public String getPredesc() {
        return predesc;
    }

    public void setPredesc(String predesc) {
        this.predesc = predesc;
    }

    public String getPrestate() {
        return prestate;
    }

    public void setPrestate(String prestate) {
        this.prestate = prestate;
    }

    public Date getPredate() {
        return predate;
    }

    public void setPredate(Date predate) {
        this.predate = predate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Prescription() {
    }

    public Prescription(String regid, String preid, String predesc, String prestate, Date predate, double totalPrice) {
        this.regid = regid;
        this.preid = preid;
        this.predesc = predesc;
        this.prestate = prestate;
        this.predate = predate;
        this.totalPrice = totalPrice;
    }
}
