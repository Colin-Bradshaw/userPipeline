package com.ss.usermsvc.service;

import com.ss.usermsvc.dao.UserDAO;
import com.ss.usermsvc.entity.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    ConnectionUtil connUtil = new ConnectionUtil();

    public List<User> getUserByID(int id) {
        System.out.println("Hi");
        try {
            return readUsers("SELECT user.id, user.role_id, user.given_name, user.family_name, user.username," +
                    "user.email, user.password, user.phone, user_role.name FROM user " +
                    "LEFT JOIN user_role ON user.role_id = user_role.id WHERE user.id = ?", new Object[]{id});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //default return for failure
        User u = new User();
        u.setEmail("Hi");
        User[] uarr = new User[]{u};

        return Arrays.asList(uarr);
    }

    public List<User> readUsers(String sql, Object[] param) throws SQLException, ClassNotFoundException{
        Connection conn = connUtil.getConnection();
        try {
            UserDAO uDAO = new UserDAO(conn);
            ArrayList<User> all= (ArrayList<User>) uDAO.read(sql, param);
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            if(conn!=null) {
                conn.rollback();
            }
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return null;
    }
    public Boolean addUser(User u) throws ClassNotFoundException, SQLException {
        Connection conn = connUtil.getConnection();
        try {
            UserDAO uDAO = new UserDAO(conn);
            System.out.println(u.getRole_ID());
            Integer id = uDAO.saveWithPK("INSERT INTO user SET role_id = (SELECT id FROM user_role WHERE id = ?)," +
                            "given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ?",
                    (new Object[] {u.getRole_ID(), u.getGiven_Name(), u.getFamily_Name(), u.getUsername(), u.getEmail(),
                            u.getPassword(), u.getPhone()}));
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(conn!=null) {
                conn.rollback();
            }
            return false;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
    }
    public Boolean deleteUser(User u)  throws ClassNotFoundException, SQLException {
        Connection conn = connUtil.getConnection();
        try {
            UserDAO uDAO = new UserDAO(conn);
            uDAO.save("DELETE FROM user WHERE id = ?",new Object[]{u.getId()});
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(conn!=null) {
                conn.rollback();
            }
            return false;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
    }
    public Boolean updateUser(User u) throws ClassNotFoundException, SQLException {
        Connection conn = connUtil.getConnection();
        try {
            UserDAO uDAO = new UserDAO(conn);
            uDAO.save("UPDATE user SET role_id = (SELECT id FROM user_role WHERE id = ?), given_name = ?," +
                            "family_name = ?, username = ?, email = ?, password = ?, phone = ?, roles = ? WHERE id = ?",
                    (new Object[] {u.getRole_ID(), u.getGiven_Name(), u.getFamily_Name(), u.getUsername(), u.getEmail(),
                            u.getPassword(), u.getPhone(), u.getRoles(), u.getId()}));
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(conn!=null) {
                conn.rollback();
            }
            return false;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
    }

    public List<User> getUsers(String id) {
        // overwrite value of id to prevent injection
        id = "*";
        try {
            return readUsers("SELECT user.id, user.role_id, user.given_name, user.family_name, user.username," +
                    "user.email, user.password, user.phone, user_role.name FROM user " +
                    "LEFT JOIN user_role ON user.role_id = user_role.id WHERE user.id > 0", null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
