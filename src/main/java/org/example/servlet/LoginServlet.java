package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Dao.UserDao;
import org.example.Dto.UserSession;
import org.example.Dto.UserVerification;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

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

       if (email == null || password == null || email.isBlank() || password.isBlank()){
           request.setAttribute("error", "Неверная почта или пароль");
            request.getRequestDispatcher("/views/login.jsp").forward(request,response);
       }

        UserSession session = userDao.userVerification(
                new UserVerification(
                        request.getParameter("email"),
                        request.getParameter("password")));
        if (session == null){
            request.setAttribute("error", "Неверная почта или пароль");
            request.getRequestDispatcher("/views/login.jsp").forward(request,response);
        }
        else{
            request.getSession().setAttribute("UserSession", session);
            response.sendRedirect(request.getContextPath() + "/home");
        }


    }
}
