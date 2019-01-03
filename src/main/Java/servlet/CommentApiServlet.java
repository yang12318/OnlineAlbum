package servlet;

import dao.impl.ComDaoImpl;
import model.Comment;
import model.User;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/comment/*")
public class CommentApiServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public CommentApiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //api/comment/delete?id=1
        String url = req.getRequestURL().toString();
        if(url.contains("api/comment/delete?id=")) {
            int deleteid = -1;
            try {
                deleteid = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            ComDaoImpl impl = new ComDaoImpl();
            impl.deleteById(deleteid);
        }
        else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //api/comment/post
        String url = req.getRequestURL().toString();
        if(url.contains("api/comment/post")) {
            ComDaoImpl impl = new ComDaoImpl();
            Comment comment = new Comment();
            HttpSession session = req.getSession();
            User user;
            user = (User) session.getAttribute("user");
            comment.setUsername(user.getName());
            comment.setContent(req.getParameter("content"));
            comment.setUserid(user.getId());
            comment.setPicid(Integer.parseInt(req.getParameter("picId")));
            comment.setDate(DateUtil.getNowDateTime("YYYY-MM-DD HH:MM:ss"));
            impl.addCom(comment);
        }
        else {

        }
    }
}
