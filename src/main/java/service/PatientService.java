package service;/*
 *Author:Litao
 *Created in:
 */


import pojo.*;

import java.util.List;

public interface PatientService {
    Patient queryPatient(String uid);
    int insertPatient(Patient p);
    int updatePatient(Patient p);
    int regist(Registration reg);
    List<Registration> queryReg(String pid, boolean single);
    Patient queryByPid(String pid);
    List<Prescription> getPreListByPid(String pid);
    int changePrestate(String preid,int prestate);
    List<Pharmacy> checkMedlist(String preid);
    List<Integer> getPidByPname(String pname);
    List<DocDuty> queryDocList(String deptno,int dutyday);
    int cancelReg(String pid);
}
