package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Medicine {
    Integer medid;
    String medname;
    Integer medamount;
    Double medprice;

    @Override
    public String toString() {
        return "Medicine{" +
                "medid=" + medid +
                ", medname='" + medname + '\'' +
                ", medamount=" + medamount +
                ", medprice=" + medprice +
                '}';
    }

    public Integer getMedid() {
        return medid;
    }

    public void setMedid(Integer medid) {
        this.medid = medid;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public Integer getMedamount() {
        return medamount;
    }

    public void setMedamount(Integer medamount) {
        this.medamount = medamount;
    }

    public Double getMedprice() {
        return medprice;
    }

    public void setMedprice(Double medprice) {
        this.medprice = medprice;
    }

    public Medicine() {
    }

    public Medicine(Integer medid, String medname, Integer medamount, Double medprice) {
        this.medid = medid;
        this.medname = medname;
        this.medamount = medamount;
        this.medprice = medprice;
    }
}
