package ssh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssh.dao.UserDAO;
import ssh.entity.User;
import ssh.exception.UserException;

import java.util.List;

/**
 * create by tan on 2018/5/11
 * 用户操作业务逻辑层实现
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(int id, String password) throws UserException {
        if(userDAO.queryUser(id) == null) {
            throw new UserException("用户不存在");
        } else if(userDAO.queryUser(id, password) == null) {
            throw new UserException("密码不正确");
        }
        return userDAO.queryUser(id, password);
    }

    @Override
    public User queryUser(int id) throws UserException {
        if(userDAO.queryUser(id) == null) {
            throw new UserException("用户不存在");
        }
        return userDAO.queryUser(id);
    }

    @Override
    public List<User> queryAllUser() throws UserException {
        if(userDAO.queryAllUser() == null) {
            throw new UserException("用户不存在");
        }
        return userDAO.queryAllUser();
    }

    @Override
    public boolean updatePswd(User user) throws UserException {
        if(userDAO.queryUser(user.getId(), user.getPassword()) == null) {
            throw new UserException("密码不正确");
        }
        return userDAO.updatePswd(user);
    }

    @Override
    public User queryUser(int id, String password) throws UserException {
        if(userDAO.queryUser(id, password) == null) {
            throw new UserException("密码不正确");
        }
        return userDAO.queryUser(id, password);
    }
}
