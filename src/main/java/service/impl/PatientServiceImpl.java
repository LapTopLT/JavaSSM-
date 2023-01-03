package service.impl;/*
 *Author:Litao
 *Created in:
 */

import mapper.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.*;
import service.PatientService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PatientServiceImpl implements PatientService {
    @Resource
    PatientMapper patientMapper;

    @Override
    public Patient queryPatient(String uid) {
        return patientMapper.queryPatient(uid);
    }

    @Override
    public int insertPatient(Patient p) {
        return patientMapper.insertPatient(p);
    }

    @Override
    public int updatePatient(Patient p) {
        return patientMapper.updatePatient(p);
    }

    @Override
    public int regist(Registration reg) {
        Date date = reg.getRegtime();
        Calendar c = new GregorianCalendar();
        c.setTime(date);
//        patientMapper.registSuccess(reg.getDid(),);
        int day = c.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour > 12){
            hour = 2;
        }else{
            hour = 1;
        }
        int count = 0;
        count += patientMapper.regist(reg);
        if(count != 0){
            count += patientMapper.registSuccess(reg.getDid(),day,hour);
        }
        return count;
    }

    @Override
    public List<Registration> queryReg(String pid, boolean single) {
        return patientMapper.queryReg(pid,single);
    }

    @Override
    public Patient queryByPid(String pid) {
        return patientMapper.queryByPid(pid);
    }

    @Override
    public List<Prescription> getPreListByPid(String pid) {
        return patientMapper.getPreListByPid(pid);
    }

    @Override
    public int changePrestate(String preid, int prestate) {
        return patientMapper.changePrestate(preid,prestate);
    }

    @Override
    public List<Pharmacy> checkMedlist(String preid) {
        return patientMapper.checkMedlist(preid);
    }

    @Override
    public List<Integer> getPidByPname(String pname) {
        return patientMapper.getPidByPname(pname);
    }

    @Override
    public List<DocDuty> queryDocList(String deptno, int dutyday) {
        return patientMapper.queryDocList(deptno,dutyday);
    }

    @Override
    public int cancelReg(String pid) {
        return patientMapper.cancelReg(pid);
    }
}
