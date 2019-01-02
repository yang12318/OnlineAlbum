package servlet;

import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(username == null || username.length() <= 0 ||
            password == null || password.length() <= 0) {
            request.setAttribute("errorMessage", "用户名和密码不能为空");
            request.setAttribute("returnUrl", "../register");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        else {
            User user=new User();
            user.setPassword(password);
            user.setName(username);
            UserDaoImpl impl = new UserDaoImpl();
            user = impl.addUser(user);
            if(user == null){
                //注册失败
                request.setAttribute("errorMessage", "注册失败");
                request.setAttribute("returnUrl", "../register");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            else {
                //注册成功
                response.sendRedirect("../login");
            }
        }
    }
}
