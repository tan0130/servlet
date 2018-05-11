package ssh.dao;

import ssh.entity.User;

import java.util.List;

/**
 * create by tan on 2018/5/11
 * 用户操作接口
 **/
public interface UserDAO {
    public User queryUser(int id); // 根据id来查询所有用户信息
    public List<User> queryAllUser(); // 查询所有用户信息
    public User queryUser(int id,String password); // 根据id和密码来查询密码是否正确
    public boolean updatePswd(User user); // 根据id修改密码
}
