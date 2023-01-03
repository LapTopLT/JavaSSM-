package pojo;/*
 *Author:Litao
 *Created in:
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Registration {
    String regid;
    String pid;
    String did;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Date regtime;
    Integer regstate;
    String regdesc;

    @Override
    public String toString() {
        return "Registration{" +
                "regid='" + regid + '\'' +
                ", pid='" + pid + '\'' +
                ", did='" + did + '\'' +
                ", regtime=" + regtime +
                ", regstate=" + regstate +
                ", regdesc='" + regdesc + '\'' +
                '}';
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
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

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Integer getRegstate() {
        return regstate;
    }

    public void setRegstate(Integer regstate) {
        this.regstate = regstate;
    }

    public String getRegdesc() {
        return regdesc;
    }

    public void setRegdesc(String regdesc) {
        this.regdesc = regdesc;
    }

    public Registration() {
    }

    public Registration(String regid, String pid, String did, Date regtime, Integer regstate, String regdesc) {
        this.regid = regid;
        this.pid = pid;
        this.did = did;
        this.regtime = regtime;
        this.regstate = regstate;
        this.regdesc = regdesc;
    }
}
