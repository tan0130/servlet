package ssh.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import ssh.entity.User;
import ssh.hibernate.HibernateSessionFactory;

import java.util.List;

/**
 * create by tan on 2018/5/11
 * 用户操作接口实现类
 **/
@Transactional
public class UserDAOImpl implements UserDAO{
    public User queryUser(int id) {
        Session session = HibernateSessionFactory.getSession();
        String hql = "from User u where u.id = ?";
        Query query = session.createQuery(hql);
        query.setString(0, String.valueOf(id));
        session.flush();
        return (User)query.uniqueResult();
    }

    public List<User> queryAllUser() {
        Session session = HibernateSessionFactory.getSession();
        String hql = "from User";
        Query query = session.createQuery(hql);
        session.flush();
        return query.list();
    }

    public User queryUser(int id, String password) {
        Session session = HibernateSessionFactory.getSession();
        String hql = "from User u where u.id = ? and u.password = ?";
        Query query = session.createQuery(hql);
        query.setString(0, String.valueOf(id));
        query.setString(1, password);
        session.flush();
        return (User)query.uniqueResult();
    }

    public boolean updatePswd(User user) {
        Session session = HibernateSessionFactory.getSession();
        String hql = "update User u set u.password = ? where u.id = ?";
        Query query = session.createQuery(hql);
        Transaction tran = null;
        try {
            tran = session.beginTransaction();
            query.setString(0, user.getPassword());
            query.setString(1, String.valueOf(user.getId()));
            query.executeUpdate();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
        } finally {
            if(session != null) {
                session.flush();
            }
        }
        return query.executeUpdate() > 0;
    }
}
