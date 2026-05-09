<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, org.example.model.Glasses, org.example.Dto.UserSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Каталог очков</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
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
                <a href="${pageContext.request.contextPath}/login"><%= userSession.getName()%></a>
                <%}
                else{%>
                <a href="${pageContext.request.contextPath}/login">Вход</a>
                <%}%>
            </nav>
        </div>
    </header>

    <main class="container">
        <h2>Каталог</h2>
        <div class="glasses-grid">
            <%
                List<Glasses> glassesList = (List<Glasses>) request.getAttribute("glassesList");
                if (glassesList != null && !glassesList.isEmpty()) {
                    for (Glasses glasses : glassesList) {
            %>
            <div class="glass-card">
                <img src="${pageContext.request.contextPath}/<%= glasses.getPathToPhoto() %>"
                     alt="<%= glasses.getNameModel() %>"
                     onerror="this.src='https://via.placeholder.com/200'">
                <h3><%= glasses.getNameModel() %></h3>
                <p class="price"><%= glasses.getPrice() %> ₽</p>
            </div>
            <%      }
                } else { %>
                <p>Товары не найдены</p>
            <% } %>
        </div>
    </main>

</body>
</html>