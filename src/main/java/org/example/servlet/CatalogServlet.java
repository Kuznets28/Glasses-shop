package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Dao.GlassesDao;
import org.example.model.Glasses;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    private GlassesDao glassesDao;
    @Override
    public void init(){
        glassesDao = new GlassesDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Glasses> glassesList;
        if(search == null || search.trim().isEmpty()){
            glassesList = glassesDao.getAllGlasses();
        }
        else {
            glassesList = glassesDao.searchGlasses(search);
        }

        request.setAttribute("glassesList" , glassesList);
        request.getRequestDispatcher("/views/catalog.jsp").forward(request, response);

    }
}
