package org.example.Dao;

import org.example.DBConfig.DatabasePool;
import org.example.Dto.UserSession;
import org.example.model.Cart;
import org.example.model.Glasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDao {
    private final Logger logger = LoggerFactory.getLogger(CartDao.class);

    public Cart getCart(UserSession userSession){
        logger.debug("получение корзины пользователя {}", userSession.getId());
        String sql = "select g.id as glasses_id, g.article, g.name_model, g.price, g.description, g.path_to_photo," +
                "ci.quantity, (g.price * ci.quantity) AS total_price" +
                "from cart c join cart_item ci on c.id = ci.cart_id " +
                "join glasses g on ci.glasses_id = g.id where c.user_id = ?";
        try(Connection conn = DatabasePool.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, userSession.getId());
            try(ResultSet resultSet = pst.executeQuery()){
                Cart cart = new Cart();
                int i = 0;
                while (resultSet.next()){
                    if (i == 0){
                        cart.setTotal_price(resultSet.getInt("total_price"));
                        i++;
                    }
                    cart.addCartItem(new Glasses(resultSet.getInt("glasses_id"),
                                                resultSet.getString("article"),
                                                resultSet.getString("name_model"),
                                                resultSet.getInt("price"),
                                                resultSet.getString("description"),
                                                resultSet.getString("path_to_photo")),
                                    resultSet.getInt("quantity"));
                }
                return cart;
            }
            catch (SQLException e){
                logger.warn("ОШИБКА: при ResultSet {}", e);
                return null;
            }
        }
        catch (SQLException e){
            logger.warn("ОШИБКА при подключении и отоправки запроса {}", e);
            return null;
        }
    }

}
