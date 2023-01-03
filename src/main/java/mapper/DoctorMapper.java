package mapper;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.*;

import java.util.List;

public interface DoctorMapper {
    Doctor queryDoctor(String uid);
    int insertDoctor(Doctor doctor);
    int updateDoctor(Doctor doctor);
    List<Dept> queryDepts();
    List<Doctor> queryByDeptno(String deptno);
    Doctor queryByDid(String did);
    List<Registration> queryRegList(
            @Param("did")
            String did,
            @Param("start")
            Integer start,
            @Param("size")
            Integer size);

    Integer getRegRowCount(
            @Param("did")
            String did);

    int changeRegstate(
            @Param("regstate")
            int regstate,
                       @Param("regid")
                       String regid);

    List<Medicine> queryMedPage(
            @Param("start")
            int start,
            @Param("medname")
            String medname,
            @Param("size")
            int size);

    int getMedRowCount(
            @Param("medname")
            String medname);

    List<Prescription> getPreListByDid(@Param("did") String did,@Param("start") int currentPage);

    int getPreRowCountByDid(String did);

    List<Duty> queryDutyByDid(String did);

    int insertDutyList(@Param("list") List<Duty> list);

    int deleteDutyByDid(String did);
}

