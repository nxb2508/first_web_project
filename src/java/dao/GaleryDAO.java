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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GaleryModel;
import model.ProductModel;

/**
 *
 * @author Bach
 */
public class GaleryDAO extends ConnectDB {
    //lay toan bo san pham

    public List<GaleryModel> getAllGaleries() {
        List<GaleryModel> galeries = new ArrayList<>();
        String sql = "select * from galeries";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                GaleryModel temp = new GaleryModel();
                temp.setId(rs.getInt("id"));
                //lay san pham bang id
                ProductModel product = new ProductDAO().getProductById(rs.getInt("product_id"));
                temp.setProduct(product);
                temp.setThumbnail(rs.getString("thumbnail"));
                galeries.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return galeries;
    }

    //lay toan bo san pham tu start - end
    public List<GaleryModel> getGaleriesByPage(List<GaleryModel> galeries_raw, int start, int end) {
        List<GaleryModel> galeries = new ArrayList<>();
        for (int i = start; i < end; i++) {
            galeries.add(galeries_raw.get(i));
        }
        return galeries;
    }

    //Xoa hinh anh
    public int deleteGaleryById(int id) {
        String sql = "delete galeries where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //Them hinh anh
    public int addGalery(GaleryModel galery) {
        String sql = "insert into galeries (product_id, thumbnail) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, galery.getProduct().getId());
            statement.setString(2, galery.getThumbnail());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //Sua hinh anh 
    public int updateGalery(GaleryModel galery) {
        String sql = "update galeries set product_id = ?, thumbnail = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, galery.getProduct().getId());
            statement.setString(2, galery.getThumbnail());
            statement.setInt(3, galery.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //tim kiem theo ten san pham
    public List<GaleryModel> searchGaleriesByProductName(String product_name) {
        List<GaleryModel> galeries = new ArrayList<>();
        String sql = "select g.id as galery_id, p.id as product_id, g.thumbnail from galeries as g\n"
                + " join products as p\n"
                + " on g.product_id = p.id\n"
                + " WHERE p.name COLLATE Latin1_general_CI_AI like '%" + product_name + "%' COLLATE Latin1_general_CI_AI";
        System.out.println(sql);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                GaleryModel temp = new GaleryModel();
                temp.setId(rs.getInt("galery_id"));
                //lay san pham bang id
                ProductModel product = new ProductDAO().getProductById(rs.getInt("product_id"));
                temp.setProduct(product);
                temp.setThumbnail(rs.getString("thumbnail"));
                galeries.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return galeries;
    }
    
    //lay galeries theo product_id
    public List<GaleryModel> getGaleriesByProductId (ProductModel product){
        List<GaleryModel> galeries = new ArrayList<>();
        String sql = "select * from galeries where product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                GaleryModel category = new GaleryModel();
                category.setId(rs.getInt("id"));
                category.setProduct(product);
                category.setThumbnail(rs.getString("thumbnail"));
                galeries.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return galeries;
    }
    
    public static void main(String[] args) {
        new GaleryDAO().searchGaleriesByProductName("ao");
    }
    
}
