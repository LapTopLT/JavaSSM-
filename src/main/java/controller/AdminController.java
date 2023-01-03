package controller;/*
 *Author:Litao
 *Created in:
 */

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.*;
import service.AdminService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static controller.DoctorController.PAGE_SIZE;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
    }

    @RequestMapping("/getMeds")
    public List<Medicine> getMeds(int currentPage,String medname,String medid){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.getMeds(start,medname,medid);
    }

    @RequestMapping("/getMedRowCount")
    public int getMedRowCount(String medname,String medid){
        return service.getMedRowCount(medname,medid);
    }

    //这个是药房管理员的查询，只能查询到已缴费，即prestate为2的列表
    @RequestMapping("/pharmacyQueryPre")
    public List<Prescribe> pharmacyQueryPre(String preid,String pname,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.pharmacyQueryPre(preid,pname,start);
    }

    //查询处方药物
    @RequestMapping("/getDeliverMedList")
    public List<PharmacyMed> getDeliverMedList(String preid){
        return service.getDeliverMedList(preid);
    }

    //开药操作
    @RequestMapping("/medOut")
    public int medOut(String preid,int prestate){
        return service.medOut(preid,prestate);
    }

    @RequestMapping("/getAllDept")
    public List<Dept> getAllDept(String deptno,String deptname,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.getAllDept(deptno,deptname,start);
    }

    @RequestMapping("/getDeptRowCount")
    public int getDeptRowCount(String deptno,String deptname){
        return service.getDeptRowCount(deptno,deptname);
    }

    @RequestMapping("/insertDept")
    public int insertDept(String deptno,String deptname){
        return service.insertDept(deptno,deptname);
    }

    @RequestMapping("/updateDept")
    public int updateDept(String deptno,String deptname){
        return service.updateDept(deptno,deptname);
    }

    @RequestMapping("/deleteDept")
    public int deleteDept(String deptno){
        return service.deleteDept(deptno);
    }

    @RequestMapping("/getAllDoc")
    public List<Doctor> getAllDoc(String did,String dname,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.getAllDoc(did,dname,start);
    }

    @RequestMapping("/getDocRowCount")
    public int getDocRowCount(String did,String dname){
        return service.getDocRowCount(did,dname);
    }

    @RequestMapping("/deleteDoc")
    public int deleteDoc(String did){
        return service.deleteDoc(did);
    }

    @RequestMapping("/getAllPatient")
    public List<Patient> getAllPatient(String pid,String pname,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.getAllPatient(pid,pname,start);
    }

    @RequestMapping("/getPatientRowCount")
    public int getPatientRowCount(String pid,String pname){
        return service.getPatientRowCount(pid,pname);
    }


    @RequestMapping("/updatePatient")
    public int updatePatient(Patient p){
        return service.updatePatient(p);
    }

    @RequestMapping("/queryUser")
    public List<User> queryUser(String uid,String uname,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.queryUser(uid,uname,start);
    }

    @RequestMapping("/getUserRowCount")
    public int getUserRowCount(String uid,String uname){
        return service.getUserRowCount(uid,uname);
    }

    @RequestMapping("/getRegList")
    public List<Registration> getRegList(String regid,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.getRegList(regid,start);
    }

    @RequestMapping("/getRegRowCount")
    public int getRegRowCount(String regid){
        return service.getRegRowCount(regid);
    }

    @RequestMapping("/updateUser")
    public int updateUser(User user){
        return service.updateUser(user);
    }

    @RequestMapping("/deleteUser")
    public int deleteUser(String uid){
        return service.deleteUser(uid);
    }

    @RequestMapping("/updateReg")
    public int updateReg(Registration reg){
        return service.updateReg(reg);
    }

    @RequestMapping("/updateMed")
    public int updateMed(Medicine med){
        return service.updateMed(med);
    }

    @RequestMapping("/deleteMed")
    public int deleteMed(String medid){
        return service.deleteMed(medid);
    }

    @RequestMapping("/insertMed")
    public int insertMed(Medicine med){
        return service.insertMed(med);
    }

    @RequestMapping("/getAllPre")
    public List<Prescription> getAllPre(String preid,int currentPage){
        int start = (currentPage - 1) * PAGE_SIZE;
        return service.getAllPre(preid,start);
    }

    @RequestMapping("/getPreRowCount")
    public int getPreRowCount(String preid){
        return service.getPreRowCount(preid);
    }

    @RequestMapping("/updatePre")
    public int updatePre(Prescription pre){
        return service.updatePre(pre);
    }

    @RequestMapping("/deletePre")
    public int deletePre(String preid){
        return service.deletePre(preid);
    }
}
