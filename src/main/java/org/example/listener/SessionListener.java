package org.example.servlet;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.example.Dto.UserSession;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se){
        UserSession user = (UserSession) se.getSession().getAttribute("UserSession");
        if (user != null){

        }
    }
}
