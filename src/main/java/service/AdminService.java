package service;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.*;

import java.util.List;

public interface AdminService {
    List<Medicine> getMeds(int start,String medname,String medid);
    int getMedRowCount(String medname,String medid);
    List<Prescribe> pharmacyQueryPre(String preid,String pname,int start);
    List<PharmacyMed> getDeliverMedList(String preid);
    int medOut(String preid,int prestate);
    List<Dept> getAllDept(String deptid,String deptname,int start);
    int getDeptRowCount(String deptno,String deptname);
    int insertDept(String deptno,String deptname);
    int updateDept(String deptno,String deptname);
    int deleteDept(String deptno);
    List<Doctor> getAllDoc(String did,String dname,int start);
    int getDocRowCount(String did,String dname);
    int deleteDoc(String did);
    List<Patient> getAllPatient(String pid,String pname,int start);
    int getPatientRowCount(String pid,String pname);
    int deletePatientByPid(String pid);
    int updatePatient(Patient p);
    List<User> queryUser(String uid,String uname,int start);
    int getUserRowCount(String uid,String uname);
    List<Registration> getRegList(String regid,int start);
    int getRegRowCount(String regid);
    int updateUser(User user);
    int deleteUser(String uid);
    int updateReg(Registration reg);
    int updateMed(Medicine med);
    int deleteMed(String medid);
    int insertMed(Medicine med);
    List<Prescription> getAllPre(String preid,int start);
    int getPreRowCount(String preid);
    int updatePre(Prescription pre);
    int deletePre(String preid);
}
