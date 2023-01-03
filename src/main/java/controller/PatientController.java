package controller;/*
 *Author:Litao
 *Created in:
 */

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.*;
import service.PatientService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {
    @InitBinder
     public void initBinder(WebDataBinder binder) {
                 binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
             }

    @Resource
    PatientService patientService;

    @RequestMapping("/query")
    public Patient queryPatient(String uid){
        Patient p = patientService.queryPatient(uid);
        return p;
    }

    @RequestMapping("/insert")
    public int insertPatient(Patient p){
        String pid = String.valueOf(System.currentTimeMillis()).substring(4,10);
        p.setPid(pid);
        return patientService.insertPatient(p);
    }

    @RequestMapping("/update")
    public int updatePatient(Patient p){
        return patientService.updatePatient(p);
    }

    @RequestMapping("/reg")
    public int regist(Registration reg){
        String regid = String.valueOf(System.currentTimeMillis()).substring(5,10);
        reg.setRegid(regid);
        return patientService.regist(reg);
    }

    //查询挂号单，第二个参数决定是查询单个（state不为0），还是多个（state为0）
    @RequestMapping("/queryReg")
    public List<Registration> queryReg(String pid,boolean single){
        return patientService.queryReg(pid,single);
    }

    @RequestMapping("/queryByPid")
    public Patient queryByPid(String pid){
        return patientService.queryByPid(pid);
    }

    @RequestMapping("/getPreListByPid")
    public List<Prescription> getPreListByPid(String pid){
        return patientService.getPreListByPid(pid);
    }

    @RequestMapping("/pay")
    public int pay(String preid,int prestate){
        return patientService.changePrestate(preid,prestate);
    }

    @RequestMapping("/checkMedlist")
    public List<Pharmacy> checkMedlist(String preid){
        return patientService.checkMedlist(preid);
    }

    @RequestMapping("/getPidByPname")
    public List<Integer> getPidByPname(String pname){
        if(pname.isEmpty()){
            return null;
        }
        return patientService.getPidByPname(pname);
    }

    @RequestMapping("/queryDocList")
    public List<DocDuty> queryDocList(String deptno,int dutyday){
        return patientService.queryDocList(deptno,dutyday);
    }

    @RequestMapping("/cancelReg")
    public int cancelReg(String pid){
        return patientService.cancelReg(pid);
    }
}
