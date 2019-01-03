package dao.impl;

import dao.ComDao;
import model.Comment;
import model.Pic;
import util.DatabaseUtil;
import util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComDaoImpl implements ComDao {
    @Override
    public boolean addCom(Comment comment) {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean flag = true;
        String sql = "insert into comment(comment, date, picid, userid)" +
                " values(?,?,?,?)";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, comment.getContent());
            pst.setString(2, DateUtil.getNowDateTime("YYYY-MM-DD HH:MM:ss"));
            pst.setInt(3, comment.getPicid());
            pst.setInt(4, comment.getUserid());
            if(pst.executeUpdate() != 1) {
                flag = false;
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
            flag = false;
        } finally {
            DatabaseUtil.close(null, pst, connection);
        }
        return flag;
    }

    @Override
    public List<Comment> findCommentByPicId(int picId) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Comment> list = new ArrayList<>();
        String sql = "select * from comment where picid = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setInt(1, picId);
            rs = pst.executeQuery();
            Comment comment;
            while(rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setUsername(rs.getString("username"));
                comment.setContent(rs.getString("content"));
                comment.setDate(rs.getString("date"));
                comment.setPicid(rs.getInt("picid"));
                comment.setUserid(rs.getInt("userid"));
                list.add(comment);
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
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "delete from comment where id = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
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
            DatabaseUtil.close(null, pst, connection);
        }
        return flag;
    }

    @Override
    public Comment findById(int id) {
        Comment comment = new Comment();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from comment where id = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            if(id <= 0) {
                comment= null;
            }
            else {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if(rs.next()) {
                    comment.setId(rs.getInt("id"));
                    comment.setUsername(rs.getString("username"));
                    comment.setContent(rs.getString("content"));
                    comment.setDate(rs.getString("date"));
                    comment.setPicid(rs.getInt("picid"));
                    comment.setUserid(rs.getInt("userid"));
                }
                else {
                    comment = null;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            comment = null;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return comment;
    }
}
