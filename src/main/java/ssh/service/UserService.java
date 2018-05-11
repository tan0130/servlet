package ssh.service;


import ssh.entity.User;
import ssh.exception.UserException;

import java.util.List;

/**
 * create by tan on 2018/5/11
 * 用户操作业务逻辑层
 **/
public interface UserService {
    /**
     * 提供登录服务
     * @param id 用户编号
     * @param password 用户密码
     * return
     * */
    public User login(int id,String password) throws UserException;

    /**
     * 根据id查询员工信息
     * @param id 用户编号
     * return
     * */
    public User queryUser(int id) throws UserException;

    /**
     * 查询所有用户信息
     * return
     * */
    public List<User> queryAllUser() throws UserException;

    /**
     * 根据id修改密码
     * @param user 用户信息
     * return
     * */
    public boolean updatePswd(User user) throws UserException;
    /**
     * 根据id和密码查询用户
     * @param id 用户编号
     * @param password 用户密码
     * return
     * */
    public User queryUser(int id,String password) throws UserException;
}
