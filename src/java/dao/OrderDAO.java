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
import model.UserModel;

/**
 *
 * @author Bach
 */
public class OrderDAO extends ConnectDB {

    public int addOrder(OrderModel order) {
        int result = 0;
        String sql1 = "insert into orders"
                    + "(user_id, fullname, phone_number, address, note, total_money)"
                    + " values (?, ?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            //them thong tin order vao database
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, order.getUser().getId());
            statement1.setString(2, order.getFullname());
            statement1.setString(3, order.getPhoneNumber());
            statement1.setString(4, order.getAddress());
            statement1.setString(5, order.getNote());
            statement1.setInt(6, order.getTotalMoney());
            result = statement1.executeUpdate();
            if (result == 1) {
                String sql2 = "select TOP(1) id from orders order by id desc";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet rs = statement2.executeQuery();
                if (rs.next()) {
                    order.setId(rs.getInt("id"));
                    for (OrderDetailModel orderDetail : order.getOrderDetails()) {
                        orderDetail.setOrder(order);
                        String sql3 = "insert into order_details (order_id, inventory_id, price, quantity) values (?, ?, ?, ?)";
                        PreparedStatement statement3 = connection.prepareStatement(sql3);
                        statement3.setInt(1, orderDetail.getOrder().getId());
                        statement3.setInt(2, orderDetail.getInventory().getId());
                        statement3.setInt(3, orderDetail.getPrice());
                        statement3.setInt(4, orderDetail.getQuantity());
                        result = statement3.executeUpdate();
                        if (result != 0) {
                            String sql4 = "update inventories set quantity = ? where id = ?";
                            PreparedStatement statement4 = connection.prepareStatement(sql4);
                            int inventoryQuantity = orderDetail.getInventory().getQuantity();
                            orderDetail.getInventory().setQuantity(inventoryQuantity - orderDetail.getQuantity());
                            statement4.setInt(1, orderDetail.getInventory().getQuantity());
                            statement4.setInt(2, orderDetail.getInventory().getId());
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

    public OrderModel getOrderById(int orderId){
        OrderModel order = new OrderModel();
        String sql = "select * from orders where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                order.setId(rs.getInt("id"));
                order.setUser(new UserDAO().getUserById(rs.getInt("user_id")));
                order.setFullname(rs.getString("fullname"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setAddress(rs.getString("address"));
                order.setNote(rs.getString("note"));
                order.setStatus(new StatusDAO().getStatusById(rs.getInt("status_id")));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalMoney(rs.getInt("total_money"));
                List<OrderDetailModel> listOrderDetails = new OrderDetailDAO().getOrderDetailsByOrderId(rs.getInt("id"));
//                for(OrderDetailModel orderDetail : listOrderDetails){
//                    orderDetail.setOrder(order);
//                }
                order.setOrderDetails(listOrderDetails);
            }
        } catch (SQLException e) {
        }
        return order;
    }
    
    
    //lay toan boo order cua user
    public List<OrderModel> listOrderByUser(UserModel user){
        List<OrderModel> listOrder = new ArrayList<>();
        String sql = "select * from orders where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                OrderModel order = new OrderModel();
                order.setId(rs.getInt("id"));
                order.setUser(new UserDAO().getUserById(rs.getInt("user_id")));
                order.setFullname(rs.getString("fullname"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setAddress(rs.getString("address"));
                order.setNote(rs.getString("note"));
                order.setStatus(new StatusDAO().getStatusById(rs.getInt("status_id")));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalMoney(rs.getInt("total_money"));
                List<OrderDetailModel> listOrderDetails = new OrderDetailDAO().getOrderDetailsByOrderId(rs.getInt("id"));
                order.setOrderDetails(listOrderDetails);
                listOrder.add(order);
            }
        } catch (SQLException e) {
        }
        return listOrder;
    }
    
    //lay toan bo san pham
    public List<OrderModel> getAllOrder(){
        List<OrderModel> listOrder = new ArrayList<>();
        String sql = "select * from orders";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                OrderModel order = new OrderModel();
                order.setId(rs.getInt("id"));
                order.setUser(new UserDAO().getUserById(rs.getInt("user_id")));
                order.setFullname(rs.getString("fullname"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setAddress(rs.getString("address"));
                order.setNote(rs.getString("note"));
                order.setStatus(new StatusDAO().getStatusById(rs.getInt("status_id")));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalMoney(rs.getInt("total_money"));
                List<OrderDetailModel> listOrderDetails = new OrderDetailDAO().getOrderDetailsByOrderId(rs.getInt("id"));
                order.setOrderDetails(listOrderDetails);
                listOrder.add(order);
            }
        } catch (SQLException e) {
        }
        return listOrder;
    }
    
    //tim kiem theo nhieu tieu chi
    public List<OrderModel> searchOrderByKey(String key){
        List<OrderModel> listOrder = new ArrayList<>();
        String sql = "select o.id as id, o.user_id as user_id, "
                + " o.fullname as fullname, o.phone_number as phone_number,"
                + " o.address as address, o.note as note, o.status_id as status_id, o.order_date as order_date,"
                + " o.total_money as total_money "
                + " from orders as o"
                + " join statuses as s"
                + " on o.status_id = s.id"
                + " where o.fullname COLLATE Latin1_general_CI_AI like '%"+ key +"%' COLLATE Latin1_general_CI_AI "
                + " or o.phone_number COLLATE Latin1_general_CI_AI like '%"+ key +"%' COLLATE Latin1_general_CI_AI "
                + " or s.name COLLATE Latin1_general_CI_AI like '%"+ key +"%' COLLATE Latin1_general_CI_AI ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                OrderModel order = new OrderModel();
                order.setId(rs.getInt("id"));
                order.setUser(new UserDAO().getUserById(rs.getInt("user_id")));
                order.setFullname(rs.getString("fullname"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setAddress(rs.getString("address"));
                order.setNote(rs.getString("note"));
                order.setStatus(new StatusDAO().getStatusById(rs.getInt("status_id")));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalMoney(rs.getInt("total_money"));
                List<OrderDetailModel> listOrderDetails = new OrderDetailDAO().getOrderDetailsByOrderId(rs.getInt("id"));
                order.setOrderDetails(listOrderDetails);
                listOrder.add(order);
            }
        } catch (SQLException e) {
        }
        return listOrder;
    }
    
    //phan trang
    public List<OrderModel> getOrdersByPage(List<OrderModel> listOrderRaw, int start, int end){
        List<OrderModel> listOrder = new ArrayList<>();
        for(int i = start; i < end; i++){
            listOrder.add(listOrderRaw.get(i));
        }
        return listOrder;
    }
    
    //huy dono hang
    
    public int cancelOrderByOrderId(int orderId){
        int result = 0;
        String sql = "update orders set status_id = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 4);
            statement.setInt(2, orderId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }
    
        //cap nhat trang thai don hang
    
    public int updateOrderStatus(int orderId, int statusId){
        int result = 0;
        String sql = "update orders set status_id = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, statusId);
            statement.setInt(2, orderId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }
    
    public static void main(String[] args) {
        OrderModel order = new OrderDAO().getOrderById(1);
        System.out.println(order.getFullname());
//        UserModel user = new UserDAO().getUserById(2);
//        System.out.println(user.getEmail());
//        System.out.println(new  OrderDAO().listOrderByUserId(2).size());
    }
    
}
