package ssh.entity;

/**
 * create by tan on 2018/5/11
 * 用户实体类
 **/
public class User {
    private int id; // 用户编号
    private String name; // 用户姓名
    private String password; // 用户密码
    private int age; // 用户年龄
    private String sex; // 用户性别

    public User(int id, String name, String password, int age, String sex) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
