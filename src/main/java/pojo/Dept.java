package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Dept {
    String deptno;
    String deptname;

    @Override
    public String toString() {
        return "Dept{" +
                "deptno='" + deptno + '\'' +
                ", deptname='" + deptname + '\'' +
                '}';
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

    public Dept(String deptno, String deptname) {
        this.deptno = deptno;
        this.deptname = deptname;
    }

    public Dept() {
    }
}
