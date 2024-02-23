package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        if (userId != null) {
            UserDao userDao = new UserDao();
            User read = userDao.read(Integer.parseInt(userId));
            request.setAttribute("user", read);
            getServletContext().getRequestDispatcher("/users/delete.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");

        if (userId != null) {
            UserDao userDao = new UserDao();
            userDao.delete(Integer.parseInt(userId));
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }
}
