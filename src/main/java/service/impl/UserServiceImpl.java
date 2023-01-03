package service.impl;/*
 *Author:Litao
 *Created in:
 */

import mapper.UserMapper;
import org.springframework.stereotype.Service;
import pojo.Menu;
import pojo.User;
import service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;

    /*@Override
    public List<User> selectUserPage(String userName, String userSex, int startRow) {
        return mapper.selectUserPage(userName,userSex,startRow);
    }

    @Override
    public int createUser(User user) {
        return mapper.createUser(user);
    }

    @Override
    public int deleteUserById(String userId) {
        return mapper.deleteUserById(userId);
    }

    @Override
    public int getRowCount(String userName, String userSex)
    {
        return mapper.getRowCount(userName,userSex);
    }
*/
    @Override
    public User queryUser(String uname, String upassword) {
        return mapper.queryUser(uname,upassword);
    }

    @Override
    public int createUser(User user) {
        return mapper.createUser(user);
    }

    @Override
    public List<Menu> queryMenu(int roleid) {
        return mapper.queryMenu(roleid);
    }

    public int checkUname(String uname){
        return mapper.checkUname(uname);
    }
}
