package service.impl;/*
 *Author:Litao
 *Created in:
 */

import mapper.DoctorMapper;
import org.springframework.stereotype.Service;
import pojo.*;
import service.DoctorService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Resource
    DoctorMapper mapper;

    @Override
    public Doctor queryDoctor(String uid) {
        return mapper.queryDoctor(uid);
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        return mapper.updateDoctor(doctor);
    }

    @Override
    public int insertDoctor(Doctor doctor) {
        return mapper.insertDoctor(doctor);
    }

    @Override
    public List<Dept> queryDepts() {
        return mapper.queryDepts();
    }

    @Override
    public List<Doctor> queryByDeptno(String deptno) {
        return mapper.queryByDeptno(deptno);
    }

    @Override
    public Doctor queryByDid(String did) {
        return mapper.queryByDid(did);
    }

    @Override
    public List<Registration> queryRegList(String did, Integer start, Integer size) {
        return mapper.queryRegList(did,start,size);
    }

    @Override
    public Integer getRegRowCount(String did) {
        return mapper.getRegRowCount(did);
    }

    @Override
    public int changeRegstate(int regstate, String regid) {
        return mapper.changeRegstate(regstate,regid);
    }

    @Override
    public List<Medicine> queryMedPage(int start, String medname, int size) {
        return mapper.queryMedPage(start,medname,size);
    }

    @Override
    public int getMedRowCount(String medname) {
        return mapper.getMedRowCount(medname);
    }

    @Override
    public List<Prescription> getPreListByDid(String did,int currentPage) {
        return mapper.getPreListByDid(did,currentPage);
    }

    @Override
    public int getPreRowCountByDid(String did) {
        return mapper.getPreRowCountByDid(did);
    }

    @Override
    public List<Duty> queryDutyByDid(String did) {
        return mapper.queryDutyByDid(did);
    }

    @Override
    public int changeDuty(List<Duty> list) {
        //先删除该医生的值班信息，再重新添加
        int count = 0;
        count += mapper.deleteDutyByDid(list.get(0).getDid());
        count += mapper.insertDutyList(list);
        return count;
    }

}
