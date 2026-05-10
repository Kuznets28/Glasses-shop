package org.example.Dao;

import org.example.DBConfig.DatabasePool;
import org.example.Dto.UserRegisterDto;
import org.example.Dto.UserSession;
import org.example.Dto.UserVerification;
import org.example.model.Role;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDao {
    public static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    public UserDao(){

    }


    public ArrayList<UserSession> getAllUsers() throws SQLException {
        logger.debug("Получение всех пользователь из бд");
        String sql = "SELECT id, name, email, role, phone from users";
        try(Connection connection = DatabasePool.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()){
            ArrayList<UserSession> array = new ArrayList<>();
            while(rs.next()){
                UserSession user = new UserSession(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("phone")
                );
                array.add(user);
            }
            if (array.isEmpty()){
                logger.warn("При попытке получения всех пользователей нет не одного пользователя");
                throw new SQLException();
            }
            return array;
        }
    }

    public UserSession createUser(UserRegisterDto user) throws SQLException {
        String sql = "insert into users (name, password, email, phone) values (?, ?, ?, ?) returning id";
        try (Connection connect = DatabasePool.getConnection();
             PreparedStatement pst = connect.prepareStatement(sql)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPhone());
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    return new UserSession(
                            resultSet.getLong("id"),
                            user.getName(),
                            user.getEmail(),
                            Role.USER,
                            user.getPhone()
                    );
                } else {
                    logger.warn("При создании пользователя {} не вернулся id", user.getEmail());
                    throw new SQLException("НЕ вернулся id созданого пользователя");
                }
            }
        }
    }

    public UserSession userVerification(UserVerification user) throws SQLException{
        logger.debug("Попытка входа в аккаунт {}", user.getEmail());
        String sql = "select id, name, role, phone from users where email = ? and password = ?";
        try (Connection connection = DatabasePool.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            try(ResultSet result = pst.executeQuery()){
                if (result.next()){
                    return new UserSession(
                            result.getLong("id"),
                            result.getString("name"),
                            user.getEmail(),
                            Role.valueOf(result.getString("role")),
                            result.getString("phone")
                    );
                }
                else {
                    logger.info("Неудачная поппытка входа в аккаунт {}", user.getEmail());
                    return null;
                }
            }
        }
    }

}

