<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход в магазин очков</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>👓 Магазин очков</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/home">Главная</a>
                <a href="${pageContext.request.contextPath}/login">Вход</a>
            </nav>
        </div>
    </header>

    <main class="container">
        <div class="login-container">
            <div class="login-box">
                <h2>Вход в магазин очков</h2>

                <% if (request.getAttribute("error") != null) { %>
                    <div class="error-message">
                        <%= request.getAttribute("error") %>
                    </div>
                <% } %>

                <form action="${pageContext.request.contextPath}/login" method="post" class="login-form">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email"
                               value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                               placeholder="Введите ваш email"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="password">Пароль:</label>
                        <input type="password" id="password" name="password"
                               placeholder="Введите пароль"
                               required>
                    </div>

                    <button type="submit" class="btn btn-login">Войти</button>
                </form>

                <div class="login-links">
                    <a href="${pageContext.request.contextPath}/register">Нет аккаунта? Зарегистрироваться</a>
                    <a href="${pageContext.request.contextPath}/forgot-password">Забыли пароль?</a>
                </div>
            </div>
        </div>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 Магазин очков. Все права защищены.</p>
        </div>
    </footer>
</body>
</html>