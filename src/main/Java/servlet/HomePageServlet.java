package servlet;

import dao.AlbumDao;
import dao.impl.AlbumDaoImpl;
import dao.impl.UserDaoImpl;
import model.Album;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public HomePageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        req.setAttribute("user", user);
        AlbumDaoImpl impl = new AlbumDaoImpl();
        List<Album> albumList = impl.findByUserId(userId);
        req.setAttribute("albumList", albumList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("homepage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        //Or
        //404
    }
}
