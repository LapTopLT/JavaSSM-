package service;/*
 *Author:Litao
 *Created in:
 */

import pojo.*;

import java.util.List;

public interface DoctorService {
    Doctor queryDoctor(String uid);
    int updateDoctor(Doctor doctor);
    int insertDoctor(Doctor doctor);
    List<Dept> queryDepts();
    List<Doctor> queryByDeptno(String deptno);
    Doctor queryByDid(String did);
    List<Registration> queryRegList(String did,Integer start,Integer size);
    Integer getRegRowCount(String did);
    int changeRegstate(int regstate, String regid);
    List<Medicine> queryMedPage(int start,String medname,int size);
    int getMedRowCount(String medname);
    List<Prescription> getPreListByDid(String did,int currentPage);
    int getPreRowCountByDid(String did);
    List<Duty> queryDutyByDid(String did);
    int changeDuty(List<Duty> list);
}
