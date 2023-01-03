package service;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.Medicine;

import java.util.Date;
import java.util.List;

public interface PrescriptionService {
    int addPre(String regid, String preid,
               String predesc,String pid,String did,int prestate,
               Date predate, double totalPrice);

    int addPharmacy(String preid, List<Medicine> meds);
    int changeRegstate(String regid,Integer regstate);
}
