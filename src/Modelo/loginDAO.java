/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane; */

public class loginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public login log(String correo, String pass){
        login uno = new login();
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        
        try {
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                uno.setId(rs.getInt("id"));
                uno.setNombre(rs.getString("nombre"));
                uno.setCorreo(rs.getString("correo"));
                uno.setPass(rs.getString("pass"));
            }
            
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return uno;
    }
}
