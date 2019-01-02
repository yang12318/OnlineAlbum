package dao.impl;

import dao.AlbumDao;
import model.Album;
import model.User;
import util.DatabaseUtil;
import util.DateUtil;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl implements AlbumDao {
    @Override
    public boolean isAlbumNameExist(int userId, String albumName) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from album where userid = ? and name = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setInt(1, userId);
            pst.setString(2, albumName);
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
    public boolean createAlbum(Album album) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "insert into album(name, userid, createdate, description)" +
                "values(?,?,?,?)";
        if(isAlbumNameExist(album.getId(), album.getName())) {
            flag = false;
        }
        else {
            try {
                connection = DatabaseUtil.getConnection();
                connection.setAutoCommit(false);
                pst = connection.prepareStatement(sql);
                pst.setString(1, album.getName());
                pst.setInt(2, album.getUserid());
                pst.setString(3, DateUtil.getNowDateTime("YYYY-MM-DD HH:MM:ss"));
                pst.setString(4, album.getDescription());
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
        }
        return flag;
    }

    @Override
    public boolean updateAlbum(Album album) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "update album set name = ?, description = ? where id = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, album.getName());
            pst.setString(2, album.getDescription());
            pst.setInt(3, album.getId());
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
    public boolean deleteById(int id) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "delete from album where id = ?";
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
    public Album findById(int id) {
        Album album = new Album();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from album where id = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            if(id <= 0) {
                album = null;
            }
            else {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if(rs.next()) {
                    album.setId(rs.getInt("id"));
                    album.setUserid(rs.getInt("userid"));
                    album.setDescription(rs.getString("description"));
                    album.setName(rs.getString("name"));
                    album.setDate(rs.getString("createdate"));
                }
                else {
                    album = null;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            album = null;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return album;
    }

    @Override
    public List<Album> findByUserId(int userId) {
        List<Album> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from album where userid = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setInt(1, userId);
            rs = pst.executeQuery();
            Album album;
            while(rs.next()) {
                album = new Album();
                album.setId(rs.getInt("id"));
                album.setDate(rs.getString("createdate"));
                album.setDescription(rs.getString("description"));
                album.setUserid(rs.getInt("userid"));
                album.setName(rs.getString("name"));
                list.add(album);
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
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return list;
    }

    @Override
    public List<Album> findAll() {
        List<Album> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from album";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            Album album;
            while(rs.next()) {
                album = new Album();
                album.setId(rs.getInt("id"));
                album.setDate(rs.getString("createdate"));
                album.setDescription(rs.getString("description"));
                album.setUserid(rs.getInt("userid"));
                album.setName(rs.getString("name"));
                list.add(album);
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
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return list;
    }


}
