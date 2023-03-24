/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CategoryModel;

/**
 *
 * @author Bach
 */
public class CategoryDAO extends ConnectDB {

    // lay toan bo category
    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        String sql = "select * from categories";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categories.add(new CategoryModel(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }
    
    //lay loai san pham bang id
    public CategoryModel getCategoryById(int category_id) {
        CategoryModel category = new CategoryModel();
        String sql = "select * from categories where id = " + category_id;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                category.setId(category_id);
                category.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return category;
    }

    // them san pham
    public int addCategory(String categoryName) {
        String sql = "insert into categories (name) values (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //sua san pham
    public int updateCategory(CategoryModel category) {
        String sql = "update categories set name = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //xoa san pham
    public int deleteCategory(CategoryModel category) {
        String sql = "delete categories where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //tim kiem san pham
    public List<CategoryModel> searchCategoriesByName(String category_name) {
        List<CategoryModel> categories = new ArrayList<>();
        String sql = "SELECT * \n"
                    + "FROM categories\n"
                    + "WHERE name COLLATE Latin1_general_CI_AI like '%"+ category_name +"%' COLLATE Latin1_general_CI_AI";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categories.add(new CategoryModel(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }

    //lay ra so category tu start den end
    public List<CategoryModel> getCategoriesByPage(List<CategoryModel> categories, int start, int end){
        List<CategoryModel> result = new ArrayList<>();
        for(int i = start; i < end; i++){
            result.add(categories.get(i));
        }
        return result;
    }
    
    
    public static void main(String[] args) {
    }

}
