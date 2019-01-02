package dao;

import model.Pic;

import java.util.List;

public interface PicDao {

    public boolean addPhoto(Pic photo);

    public List<Pic> findPhotoByAlbumId(int albumId);

    public boolean updateById(Pic photo);

    public boolean deleteById(int id);

    public Pic findById(int id);

    public int getPhotoNum(int albumId, int userId);
}
