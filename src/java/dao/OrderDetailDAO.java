/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.OrderDetailModel;

/**
 *
 * @author Bach
 */
public class OrderDetailDAO extends ConnectDB{
    
    public int addOrderDetail(OrderDetailModel orderDetail){
        String sql = "insert into order_details (order_id, product_id, price, quantity) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderDetail.getOrder().getId());
            statement.setInt(2, orderDetail.getProduct().getId());
            statement.setInt(3, orderDetail.getPrice());
            statement.setInt(4, orderDetail.getQuantity());
            return statement.executeUpdate();
        } catch (SQLException e) {
            
        }
        return 0;
    }
    
}
