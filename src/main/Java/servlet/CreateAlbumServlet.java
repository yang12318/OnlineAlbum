package servlet;

import dao.impl.AlbumDaoImpl;
import model.Album;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createAlbum")
public class CreateAlbumServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public CreateAlbumServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("createAlbum.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        if(name == null || name.length() <= 0) {
            req.setAttribute("errorMessage", "相册名不能为空");
            req.setAttribute("returnUrl", "../createAlbum");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }
        else {
            Album album=new Album();
            album.setDescription(description);
            album.setName(name);
            User user = (User) req.getSession().getAttribute("user");
            album.setUserid(user.getId());
            AlbumDaoImpl impl = new AlbumDaoImpl();
            if(impl.createAlbum(album)) {
                //创建成功
                //这里或许应该在前端加入提示，然后从前端跳转
                resp.sendRedirect("/homepage");
            }
            else {
                req.setAttribute("errorMessage", "相册创建失败");
                req.setAttribute("returnUrl", "../createAlbum");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }
        }
    }
}
