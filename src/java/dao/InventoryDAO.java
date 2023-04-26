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
import model.InventoryModel;

/**
 *
 * @author Bach
 */
public class InventoryDAO extends ConnectDB {

    //lay so luong san pham trong kho ra
    public List<InventoryModel> getAllInventories() {
        List<InventoryModel> inventories = new ArrayList<>();
        String sql = "select * from inventories";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                InventoryModel inventory = new InventoryModel();
                inventory.setId(rs.getInt("id"));
                inventory.setProduct(new ProductDAO().getProductById(rs.getInt("product_id")));
                inventory.setSize(new SizeDAO().getSizeById(rs.getInt("size_id")));
                inventory.setQuantity(rs.getInt("quantity"));
                inventories.add(inventory);
            }
        } catch (SQLException e) {
        }
        return inventories;
    }

    //lay inventory tu start den end
    public List<InventoryModel> getInventoryByPage(List<InventoryModel> inventories, int start, int end) {
        List<InventoryModel> inventoriesByPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            inventoriesByPage.add(inventories.get(i));
        }
        return inventoriesByPage;
    }

    //them so luong san pham vao trong kho
    public int addInventory(InventoryModel inventory) {
        String sql = "insert into inventories (product_id, size_id, quantity) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inventory.getProduct().getId());
            statement.setInt(2, inventory.getSize().getId());
            statement.setInt(3, inventory.getQuantity());
            return statement.executeUpdate();
        } catch (SQLException ex) {
        }
        return 0;
    }

    //tim kiem san pham trong kho
    public InventoryModel getInventoryById(int inventoryId) {
        InventoryModel inventory = new InventoryModel();
        String sql = "select * from inventories where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inventoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                inventory.setId(inventoryId);
                inventory.setProduct(new ProductDAO().getProductById(rs.getInt("product_id")));
                inventory.setSize(new SizeDAO().getSizeById(rs.getInt("size_id")));
                inventory.setQuantity(rs.getInt("quantity"));
                return inventory;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventory;
    }

    //tim san pham trong kho theo ten
    public List<InventoryModel> searchInventoryByName(String productName) {
        List<InventoryModel> inventories = new ArrayList<>();
        String sql = "select i.id as id, i.product_id as product_id, i.size_id as size_id, i.quantity as quantity\n"
                + "from inventories as i\n"
                + "join products as p\n"
                + "on i.product_id = p.id\n"
                + "where p.name COLLATE Latin1_general_CI_AI like '%" + productName +"%' COLLATE Latin1_general_CI_AI";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                InventoryModel inventory = new InventoryModel();
                inventory.setId(rs.getInt("id"));
                inventory.setProduct(new ProductDAO().getProductById(rs.getInt("product_id")));
                inventory.setSize(new SizeDAO().getSizeById(rs.getInt("size_id")));
                inventory.setQuantity(rs.getInt("quantity"));
                inventories.add(inventory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventories;
    }

    //xoa san pham trong kho
    public int deleteInventory(int inventoryId) {
        String sql = "delete inventories where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inventoryId);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //cap nhat so luong san pham
    public int updateInventory(InventoryModel inventory){
        String sql = "update inventories set quantity = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inventory.getQuantity());
            statement.setInt(2, inventory.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public InventoryModel getInventoryByProductIdAndSizeId(int productId, int sizeId){
        InventoryModel inventory = new InventoryModel();
        String sql = "select * from inventories where product_id = ? and size_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, sizeId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                inventory.setId(rs.getInt("id"));
                inventory.setProduct(new ProductDAO().getProductById(rs.getInt("product_id")));
                inventory.setSize(new SizeDAO().getSizeById(rs.getInt("size_id")));
                inventory.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventory;
    }
    
    public static void main(String[] args) {
        InventoryModel inventory = new InventoryDAO().getInventoryByProductIdAndSizeId(1, 2);
        List<InventoryModel> inventories = new InventoryDAO().searchInventoryByName("ao");
        System.out.println(inventory.getId());
    }

}
