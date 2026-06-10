package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Dto.UserSession;

import java.io.IOException;


@WebServlet({"/userProfile", "/updateUserProfile"})
public class UserProfileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserSession user = (UserSession) request.getSession().getAttribute("UserSession");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else{
            request.getRequestDispatcher("/views/userProfile.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
