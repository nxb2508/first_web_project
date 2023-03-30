/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Bach
 */
public class UserModel {

    private int id;
    private RoleModel role;
    private String fullname, phoneNumber, email, password;
    private List<FeedbackModel> feedbacks;
    private List<OrderModel> orders;
    
    public UserModel() {
    }

    public UserModel(int id, RoleModel role, String fullname, String phoneNumber, String email, String password, List<FeedbackModel> feedbacks, List<OrderModel> orders) {
        this.id = id;
        this.role = role;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.feedbacks = feedbacks;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FeedbackModel> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

    
    
    
}
