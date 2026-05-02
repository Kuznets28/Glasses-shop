<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.shop.model.Glasses" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Каталог очков</title>
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
        <h2>Весь каталог</h2>

        <!-- Форма фильтрации -->
        <div class="filters">
            <form action="${pageContext.request.contextPath}/catalog" method="get">
                <div class="filter-group">
                    <label>Бренд:</label>
                    <select name="brand">
                        <option value="">Все бренды</option>
                        <%
                            List<String> brands = (List<String>) request.getAttribute("brands");
                            String currentBrand = (String) request.getAttribute("currentBrand");
                            if (brands != null) {
                                for (String brand : brands) {
                        %>
                        <option value="<%= brand %>" <%= brand.equals(currentBrand) ? "selected" : "" %>>
                            <%= brand %>
                        </option>
                        <%      }
                            }
                        %>
                    </select>
                </div>

                <div class="filter-group">
                    <label>Цена:</label>
                    <input type="number" name="minPrice" placeholder="от" step="100">
                    <input type="number" name="maxPrice" placeholder="до" step="100">
                </div>

                <div class="filter-group">
                    <label>Сортировка:</label>
                    <select name="sort">
                        <option value="">По умолчанию</option>
                        <option value="price_asc">Цена: по возрастанию</option>
                        <option value="price_desc">Цена: по убыванию</option>
                        <option value="name_asc">Название: А-Я</option>
                    </select>
                </div>

                <button type="submit" class="btn-small">Применить</button>
                <a href="${pageContext.request.contextPath}/catalog" class="btn-small">Сбросить</a>
            </form>
        </div>

        <div class="glasses-grid">
            <%
                List<Glasses> glassesList = (List<Glasses>) request.getAttribute("glassesList");
                if (glassesList != null && !glassesList.isEmpty()) {
                    for (Glasses glasses : glassesList) {
            %>
            <div class="glass-card">
                <img src="${pageContext.request.contextPath}/<%= glasses.getImageUrl() %>"
                     alt="<%= glasses.getName() %>"
                     onerror="this.src='https://via.placeholder.com/200'">
                <h3><%= glasses.getName() %></h3>
                <p class="brand"><%= glasses.getBrand() %></p>
                <p class="price"><%= glasses.getPrice() %> ₽</p>
                <p class="stock">В наличии: <%= glasses.getStock() %> шт.</p>
                <p class="description"><%= glasses.getDescription() %></p>

                <% if (glasses.getStock() > 0) { %>
                    <form action="${pageContext.request.contextPath}/cart" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="id" value="<%= glasses.getId() %>">
                        <input type="number" name="quantity" value="1" min="1" max="<%= glasses.getStock() %>" style="width: 60px;">
                        <button type="submit" class="btn-small">В корзину</button>
                    </form>
                <% } else { %>
                    <p class="out-of-stock">Нет в наличии</p>
                <% } %>
            </div>
            <%      }
                } else { %>
                <p>Товары не найдены</p>
            <% } %>
        </div>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 Магазин очков. Все права защищены.</p>
        </div>
    </footer>
</body>
</html>