package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.Dao.UserDao;
import org.example.Dto.UserSession;
import org.example.Dto.UserVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init(){
        userDao = new UserDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
       String  password = request.getParameter("password");
       logger.info("Попытка входа в аккаунт {} с IP {}", email , request.getRemoteAddr());
       if (email == null || password == null || email.isBlank() || password.isBlank()){
           logger.info("Неудачная попаытка входа в аккаунт {}", email);
           request.setAttribute("error", "Неверная почта или пароль");
            request.getRequestDispatcher("/views/login.jsp").forward(request,response);
       }

        try {
            UserSession session = userDao.userVerification(
                    new UserVerification(
                            request.getParameter("email"),
                            request.getParameter("password")));
            if (session != null){
                request.getSession().setAttribute("UserSession",session);
                response.sendRedirect(request.getContextPath() + "/home");
            }
            else {
                request.setAttribute("error", "Неверная почта или пароль");
                request.getRequestDispatcher("/views/login.jsp").forward(request,response);
            }
        }
        catch (SQLException e){
            request.setAttribute("error","Техническая ошибка");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
