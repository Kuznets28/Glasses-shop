<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.Dto.UserSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Профиль Пользователя</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>👓 Магазин очков</h1>
            <form action="${pageContext.request.contextPath}/catalog" method="get" class="search-form">
                        <input type="text" name="search" id="search" placeholder="Поиск очков" value="${param.search}">
                        <button type="submit">Найти</button>
            </form>
            <nav>
                <a href="${pageContext.request.contextPath}/home">Главная</a>
                <a href="${pageContext.request.contextPath}/catalog">Каталог</a>
                <%
                    UserSession userSession = (UserSession) request.getSession().getAttribute("UserSession");
                    if (userSession != null){
                %>
                <a href="${pageContext.request.contextPath}/userProfile"><%= userSession.getName()%></a>
                <%}
                else{%>
                <a href="${pageContext.request.contextPath}/login">Вход</a>
                <%}%>
            </nav>
        </div>
    </header>
    <h2>Учётные данные</h2>
    <p>email:</p> <p><%=((UserSession) request.getSession().getAttribute("UserSession")).getEmail()%></p>
</Body>
