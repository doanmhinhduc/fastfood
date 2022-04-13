package com.example.fastfood.model;

import com.example.fastfood.entity.Category;
import com.example.fastfood.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    public boolean save(Category obj)   {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "insert into Category (categoryName, status) value (?,?)"
                    );
            preparedStatement.setString(1, obj.getCategoryName());
            preparedStatement.setDouble(2, obj.getStatus());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Category> findAll()  {
        List<Category> ListObj = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = "Select * from category where status !=2";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");
                int status = resultSet.getInt("status");
                Category obj = new Category(id,categoryName,status);
                ListObj.add(obj);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ListObj;

    }


    public Category findById(int id)  {
        Category obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = String.format("Select * from category where id =%d",id);
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            if (resultSet.next()){
                String categoryName = resultSet.getString("categoryName");
                int status = resultSet.getInt("status");
                obj = new Category(id,categoryName,status);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return obj;
    }

    public boolean update(int id, Category updateCategory) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "update category set categoryName = ?,status=? where id =?");
            preparedStatement.setString(1, updateCategory.getCategoryName());
            preparedStatement.setInt(2, updateCategory.getStatus());
            preparedStatement.setInt(3, updateCategory.getId());
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
                            "update category set status=? where id =?");
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
                            "delete from category where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
