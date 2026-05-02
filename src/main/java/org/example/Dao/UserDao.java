package org.example.Dao;

import org.example.DBConfig.DatabasePool;
import org.example.Dto.UserRegisterDto;
import org.example.Dto.UserSession;
import org.example.Dto.UserVerification;
import org.example.model.Role;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    public UserDao(){

    }


    public ArrayList<UserSession> getAllUsers() throws SQLException {
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
            return array;
        }
        catch(SQLException e){
            e.printStackTrace();
            throw e;
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
                    //ошибка при запросе и возврате id
                    throw new SQLException("Failed to create user - no ID returned");
                }
            }
        } catch (SQLException e) {
            //Ошибка при подключении
            e.printStackTrace();
            throw e;  // Пробрасываем дальше
        }
    }

    public UserSession userVerification(UserVerification user){
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
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        //Надо обработать в сервлете (Пользователя нет)
        return null;
    }

}

