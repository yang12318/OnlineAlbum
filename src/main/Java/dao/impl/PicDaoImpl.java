package dao.impl;

import dao.PicDao;
import model.Pic;
import util.DatabaseUtil;
import util.DateUtil;
import util.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PicDaoImpl implements PicDao {
    @Override
    public boolean addPhoto(Pic photo) {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean flag = true;
        String sql = "insert into photo(title, description, publishtime, albumid, url, filename)" +
                " values(?,?,?,?,?,?)";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, photo.getTitle());
            pst.setString(2, photo.getDescription());
            pst.setString(3, DateUtil.getNowDateTime("YYYY-MM-DD HH:MM:ss"));
            pst.setInt(4, photo.getAlbumid());
            pst.setString(5, photo.getUrl());
            pst.setString(6, photo.getFilename());
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
    public List<Pic> findPhotoByAlbumId(int albumId) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Pic> list = new ArrayList<>();
        String sql = "select * from photo where albumid = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setInt(1, albumId);
            rs = pst.executeQuery();
            Pic pic;
            while(rs.next()) {
                pic = new Pic();
                pic.setId(rs.getInt("id"));
                pic.setTitle(rs.getString("title"));
                pic.setUrl(rs.getString("url"));
                pic.setPublishtime(rs.getString("publishtime"));
                pic.setFilename(rs.getString("filename"));
                pic.setAlbumid(rs.getInt("albumid"));
                pic.setDescription(rs.getString("description"));
                list.add(pic);
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
    public boolean updateById(Pic photo) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "update photo set title=?,description=? where id = ?";
        try {
            connection = DatabaseUtil.getConnection();
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            pst.setString(1, photo.getTitle());
            pst.setString(2, photo.getDescription());
            pst.setInt(3, photo.getId());
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
    public boolean deleteById(int id) {
        boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        String sql = "delete from photo where id = ?";
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
    public Pic findById(int id) {
        Pic pic = new Pic();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from photo where id = ?";
        connection = DatabaseUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            if(id <= 0) {
                pic= null;
            }
            else {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if(rs.next()) {
                    pic.setId(rs.getInt("id"));
                    pic.setTitle(rs.getString("title"));
                    pic.setUrl(rs.getString("url"));
                    pic.setPublishtime(rs.getString("publishtime"));
                    pic.setFilename(rs.getString("filename"));
                    pic.setAlbumid(rs.getInt("albumid"));
                    pic.setDescription(rs.getString("description"));
                }
                else {
                    pic = null;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            pic = null;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pst, connection);
        }
        return pic;
    }

    @Override
    public int getPhotoNum(int albumId, int userId) {
        return 0;
    }
}
