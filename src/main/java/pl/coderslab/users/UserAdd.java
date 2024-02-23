package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        if (userName != null && userEmail != null && userPassword != null) {
            UserDao userDao = new UserDao();

            User user = new User();
            user.setUserName(userName);
            user.setEmail(userEmail);
            user.setPassword(userDao.hashPassword(userPassword));

            userDao.createUser(user);
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }
}
