package controller;/*
*Author:Litao
*Created in:
*/

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pojo.Medicine;
import service.PrescriptionService;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pre")
public class PrescriptionController {
    @Resource
    PrescriptionService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
    }

    @RequestMapping("/addPre")
    public int addPre(
            String regid, String predesc, int prestate,
            Date predate, double totalPrice,String meds,String pid,
            String did
    ){
        ObjectMapper map = new ObjectMapper();
        int count = 0;
        try {
            List<Medicine> list = map.readValue(meds,new TypeReference<List<Medicine>>(){});
            System.out.println(list);
            String preid = String.valueOf(System.currentTimeMillis()).substring(5,10);
            count += service.addPre(regid,preid,pid,did,predesc,prestate,predate,totalPrice);
            count += service.addPharmacy(preid,list);
            count += service.changeRegstate(regid,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
