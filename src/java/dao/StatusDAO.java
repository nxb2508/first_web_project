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
import model.StatusModel;

/**
 *
 * @author Bach
 */
public class StatusDAO extends ConnectDB{
    
    // lay toan bo status
    public List<StatusModel> getAllStatuses() {
        List<StatusModel> statuses = new ArrayList<>();
        String sql = "select * from statuses";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StatusModel status = new StatusModel();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
                statuses.add(status);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return statuses;
    }
    
    //lay status bang id
    public StatusModel getStatusById(int statusId) {
        StatusModel status = new StatusModel();
        String sql = "select * from statuses where id = " + statusId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                status.setId(statusId);
                status.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return status;
    }

    // them status
    public int addStatus(String statusName) {
        String sql = "insert into statuses (name) values (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, statusName);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //sua san pham
    public int updateStatus(StatusModel status) {
        String sql = "update statuses set name = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status.getName());
            statement.setInt(2, status.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //xoa san pham
    public int deleteStatus(StatusModel status) {
        String sql = "delete statuses where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //lay ra so status tu start den end
    public List<StatusModel> getStatussByPage(List<StatusModel> statuses, int start, int end){
        List<StatusModel> result = new ArrayList<>();
        for(int i = start; i < end; i++){
            result.add(statuses.get(i));
        }
        return result;
    }
}
