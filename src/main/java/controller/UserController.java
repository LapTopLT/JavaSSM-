package controller;/*
 *Author:Litao
 *Created in:
 */

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Menu;
import pojo.Registration;
import pojo.User;
import service.UserService;
import util.ImageUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin    //支持前端Vue的跨域访问
@RestController //如果本类中全部都是ajax请求，则使用此注解，方法上的ResponseBody可以不写。
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService service;

    String str;

    @RequestMapping("/login")
    public User login(String uname,String upassword){
        User u = service.queryUser(uname,upassword);
        return u;
    }

    @RequestMapping("/regist")
    public int createUser(User user){
        String userId = String.valueOf(System.currentTimeMillis());
        user.setUid(userId);
        return service.createUser(user);
    }

    @RequestMapping("/queryMenu")
    public List<Menu> queryMenu(int roleid){
        List<Menu> menus = service.queryMenu(roleid);
        return menus;
    }

    @RequestMapping("/checkUname")
    public int checkUname(String uname){
        return service.checkUname(uname);
    }
/*
    @RequestMapping("/selectUserPage")
//    @ResponseBody
    public List<User> selectUserPage(String userName,String userSex, Integer page){
        int startRow = 0;
        if(page != null){
            startRow = (page - 1) * 5;
        }
        return service.selectUserPage(userName,userSex,startRow);
    }

    @RequestMapping("/getRowCount")
//    @ResponseBody
    public int getRowCount(String userName,String userSex){
        return service.getRowCount(userName,userSex);
    }

    @RequestMapping("/deleteUserById")
//    @ResponseBody
    public int deleteUserById(String userId){
        return service.deleteUserById(userId);
    }

    @RequestMapping("/createUser")
//    @ResponseBody
    public int createUser(User u){
        String userId = String.valueOf(System.currentTimeMillis());
        u.setUserId(userId);
        return service.createUser(u);
    }*/

    @RequestMapping("/verifyCode")
    public void generateImage(HttpServletRequest request,
                              HttpServletResponse response){
        ImageUtil imageUtil = ImageUtil.getInstance();
        ByteArrayInputStream image = imageUtil.getImage();
        String code = imageUtil.getStr();

        this.str = code;
        response.setContentType("image/jpeg");
        byte[] bytes = new byte[1024];
        try(ServletOutputStream out = response.getOutputStream()){
            while(image.read(bytes) != -1){
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/checkCode")
    public int checkCode(String code){
        String str = this.str;
        code = code.toLowerCase();
        str = str.toLowerCase();
        if(code.equals(str)){
            return 1;
        }else{
            return 0;
        }
    }
}
