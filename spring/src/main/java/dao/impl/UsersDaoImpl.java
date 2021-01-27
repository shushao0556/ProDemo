package dao.impl;

import dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersDaoImpl implements UsersDao {
    private UsersDao ud;
    @Override
    public void add() {
        System.out.println("将用户信息添加数据库");
    }
    public UsersDaoImpl() {}
    public UsersDaoImpl(UsersDao ud) {
        this.ud = ud;
    }

    public void setUd(UsersDao ud) {
        this.ud = ud;
    }
}
