package servlet;

import dao.impl.PicDaoImpl;
import model.Pic;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/photo/*")
public class PhotoApiServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public PhotoApiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        if(url.contains("api/photo/delete?id=")) {
            int deleteid = -1;
            try {
                deleteid = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            PicDaoImpl impl = new PicDaoImpl();
            impl.deleteById(deleteid);
        }
        else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
        String url = req.getRequestURL().toString();
        if(url.contains("api/photo/post")) {
            PicDaoImpl impl = new PicDaoImpl();
            Pic pic = new Pic();
            pic.setDescription(req.getParameter("description"));
            pic.setTitle(req.getParameter("name"));
            impl.updateById(pic);
        }
        else {

        }
    }
}
