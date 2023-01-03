package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Duty {
    String did;
    Integer dutyday;
    Integer dutytime;
    Integer dutyamount;
    Double dutycharge;

    @Override
    public String toString() {
        return "Duty{" +
                "did='" + did + '\'' +
                ", dutyday=" + dutyday +
                ", dutytime=" + dutytime +
                ", dutyamount=" + dutyamount +
                ", dutycharge=" + dutycharge +
                '}';
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Integer getDutyday() {
        return dutyday;
    }

    public void setDutyday(Integer dutyday) {
        this.dutyday = dutyday;
    }

    public Integer getDutytime() {
        return dutytime;
    }

    public void setDutytime(Integer dutytime) {
        this.dutytime = dutytime;
    }

    public Integer getDutyamount() {
        return dutyamount;
    }

    public void setDutyamount(Integer dutyamount) {
        this.dutyamount = dutyamount;
    }

    public Double getDutycharge() {
        return dutycharge;
    }

    public void setDutycharge(Double dutycharge) {
        this.dutycharge = dutycharge;
    }

    public Duty() {
    }

    public Duty(String did, Integer dutyday, Integer dutytime, Integer dutyamount, Double dutycharge) {
        this.did = did;
        this.dutyday = dutyday;
        this.dutytime = dutytime;
        this.dutyamount = dutyamount;
        this.dutycharge = dutycharge;
    }
}
