package service.impl;/*
 *Author:Litao
 *Created in:
 */

import mapper.AdminMapper;
import org.springframework.stereotype.Service;
import pojo.*;
import service.AdminService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper mapper;

    @Override
    public List<Medicine> getMeds(int start,String medname,String medid) {
        return mapper.getMeds(start,medname,medid);
    }

    @Override
    public int getMedRowCount(String medname,String medid) {
        return mapper.getMedRowCount(medname,medid);
    }

    @Override
    public List<Prescribe> pharmacyQueryPre(String preid, String pname, int start) {
        return mapper.pharmacyQueryPre(preid,pname,start);
    }

    @Override
    public List<PharmacyMed> getDeliverMedList(String preid) {
        return mapper.getDeliverMedList(preid);
    }

    @Override
    public int medOut(String preid, int prestate) {
        int count = 0;
        count += mapper.medOut(preid);
        count += mapper.changePrestate(preid,prestate);
        return count;
    }

    @Override
    public List<Dept> getAllDept(String deptid, String deptname, int start) {
        return mapper.getAllDept(deptid,deptname,start);
    }

    @Override
    public int getDeptRowCount(String deptno, String deptname) {
        return mapper.getDeptRowCount(deptno,deptname);
    }

    @Override
    public int insertDept(String deptno, String deptname) {
        return mapper.insertDept(deptno,deptname);
    }

    @Override
    public int updateDept(String deptno, String deptname) {
        return mapper.updateDept(deptno,deptname);
    }

    @Override
    public int deleteDept(String deptno) {
        return mapper.deleteDept(deptno);
    }

    @Override
    public List<Doctor> getAllDoc(String did, String dname, int start) {
        return mapper.getAllDoc(did,dname,start);
    }

    @Override
    public int getDocRowCount(String did, String dname) {
        return mapper.getDocRowCount(did,dname);
    }

    @Override
    public int deleteDoc(String did) {
        return mapper.deleteDoc(did);
    }

    @Override
    public List<Patient> getAllPatient(String pid, String pname, int start) {
        return mapper.getAllPatient(pid,pname,start);
    }

    @Override
    public int getPatientRowCount(String pid, String pname) {
        return mapper.getPatientRowCount(pid,pname);
    }

    @Override
    public int deletePatientByPid(String pid) {
        return mapper.deletePatientByPid(pid);
    }

    @Override
    public int updatePatient(Patient p) {
        return mapper.updatePatient(p);
    }

    @Override
    public List<User> queryUser(String uid, String uname, int start) {
        return mapper.queryUser(uid,uname,start);
    }

    @Override
    public int getUserRowCount(String uid, String uname) {
        return mapper.getUserRowCount(uid,uname);
    }

    @Override
    public List<Registration> getRegList(String regid, int start) {
        return mapper.getRegList(regid,start);
    }

    @Override
    public int getRegRowCount(String regid) {
        return mapper.getRegRowCount(regid);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public int deleteUser(String uid) {
        return mapper.deleteUser(uid);
    }

    @Override
    public int updateReg(Registration reg) {
        return mapper.updateReg(reg);
    }

    @Override
    public int updateMed(Medicine med) {
        return mapper.updateMed(med);
    }

    @Override
    public int deleteMed(String medid) {
        return mapper.deleteMed(medid);
    }

    @Override
    public int insertMed(Medicine med) {
        return mapper.insertMed(med);
    }

    @Override
    public List<Prescription> getAllPre(String preid, int start) {
        return mapper.getAllPre(preid,start);
    }

    @Override
    public int getPreRowCount(String preid) {
        return mapper.getPreRowCount(preid);
    }

    @Override
    public int updatePre(Prescription pre) {
        return mapper.updatePre(pre);
    }

    @Override
    public int deletePre(String preid) {
        return mapper.deletePre(preid);
    }


}
