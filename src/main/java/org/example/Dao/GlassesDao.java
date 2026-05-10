package org.example.Dao;

import org.example.DBConfig.DatabasePool;

import org.example.model.Glasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GlassesDao {
    private static final Logger logger = LoggerFactory.getLogger(GlassesDao.class);

    public List<Glasses> getAllGlasses() {
        String sql = "select * from glasses";
        try(Connection connect = DatabasePool.getConnection();
            PreparedStatement pst = connect.prepareStatement(sql);
            ResultSet res = pst.executeQuery()){
            ArrayList<Glasses> array = new ArrayList<>();
            while(res.next()){
                Glasses glasses = new Glasses(
                        res.getLong("id"),
                        res.getString("sku"),
                        res.getString("name"),
                        res.getInt("price"),
                        res.getString("pathToPhoto")
                );
                array.add(glasses);
            }
            logger.info("Получение всех очков");
            return array;
        }
        catch (SQLException e){
            logger.error("SQL ОШИБКА: при получении всех очков:", e);
            return new ArrayList<>();
        }
    }

    public List<Glasses> getGlassesForHome(){
        String sql = "select * from glasses limit 10";
        try(Connection connect = DatabasePool.getConnection();
            PreparedStatement pst = connect.prepareStatement(sql);
            ResultSet res = pst.executeQuery()){
            ArrayList<Glasses> array = new ArrayList<>();
            while(res.next()){
                Glasses glasses = new Glasses(
                        res.getLong("id"),
                        res.getString("sku"),
                        res.getString("name"),
                        res.getInt("price"),
                        res.getString("pathToPhoto")
                );
                array.add(glasses);
            }
            logger.info("Получение 10 первых очков для главной страници");
            return array;
        }
        catch (SQLException e){
            logger.error("SQL ОШИБКА: при получении 10 очков:", e);
            return new ArrayList<>();
        }
    }

    public void addGlasses(Glasses glasses){
        String sql = "insert into glasses(sku, name, price, path) values(?, ?, ?, ?)";
        try (Connection connect = DatabasePool.getConnection();
            PreparedStatement pst = connect.prepareStatement(sql)){
            pst.setString(1, glasses.getSku());
            pst.setString(2, glasses.getNameModel());
            pst.setInt(3, glasses.getPrice());
            pst.setString(4, glasses.getPathToPhoto());
            int row = pst.executeUpdate();

            if (row > 0){
                logger.info("Добавлеы очки с названием {}",  glasses.getNameModel());
            }
            else {
                logger.warn("Не получилось добавить очки {}", glasses.getNameModel());
            }
        }
        catch (SQLException e){
            logger.error("SQL ОШИБКА: при добавлении очков с названием {}", glasses.getNameModel(), e);
        }
    }

    public List<Glasses> searchGlasses(String search){
        String sql = "select * from glasses where name like ?";
        try(Connection connect = DatabasePool.getConnection();
            PreparedStatement pst = connect.prepareStatement(sql);
        ){
            List<Glasses> list = new ArrayList<>();
            pst.setString(1,"%" + search + "%");
            try(ResultSet res = pst.executeQuery()){
                while (res.next()){
                list.add( new Glasses(
                        res.getLong("id"),
                        res.getString("sku"),
                        res.getString("name"),
                        res.getInt("price"),
                        res.getString("pathToPhoto")
                ));}
                logger.info("Получение кталога при происке {} кол-во записей {}", search, list.size());
                return list;
            }
        }
        catch (SQLException e){
            logger.error("SQL ОШИБКА: при получении поиске по каталогу", e);
            return new ArrayList<>();
        }
    }
}

