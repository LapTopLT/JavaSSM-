package pojo;/*
 *Author:Litao
 *Created in:
 */

public class DocDuty {
    String uid;
    String did;
    String dname;
    String dsex;
    Integer dage;
    String dnumber;
    String deptno;
    String deptname;
    Integer dutyday;
    Integer dutyamount;
    Integer dutytime;
    Double dutycharge;

    @Override
    public String
    toString() {
        return "DocDuty{" +
                "uid='" + uid + '\'' +
                ", did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                ", dsex='" + dsex + '\'' +
                ", dage=" + dage +
                ", dnumber='" + dnumber + '\'' +
                ", deptno='" + deptno + '\'' +
                ", deptname='" + deptname + '\'' +
                ", dutyday=" + dutyday +
                ", dutyamount=" + dutyamount +
                ", dutytime=" + dutytime +
                ", dutycharge=" + dutycharge +
                '}';
    }

    public Double getDutycharge() {
        return dutycharge;
    }

    public void setDutycharge(Double dutycharge) {
        this.dutycharge = dutycharge;
    }

    public DocDuty(String uid, String did, String dname, String dsex, Integer dage, String dnumber, String deptno, String deptname, Integer dutyday, Integer dutyamount, Integer dutytime, Double dutycharge) {
        this.uid = uid;
        this.did = did;
        this.dname = dname;
        this.dsex = dsex;
        this.dage = dage;
        this.dnumber = dnumber;
        this.deptno = deptno;
        this.deptname = deptname;
        this.dutyday = dutyday;
        this.dutyamount = dutyamount;
        this.dutytime = dutytime;
        this.dutycharge = dutycharge;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDsex() {
        return dsex;
    }

    public void setDsex(String dsex) {
        this.dsex = dsex;
    }

    public Integer getDage() {
        return dage;
    }

    public void setDage(Integer dage) {
        this.dage = dage;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Integer getDutyday() {
        return dutyday;
    }

    public void setDutyday(Integer dutyday) {
        this.dutyday = dutyday;
    }

    public Integer getDutyamount() {
        return dutyamount;
    }

    public void setDutyamount(Integer dutyamount) {
        this.dutyamount = dutyamount;
    }

    public Integer getDutytime() {
        return dutytime;
    }

    public void setDutytime(Integer dutytime) {
        this.dutytime = dutytime;
    }

    public DocDuty() {
    }

    public DocDuty(String uid, String did, String dname, String dsex, Integer dage, String dnumber, String deptno, String deptname, Integer dutyday, Integer dutyamount, Integer dutytime) {
        this.uid = uid;
        this.did = did;
        this.dname = dname;
        this.dsex = dsex;
        this.dage = dage;
        this.dnumber = dnumber;
        this.deptno = deptno;
        this.deptname = deptname;
        this.dutyday = dutyday;
        this.dutyamount = dutyamount;
        this.dutytime = dutytime;
    }
}
