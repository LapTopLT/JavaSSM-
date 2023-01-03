package pojo;/*
 *Author:Litao
 *Created in:
 */

import java.util.Date;

public class Prescribe {
    String preid;
    String dname;
    String pname;
    Integer prestate;
    Date predate;
    Double totalPrice;


    @Override
    public String toString() {
        return "Prescribe{" +
                "preid='" + preid + '\'' +
                ", dname='" + dname + '\'' +
                ", pname='" + pname + '\'' +
                ", prestate=" + prestate +
                ", predate=" + predate +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Integer getPrestate() {
        return prestate;
    }

    public void setPrestate(Integer prestate) {
        this.prestate = prestate;
    }

    public Prescribe(String preid, String dname, String pname, Integer prestate, Date predate, Double totalPrice) {
        this.preid = preid;
        this.dname = dname;
        this.pname = pname;
        this.prestate = prestate;
        this.predate = predate;
        this.totalPrice = totalPrice;
    }

    public String getPreid() {
        return preid;
    }

    public void setPreid(String preid) {
        this.preid = preid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPredate() {
        return predate;
    }

    public void setPredate(Date predate) {
        this.predate = predate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Prescribe() {
    }

    public Prescribe(String preid, String dname, String pname, Date predate, Double totalPrice) {
        this.preid = preid;
        this.dname = dname;
        this.pname = pname;
        this.predate = predate;
        this.totalPrice = totalPrice;
    }
}
