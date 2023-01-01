package com.hejow.toby_spring.user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;

public class UserDao {
    private static UserDao INSTANCE;
    private Connection c;
    private User user;
    private ConnectionMaker connectionMaker;

    // setter 방식의 DI
    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

//    public UserDao(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }


    // 스스로 컨테이너에게 요청
//    public UserDao() {
//        DaoFactory daoFactory = new DaoFactory();
//        this.connectionMaker = daoFactory.connectionMaker();
//    }

    // getBean()의 활용
//    public UserDao() {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
//    }

    public static synchronized UserDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDao();
        }
        return INSTANCE;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        this.c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        this.user = new User();
        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

//    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
