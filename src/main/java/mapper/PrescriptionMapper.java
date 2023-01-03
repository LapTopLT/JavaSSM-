package mapper;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.Medicine;

import java.util.Date;
import java.util.List;

public interface PrescriptionMapper {
    //添加处方
    int addPre(
            @Param("regid") String regid, @Param("preid")String preid,
            @Param("pid") String pid,@Param("did") String did,
            @Param("predesc")String predesc,@Param("prestate") int prestate,
            @Param("predate")Date predate,@Param("totalPrice") double totalPrice);
    //吧处方药添加到药库里
    int addPharmacy(@Param("preid") String preid, @Param("meds") List<Medicine> meds);
    //把挂号单的状态改成0（结束）
    int changeRegstate(@Param("regid") String regid,
                       @Param("regstate") int regstate);
}
