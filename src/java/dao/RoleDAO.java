/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.RoleModel;

/**
 *
 * @author Bach
 */
public class RoleDAO extends ConnectDB{
    //lay role theo id
    public RoleModel getRoleById(int role_id){
        RoleModel role = null;
        String sql = "select * from roles where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, role_id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                role = new RoleModel();
                role.setId(role_id);
                role.setName(rs.getString("name"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return role;
    }
    
    public static void main(String[] args) {
        System.out.println(new RoleDAO().getRoleById(1) == null);
    }
}
