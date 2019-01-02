package dao;

import model.Album;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDao {

    public boolean isAlbumNameExist(int userId, String albumName);

    public boolean createAlbum(Album album);

    public boolean updateAlbum(Album album);

    public boolean deleteById(int id);

    public Album findById(int id);

    public List<Album> findByUserId(int userId);

    public List<Album> findAll();
}
