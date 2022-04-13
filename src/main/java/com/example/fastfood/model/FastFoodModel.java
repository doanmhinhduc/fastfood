package com.example.fastfood.model;

import com.example.fastfood.entity.fastfood;
import com.example.fastfood.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FastFoodModel {
    public boolean save(fastfood obj)   {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "insert into food (id,name,categoryId,description,thumbnail,price,createdAt,updatedAt,status) value (?,?,?,?,?,?,?,?,?)"
                    );
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.setString(2,obj.getName());
            preparedStatement.setInt(3, obj.getCategoryId());
            preparedStatement.setString(4, obj.getDescription());
            preparedStatement.setString(5, obj.getThumbnail());
            preparedStatement.setDouble(6, obj.getPrice());
            preparedStatement.setTimestamp(7, obj.getCreatedAt());
            preparedStatement.setTimestamp(8, obj.getUpdatedAt());
            preparedStatement.setInt(9, obj.getStatus());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<fastfood> findAll()  {
        List<fastfood> ListObj = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = "Select * from food where status !=2";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("categoryId");
                String description = resultSet.getString("description");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                Timestamp createdAt = resultSet.getTimestamp("createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
                int status = resultSet.getInt("status");
                fastfood obj = new fastfood(id,name,categoryId,description,thumbnail,price,createdAt,updatedAt,status);
                ListObj.add(obj);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ListObj;

    }

    public fastfood findById(int id)  {
        fastfood obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = String.format("Select * from fastfood where id =%d",id);
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            if (resultSet.next()){
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("categoryId");
                String description = resultSet.getString("description");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                Timestamp createdAt = resultSet.getTimestamp("createdAt");
                Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
                int status = resultSet.getInt("status");
                obj = new fastfood(id,name,categoryId,description,thumbnail,price,createdAt,updatedAt,status);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return obj;
    }
    public boolean update(int id, fastfood updateFood) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "update food set name = ?,categoryId = ?,description = ?,thumbnail = ?,price = ?,createdAt = ?,updatedAt = ?,status = ? where id =?");
            preparedStatement.setString(1,updateFood.getName());
            preparedStatement.setInt(2, updateFood.getCategoryId());
            preparedStatement.setString(3, updateFood.getDescription());
            preparedStatement.setString(4, updateFood.getThumbnail());
            preparedStatement.setDouble(5, updateFood.getPrice());
            preparedStatement.setTimestamp(6, updateFood.getCreatedAt());
            preparedStatement.setTimestamp(7, updateFood.getUpdatedAt());
            preparedStatement.setInt(8, updateFood.getStatus());
            preparedStatement.setInt(9, updateFood.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteVip(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "update food set status=? where id =?");
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public  boolean delete(int id){
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "delete from food where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
