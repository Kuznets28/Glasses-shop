package org.example.Dao;

import org.example.DBConfig.DatabasePool;

import org.example.model.Glasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GlassesDao {

    public ArrayList<Glasses> getAllGlasses() {
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
            return array;
        }
        catch (SQLException e){
            System.out.println("какаета хуня");
            return new ArrayList<>();
        }
    }

    public void addGlasses(Glasses glasses){
        String sql = "insert into glasses(sku, name, price, path) values(?, ?, ?, ?)";
        try (Connection connect = DatabasePool.getConnection();
            PreparedStatement pst = connect.prepareStatement(sql)){
            pst.executeUpdate();
        }
        catch (SQLException e){

        }
    }
}
