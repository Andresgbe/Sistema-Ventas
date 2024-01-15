/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres Gil
 */
public class ClienteDao {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarCliente(Cliente cl) {
        // Consulta para verificar si la cédula ya existe
        String sqlVerificar = "SELECT COUNT(*) FROM clientes WHERE cedula = ?";
        
        // Consulta para insertar un nuevo cliente
        String sqlInsertar = "INSERT INTO clientes (cedula, nombre, telefono, direccion) VALUES (?,?,?,?)";

        try {
            con = cn.getConnection();

            // Verificar si la cédula ya existe en la base de datos
            try (PreparedStatement psVerificar = con.prepareStatement(sqlVerificar)) {
                psVerificar.setString(1, cl.getCedula());

                // Ejecutar la consulta de verificación
                try (ResultSet rs = psVerificar.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            JOptionPane.showMessageDialog(null, "Error: La cédula ya existe en la base de datos.");
                            return false;
                        }
                    }
                }
            }

            // Insertar el nuevo cliente si la cédula no existe
            try (PreparedStatement psInsertar = con.prepareStatement(sqlInsertar)) {
                psInsertar.setString(1, cl.getCedula());
                psInsertar.setString(2, cl.getNombre());
                psInsertar.setString(3, cl.getTelefono());
                psInsertar.setString(4, cl.getDireccion());

                // Ejecutar la consulta de inserción
                psInsertar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cliente Registrado");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
   
   public List ListarCliente(){
       List<Cliente> ListaCl = new ArrayList();
       String sql = "SELECT * FROM clientes";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Cliente cl = new Cliente();
               cl.setId(rs.getInt("id"));
               cl.setCedula(rs.getString("cedula"));
               cl.setNombre(rs.getString("nombre"));
               cl.setTelefono(rs.getString("telefono"));
               cl.setDireccion(rs.getString("direccion"));
               ListaCl.add(cl);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaCl;
   }
   
   public boolean EliminarCliente(int id){
       String sql = "DELETE FROM clientes WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
   
   public boolean ModificarCliente(Cliente cl){
       String sql = "UPDATE clientes SET cedula=?, nombre=?, telefono=?, direccion=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, cl.getCedula());
           ps.setString(2, cl.getNombre());
           ps.setString(3, cl.getTelefono());
           ps.setString(4, cl.getDireccion());
           ps.setInt(5, cl.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
   
   public Cliente Buscarcliente(int cedula){
       Cliente cl = new Cliente();
       String sql = "SELECT * FROM clientes WHERE cedula = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, cedula);
           rs = ps.executeQuery();
           if (rs.next()) {
               cl.setId(rs.getInt("id"));
               cl.setNombre(rs.getString("nombre"));
               cl.setTelefono(rs.getString("telefono"));
               cl.setDireccion(rs.getString("direccion"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
   
}
