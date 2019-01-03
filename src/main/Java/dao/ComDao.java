package dao;

import model.Comment;
import java.util.List;

public interface ComDao {

    public boolean addCom(Comment comment);

    public List<Comment> findCommentByPicId(int picId);

    public boolean deleteById(int id);

    public Comment findById(int id);
}
