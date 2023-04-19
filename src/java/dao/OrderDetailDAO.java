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
import model.OrderDetailModel;
import model.OrderModel;

/**
 *
 * @author Bach
 */
public class OrderDetailDAO extends ConnectDB{
    
    //them chi tiet hoa don
    public int addOrderDetail(OrderDetailModel orderDetail){
        String sql = "insert into order_details (order_id, inventory_id, price, quantity) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderDetail.getOrder().getId());
            statement.setInt(2, orderDetail.getInventory().getId());
            statement.setInt(3, orderDetail.getPrice());
            statement.setInt(4, orderDetail.getQuantity());
            return statement.executeUpdate();
        } catch (SQLException e) {
            
        }
        return 0;
    }
    
    //lay danh sach san phan trong mot hoa don
    public List<OrderDetailModel> getOrderDetailsByOrderId(OrderModel order){
        List<OrderDetailModel> orderDetails = new ArrayList<>();
        String sql = "select * from order_details where order_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                OrderDetailModel orderDetail = new OrderDetailModel();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrder(order);
                orderDetail.setInventory(new InventoryDAO().getInventoryById(rs.getInt("inventory_id")));
                orderDetail.setPrice(rs.getInt("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
        }
        return orderDetails;
    }
    
    public static void main(String[] args) {
        OrderModel order = new OrderDAO().getOrderById(1);
        List<OrderDetailModel> orderDetails = new OrderDetailDAO().getOrderDetailsByOrderId(order);
        System.out.println(orderDetails.get(1).getInventory().getProduct().getName());
    }
    
}
