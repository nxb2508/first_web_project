/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetailModel;
import model.OrderModel;

/**
 *
 * @author Bach
 */
public class OrderDAO extends ConnectDB {

    public int addOrder(OrderModel order) {
        int result = 0;
        String sql1 = "insert into orders (user_id, fullname, phone_number, email, address, note, total_money) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            //them thong tin order vao database
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, order.getUser().getId());
            statement1.setString(2, order.getFullname());
            statement1.setString(3, order.getPhoneNumber());
            statement1.setString(4, order.getEmail());
            statement1.setString(5, order.getAddress());
            statement1.setString(6, order.getNote());
            statement1.setInt(7, order.getTotalMoney());
            result = statement1.executeUpdate();
            if (result == 1) {
                String sql2 = "select TOP(1) id from orders order by id desc";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet rs = statement2.executeQuery();
                if (rs.next()) {
                    order.setId(rs.getInt("id"));
                    for (OrderDetailModel orderDetail : order.getOrderDetails()) {
                        orderDetail.setOrder(order);
                        String sql3 = "insert into order_details (order_id, product_id, price, quantity) values (?, ?, ?, ?)";
                        PreparedStatement statement3 = connection.prepareStatement(sql3);
                        statement3.setInt(1, orderDetail.getOrder().getId());
                        statement3.setInt(2, orderDetail.getProduct().getId());
                        statement3.setInt(3, orderDetail.getPrice());
                        statement3.setInt(4, orderDetail.getQuantity());
                        result = statement3.executeUpdate();
                        if (result != 0) {
                            String sql4 = "update products set quantity = ? where id = ?";
                            PreparedStatement statement4 = connection.prepareStatement(sql4);
                            int productQuantity = orderDetail.getProduct().getQuantity();
                            orderDetail.getProduct().setQuantity(productQuantity - orderDetail.getQuantity());
                            statement4.setInt(1, orderDetail.getProduct().getQuantity());
                            statement4.setInt(2, orderDetail.getProduct().getId());
                            result = statement4.executeUpdate();
                            if (result == 0) {
                                connection.rollback();
                                connection.setAutoCommit(true);
                            }
                        } else {
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                    if (result != 0) {
                        connection.commit();
                        connection.setAutoCommit(true);
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

}
