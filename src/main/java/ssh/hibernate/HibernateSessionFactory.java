package ssh.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * create by tan on 2018/5/11
 * hibernate session工厂类
 * 只支持映射文件方式的映射，不支持Annotation注释映射
 **/
public class HibernateSessionFactory {
    private static String CONFIG_FILE_LOCATION = "hibernate/hibernate.cfg.xml";//指定配置文件路径
    private static final ThreadLocal<Session> threadLocal
            = new ThreadLocal<Session>();//定义ThreadLocal对象
    private  static Configuration configuration
            = new Configuration();//定义Configuration对象
    private static org.hibernate.SessionFactory sessionFactory;//定义SessionFactory对象
    private static String configFile = CONFIG_FILE_LOCATION;
    static {
        try {
            configuration.configure(configFile);//读取配置文件
            sessionFactory =
                    configuration.buildSessionFactory();//根据配置文件创建sessionFactory对象
        } catch (Exception e) {
            System.err
                    .println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }
    private HibernateSessionFactory() {
    }
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();//从ThreadLocal对象中获得Session对象

        if (session == null || !session.isOpen()) {//判断获得的Session对象是否为空或者没有打开
            if (sessionFactory == null) {//如果没有创建SessionFactory对象，则首先创建
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession()
                    : null;//如果SessionFactory对象不为空，则调用其openSession方法创建session对象
            threadLocal.set(session);//在ThreadLocal对象中保存该Session对象
        }
        return session;
    }
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);//读取配置文件
            sessionFactory =
                    configuration.buildSessionFactory();//根据配置文件创建sessionFactory对象
        } catch (Exception e) {
            System.err
                    .println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();//从ThreadLocal对象中获得Session对象
        threadLocal.set(null);//将当前线程Session对象从ThreadLocal对象中移除

        if (session != null) {
            session.close();
        }
    }
    public static org.hibernate.SessionFactory getSessionFactory() {//取得SessionFactory对象
        return sessionFactory;
    }
    public static void setConfigFile(String configFile) {//设置新的配置文件
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }
    public static Configuration getConfiguration() {//获得Configuration对象
        return configuration;
    }
}
