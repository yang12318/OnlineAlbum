package dao.impl;

import dao.UserDao;
import model.User;
import util.DatabaseUtil;
import util.DateUtil;
import util.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean isUsernameExist(String username) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from userInfo where username = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()) {
                flag = true;
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            flag = false;
        } finally {
            DatabaseUtil.close(rs,pst, connection);
        }
        return flag;
    }

    @Override
    public User addUser(User user){
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "insert into userInfo(username, password, registertime)" +
                " values(?,?,?)";
        connection = DatabaseUtil.getConnection();
        if(isUsernameExist(user.getName())) {
            //判断用户名是否存在
            user = null;
        }
        else {
            try {
                connection.setAutoCommit(false);
                pst = connection.prepareStatement(sql);
                pst.setString(1, user.getName());
                pst.setString(2, MD5Util.encode(user.getPassword()));
                pst.setString(3, DateUtil.getNowDateTime("YYYY-MM-DD HH:MM:ss"));
                if(pst.executeUpdate() != 1) {
                    user = null;
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
                user = null;
            } finally {
                DatabaseUtil.close(null, pst, connection);
            }
        }
        return user;
    }

    @Override
    public boolean updatePassword(User user) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "update userInfo set password=? where id = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, MD5Util.encode(user.getPassword()));
            pst.setInt(2, user.getId());
            if(pst.executeUpdate() != 1) {
                flag = false;
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            flag = false;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return flag;
    }

    @Override
    public User login(User user) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from userInfo where username = ? and password = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, MD5Util.encode(user.getPassword()));
            rs = pst.executeQuery();
            if(rs.next()) {
                user = findById(rs.getInt("id"));
            }
            else {
                user = null;
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            user = null;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from userInfo where id = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            if(id <= 0) {
                user = null;
            }
            else {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if(rs.next()) {
                    user.setName(rs.getString("username"));
                    user.setId(id);
                    user.setRegisterTime(rs.getString("registertime"));
                }
                else {
                    user = null;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            user = null;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return user;
    }
}
