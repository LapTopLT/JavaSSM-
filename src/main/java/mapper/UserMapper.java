package mapper;/*
 *Author:Litao
 *Created in:
 */

import org.apache.ibatis.annotations.Param;
import pojo.Menu;
import pojo.User;

import java.util.List;

public interface UserMapper {
    /*/user/selectUserPage?
    userName=z
    &userSex=男
    &page=null*/
    /*List<User> selectUserPage(
            @Param("userName")
            String userName,
            @Param("userSex")
            String userSex,
            @Param("startRow")
            int startRow);

    int createUser(User user);

    int deleteUserById(String userId);

    int getRowCount(
            @Param("userName")
            String userName,
            @Param("userSex")
            String userSex);*/

    User queryUser(
            @Param("uname")
            String uname,
            @Param("upassword")
            String upassword);

    int createUser(User user);

    List<Menu> queryMenu(int roleid);

    int checkUname(String uname);
}
