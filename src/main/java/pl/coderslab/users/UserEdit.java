package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        if (userId != null) {
            UserDao userDao = new UserDao();
            User read = userDao.read(Integer.parseInt(userId));
            request.setAttribute("user", read);
            getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userId != null && userName != null && !userName.isEmpty() && email != null && !email.isEmpty()
                && password != null && !password.isEmpty()) {

            UserDao userDao = new UserDao();

            User user = new User();
            user.setId(userId);
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(userDao.hashPassword(password));
            userDao.editUser(user);
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }
}
