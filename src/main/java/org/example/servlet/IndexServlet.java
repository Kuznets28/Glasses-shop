package org.example.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Dao.GlassesDao;
import org.example.model.Glasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {
    private GlassesDao glassesDao;
    @Override
    public void init(){
         glassesDao = new GlassesDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Glasses> glassesList = glassesDao.getAllGlasses();
        glassesList.add(new Glasses("TEST2", "Солнцезащитные", 1999, "test2.jpg"));
        request.setAttribute("AllGlasses", glassesList);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
