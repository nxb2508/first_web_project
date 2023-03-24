/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoryModel;
import model.ProductModel;

/**
 *
 * @author Bach
 */
public class ProductDAO extends ConnectDB {

    //Them San Pham
    public int addProduct(ProductModel product) {
        String sql = "insert into products (category_id, name, description, price) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getCategory().getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPrice());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Lay Toan Bo San Pham
    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = new ArrayList<>();
        String sql = "select * from products";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("id"));
                CategoryModel category = new CategoryDAO().getCategoryById(rs.getInt("category_id"));
                product.setCategory(category);
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                products.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return products;
    }

    //Lay san pham tu start den end
    public List<ProductModel> getProductsByPage(List<ProductModel> products, int start, int end) {
        List<ProductModel> temp = new ArrayList<>();
        for (int i = start; i < end; i++) {
            temp.add(products.get(i));
        }
        return temp;
    }

    //Sua san pham
    public int updateProduct(ProductModel product) {
        String sql = "update products set category_id = ? , name = ? , description = ? , price = ?"
                + " where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getCategory().getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPrice());
            statement.setInt(5, product.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //Xoa San Pham
    public int deleteProductById(int id) {
        String sql = "delete products where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //Tim Kiem San Pham Theo Ten
    public List<ProductModel> searchProducts(String description) {
        String sql = "select p.id as p_id, c.id as c_id, p.name as p_name, p.description, p.price, c.name as c_name from products as p "
                + "join categories as c "
                + "on p.category_id = c.id "
                + "WHERE p.name COLLATE Latin1_general_CI_AI like '%" + description + "%' COLLATE Latin1_general_CI_AI "
                + "or c.name COLLATE Latin1_general_CI_AI like '%" + description + "%' COLLATE Latin1_general_CI_AI "
                + "or p.description COLLATE Latin1_general_CI_AI like '%" + description + "%' COLLATE Latin1_general_CI_AI "
                + "or p.price like '%" + description + "%' ";
        List<ProductModel> products = new ArrayList<>();
        System.out.println(sql);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("p_id"));
                product.setCategory(new CategoryDAO().getCategoryById(rs.getInt("c_id")));
                product.setName(rs.getString("p_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                products.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return products;
    }
    
    //tim kiem san pham theo id
    public ProductModel getProductById(int id){
        ProductModel product = new ProductModel();
        String sql = "select * from products where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                CategoryModel category = new CategoryDAO().getCategoryById(rs.getInt("category_id"));
                product.setId(rs.getInt("id"));
                product.setCategory(category);
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }

    public static void main(String[] args) {
        List<ProductModel> products = new ProductDAO().searchProducts("15");
        System.out.println(products.size());
        for (ProductModel product : products) {
            System.out.println(product.getPrice());
        }
    }

}
