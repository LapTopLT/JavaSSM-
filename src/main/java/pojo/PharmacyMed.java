package pojo;/*
 *Author:Litao
 *Created in:
 */

public class PharmacyMed {
    String preid;
    String medid;
    String medname;
    Integer amount;
    Integer medamount;
    Integer prestate;

    @Override
    public String toString() {
        return "PharmacyMed{" +
                "preid='" + preid + '\'' +
                ", medid='" + medid + '\'' +
                ", medname='" + medname + '\'' +
                ", amount=" + amount +
                ", medamount=" + medamount +
                ", prestate=" + prestate +
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

    public Integer getPrestate() {
        return prestate;
    }

    public void setPrestate(Integer prestate) {
        this.prestate = prestate;
    }

    public PharmacyMed(String preid, String medid, String medname, Integer amount, Integer medamount, Integer prestate) {
        this.preid = preid;
        this.medid = medid;
        this.medname = medname;
        this.amount = amount;
        this.medamount = medamount;
        this.prestate = prestate;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMedamount() {
        return medamount;
    }

    public void setMedamount(Integer medamount) {
        this.medamount = medamount;
    }

    public PharmacyMed() {
    }

    public PharmacyMed(String medid, String medname, Integer amount, Integer medamount) {
        this.medid = medid;
        this.medname = medname;
        this.amount = amount;
        this.medamount = medamount;
    }
}
