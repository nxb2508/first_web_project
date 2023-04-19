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
import model.SizeModel;

/**
 *
 * @author Bach
 */
public class SizeDAO extends ConnectDB{
    
    // lay toan bo size
    public List<SizeModel> getAllSizes() {
        List<SizeModel> sizes = new ArrayList<>();
        String sql = "select * from sizes";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SizeModel size = new SizeModel();
                size.setId(rs.getInt("id"));
                size.setName(rs.getString("name"));
                sizes.add(size);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sizes;
    }
    
    //lay size bang id
    public SizeModel getSizeById(int sizeId) {
        SizeModel size = new SizeModel();
        String sql = "select * from sizes where id = " + sizeId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                size.setId(sizeId);
                size.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return size;
    }

    // them size
    public int addSize(String sizeName) {
        String sql = "insert into sizes (name) values (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, sizeName);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //sua san pham
    public int updateSize(SizeModel size) {
        String sql = "update sizes set name = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, size.getName());
            statement.setInt(2, size.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //xoa san pham
    public int deleteSize(SizeModel size) {
        String sql = "delete sizes where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, size.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    //lay ra so size tu start den end
    public List<SizeModel> getSizesByPage(List<SizeModel> sizes, int start, int end){
        List<SizeModel> result = new ArrayList<>();
        for(int i = start; i < end; i++){
            result.add(sizes.get(i));
        }
        return result;
    }
    
}
