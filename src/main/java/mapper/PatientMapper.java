package mapper;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.*;

import java.util.Date;
import java.util.List;

public interface PatientMapper {

    Patient queryPatient(String uid);

    int insertPatient(Patient p);

    int updatePatient(Patient p);

    int regist(Registration reg);

    List<Registration> queryReg(
            @Param("pid")
            String pid,
            @Param("single")
            boolean single);

    Patient queryByPid(String pid);

    List<Prescription> getPreListByPid(String pid);

    int changePrestate(@Param("preid") String preid,
                       @Param("prestate") int prestate);

    List<Pharmacy> checkMedlist(String preid);

    List<Integer> getPidByPname(String pname);

    List<DocDuty> queryDocList(
            @Param("deptno")
            String deptno,
            @Param("dutyday")
            int dutyday);

    int registSuccess(@Param("did") String did,
                      @Param("dutyday") Integer dutyday,
                      @Param("dutytime") Integer dutytime);

    int cancelReg(String pid);
}
