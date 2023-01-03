package service;/*
 *Author:Litao
 *Created in:
 */

import pojo.Menu;
import pojo.User;

import java.util.List;

public interface UserService {
    /*List<User> selectUserPage(String userName, String userSex, int startRow);

    int createUser(User user);

    int deleteUserById(String userId);

    int getRowCount(String userName, String userSex);*/

    User queryUser(String uname,String upassword);

    int createUser(User user);

    List<Menu> queryMenu(int roleid);

    int checkUname(String uname);


}
