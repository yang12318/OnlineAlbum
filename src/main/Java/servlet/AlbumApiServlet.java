package servlet;

import dao.impl.AlbumDaoImpl;
import model.Album;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/album/*")
public class AlbumApiServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public AlbumApiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        if(url.contains("api/album/delete?id=")) {
            int deleteid = -1;
            try {
                deleteid = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            AlbumDaoImpl impl = new AlbumDaoImpl();
            impl.deleteById(deleteid);
        }
        else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
        String url = req.getRequestURL().toString();
        if(url.contains("api/album/post")) {
            AlbumDaoImpl impl = new AlbumDaoImpl();
            Album album = new Album();
            HttpSession session = req.getSession();
            User user;
            user = (User) session.getAttribute("user");
            //如果更新的命名和原来一样
            if(album.getName().equals(req.getParameter("name"))){
                album.setDescription(req.getParameter("description"));
                impl.updateAlbum(album);
            }
            else {
                //如果更新的命名和该用户其他相册重了
                if(impl.isAlbumNameExist(user.getId(),req.getParameter("name"))) {

                }
                else {
                    album.setDescription(req.getParameter("description"));
                    album.setName(req.getParameter("name"));
                    impl.updateAlbum(album);
                }
            }
        }
        else {

        }
    }
}
