package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Doctor {
    String uid;
    String did;
    String dname;
    String dsex;
    Integer dage;
    String dnumber;
    String deptno;
    String deptname;

    @Override
    public String toString() {
        return "Doctor{" +
                "uid='" + uid + '\'' +
                ", did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                ", dsex='" + dsex + '\'' +
                ", dage=" + dage +
                ", dnumber='" + dnumber + '\'' +
                ", deptno='" + deptno + '\'' +
                ", deptname='" + deptname + '\'' +
                '}';
    }

    public Doctor(String uid, String did, String dname, String dsex, Integer dage, String dnumber, String deptno, String deptname) {
        this.uid = uid;
        this.did = did;
        this.dname = dname;
        this.dsex = dsex;
        this.dage = dage;
        this.dnumber = dnumber;
        this.deptno = deptno;
        this.deptname = deptname;
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

    public Doctor() {
    }

    public Doctor(String did, String dname, String dsex, Integer dage, String dnumber, String deptno, String deptname) {
        this.did = did;
        this.dname = dname;
        this.dsex = dsex;
        this.dage = dage;
        this.dnumber = dnumber;
        this.deptno = deptno;
        this.deptname = deptname;
    }
}
