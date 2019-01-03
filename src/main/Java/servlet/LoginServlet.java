package servlet;

import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            resp.sendRedirect("../homepage");
        }
        else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        if(username == null || username.length() == 0 ||
                password == null || password.length() == 0) {
            req.setAttribute("errorMessage", "用户名和密码不能为空");
            req.setAttribute("returnUrl", "../login");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        UserDaoImpl impl = new UserDaoImpl();
        user = impl.login(user);
        if(user == null) {
            //设置返回路径，设置错误信息
            req.setAttribute("returnUrl","../login");
            req.setAttribute("errorMessage", "用户名或密码不正确");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }
        else {
            //登陆成功
            session.setAttribute("user", user);
            resp.sendRedirect("/homepage");
        }
    }
}
