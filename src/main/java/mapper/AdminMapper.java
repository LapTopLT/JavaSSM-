package mapper;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.*;

import java.util.List;

public interface AdminMapper {
    List<Medicine> getMeds(
            @Param("start") int start, @Param("medname") String medname,@Param("medid") String medid);
    int getMedRowCount(@Param("medname") String medname,
                       @Param("medid") String medid);

    List<Prescribe> pharmacyQueryPre(
            @Param("preid") String preid,@Param("pname") String pname,@Param("start") int start);

    List<PharmacyMed> getDeliverMedList(String preid);

    int medOut(String preid);

    int changePrestate(@Param("preid") String preid,
                       @Param("prestate") int prestate);
//   部门的增删改查
    List<Dept> getAllDept(
                            @Param("deptid")
                            String deptid,
                            @Param("deptname")
                            String deptname,
                            @Param("start")
                            int start);

    int getDeptRowCount(
            @Param("deptno")
            String deptno,
            @Param("deptname")
            String deptname);

    int insertDept(@Param("deptno")
                           String deptno,
                   @Param("deptname")
                           String deptname);

    int updateDept(@Param("deptno")
                           String deptno,
                   @Param("deptname")
                           String deptname);

    int deleteDept(String deptno);

    List<Doctor> getAllDoc(
            @Param("did")
            String did,
            @Param("dname")
            String dname,
            @Param("start")
            int start);

    int getDocRowCount(
            @Param("did")
            String did,
            @Param("dname")
            String dname);

    int deleteDoc(String did);

    List<Patient> getAllPatient(@Param("pid")
                                String pid,
                                @Param("pname")
                                String pname,
                                @Param("start")
                                int start);

    int getPatientRowCount(@Param("pid") String pid,@Param("pname")String pname);

    int deletePatientByPid(String pid);

    int updatePatient(Patient p);

    List<User> queryUser(@Param("uid")
                        String uid,
                         @Param("uname")
                         String uname,
                         @Param("start")
                         int start);

    int getUserRowCount(@Param("uid") String uid,
                        @Param("uname") String uname);

    List<Registration> getRegList(@Param("regid") String regid,
                                  @Param("start") int start);

    int getRegRowCount(@Param("regid") String regid);

    int updateUser(User user);

    int deleteUser(String uid);

    int updateReg(Registration reg);

    int updateMed(Medicine med);

    int deleteMed(String medid);

    int insertMed(Medicine med);

    List<Prescription> getAllPre(@Param("preid") String preid,@Param("start") int start);

    int getPreRowCount(@Param("preid") String preid);

    int updatePre(Prescription pre);

    int deletePre(String preid);
}
