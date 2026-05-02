<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.shop.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Корзина</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>👓 Магазин очков</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/">Главная</a>
                <a href="${pageContext.request.contextPath}/catalog">Каталог</a>
                <a href="${pageContext.request.contextPath}/cart">Корзина</a>
            </nav>
        </div>
    </header>

    <main class="container">
        <h2>Ваша корзина</h2>

        <%
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) request.getAttribute("cart");
            Double total = (Double) request.getAttribute("total");
            if (total == null) total = 0.0;
        %>

        <% if (cart == null || cart.isEmpty()) { %>
            <div class="empty-cart">
                <p>Корзина пуста</p>
                <a href="${pageContext.request.contextPath}/catalog" class="btn">Перейти в каталог</a>
            </div>
        <% } else { %>
            <div class="cart-container">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Товар</th>
                            <th>Цена</th>
                            <th>Количество</th>
                            <th>Сумма</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (CartItem item : cart.values()) {
                        %>
                        <tr>
                            <td>
                                <%= item.getGlasses().getName() %><br>
                                <small><%= item.getGlasses().getBrand() %></small>
                            </td>
                            <td><%= item.getGlasses().getPrice() %> ₽</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/cart" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="id" value="<%= item.getGlasses().getId() %>">
                                    <input type="number" name="quantity" value="<%= item.getQuantity() %>"
                                           min="0" max="<%= item.getGlasses().getStock() %>" style="width: 60px;">
                                    <button type="submit" class="btn-update">Обновить</button>
                                </form>
                            </td>
                            <td><%= item.getSubtotal() %> ₽</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/cart?action=remove&id=<%= item.getGlasses().getId() %>"
                                   class="btn-remove"
                                   onclick="return confirm('Удалить товар?')">Удалить</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3" style="text-align: right;"><strong>Итого:</strong></td>
                            <td><strong><%= total %> ₽</strong></td>
                            <td></td>
                        </tr>
                    </tfoot>
                </table>

                <div class="cart-actions">
                    <a href="${pageContext.request.contextPath}/catalog" class="btn">Продолжить покупки</a>
                    <a href="${pageContext.request.contextPath}/cart?action=clear" class="btn"
                       onclick="return confirm('Очистить корзину?')">Очистить корзину</a>
                    <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary">Оформить заказ</a>
                </div>
            </div>
        <% } %>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 Магазин очков. Все права защищены.</p>
        </div>
    </footer>
</body>
</html>