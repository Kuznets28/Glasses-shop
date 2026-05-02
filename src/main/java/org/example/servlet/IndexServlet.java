package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.Dao.GlassesDao;
import org.example.model.Glasses;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private GlassesDao glassesDao;
    @Override
    public void init(){
         glassesDao = new GlassesDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Glasses> glassesList = glassesDao.getAllGlasses();
        request.setAttribute("AllGlasses", glassesList);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
