package controller;/*
 *Author:Litao
 *Created in:
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.*;
import service.DoctorService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/doctor")
public class DoctorController {
    static Integer PAGE_SIZE = 5;

    @Resource
    DoctorService service;

    @RequestMapping("/query")
    Doctor queryDoctor(String uid){
        return service.queryDoctor(uid);
    }

    @RequestMapping("/insert")
    int insertDoctor(Doctor doctor){
        String did = String.valueOf(System.currentTimeMillis()).substring(4, 10);
        doctor.setDid(did);
        return service.insertDoctor(doctor);
    }

    @RequestMapping("/update")
    int updateDoctor(Doctor doctor){
        return service.updateDoctor(doctor);
    }

    //查询所有的部门，用于医生创建时查询使用
    @RequestMapping("/queryDepts")
    List<Dept> queryDepts(){
        return service.queryDepts();
    }

    @RequestMapping("/queryByDeptno")
    List<Doctor> queryByDeptno(String deptno){
        return service.queryByDeptno(deptno);
    }

    @RequestMapping("/queryByDid")
    Doctor queryByDid(String did){
        return service.queryByDid(did);
    }

    @RequestMapping("/queryRegList")
    List<Registration> queryRegList(String did,Integer currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.queryRegList(did,start,PAGE_SIZE);
    }

    @RequestMapping("/getRegRowCount")
    int getRegRowCount(String did){
        return service.getRegRowCount(did);
    }

    @RequestMapping("/changeRegstate")
    int changeRegstate(int regstate,String regid){
        return service.changeRegstate(regstate,regid);
    }

    @RequestMapping("/queryMedPage")
    List<Medicine> queryMedPage(int currentPage,String medname,int size){
        int start = (currentPage - 1) * size;
        return service.queryMedPage(start,medname,size);
    }

    @RequestMapping("/getMedRowCount")
    int getMedRowCount(String medname){
        return service.getMedRowCount(medname);
    }

    @RequestMapping("/getPreListByDid")
    public List<Prescription> getPreListByDid(String did,int currentPage){
        currentPage = (currentPage - 1) * PAGE_SIZE;
        return service.getPreListByDid(did,currentPage);
    }

    @RequestMapping("/getPreRowCountByDid")
    public int getPreRowCountByDid(String did){
        return service.getPreRowCountByDid(did);
    }

    @RequestMapping("/queryDutyByDid")
    public List<Duty> queryDutyByDid(String did){
        return service.queryDutyByDid(did);
    }

    @RequestMapping("/changeDuty")
    public int changeDuty(String list){
        System.out.println("inChangeDuty:"+list);
        ObjectMapper map = new ObjectMapper();
        List<Duty> duties = null;
        try {
            duties = map.readValue(list,new TypeReference<List<Duty>>(){});
            System.out.println(duties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return service.changeDuty(duties);
    }
}
