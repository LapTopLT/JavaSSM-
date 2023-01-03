package service.impl;/*
 *Author:Litao
 *Created in:
 */

import mapper.PrescriptionMapper;
import org.springframework.stereotype.Service;
import pojo.Medicine;
import service.PrescriptionService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Resource
    PrescriptionMapper mapper;


    @Override
    public int addPre(String regid, String preid,String pid,String did, String predesc, int prestate, Date predate, double totalPrice) {
        return mapper.addPre(regid,preid,pid,did,predesc,prestate,predate,totalPrice);
    }

    @Override
    public int addPharmacy(String preid, List<Medicine> meds) {
        return mapper.addPharmacy(preid,meds);
    }

    @Override
    public int changeRegstate(String regid, Integer regstate) {
        return mapper.changeRegstate(regid,regstate);
    }
}
