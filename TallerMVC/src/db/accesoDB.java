/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

/**
 *
 * @author juanf
 */
public class accesoDB {
    
    Connection conexion;
    
    public void abrirConexion(){
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Interactiva","postgres","univalle");
        } catch (SQLException ex) {
            Logger.getLogger(accesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(accesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarProducto(Producto producto){
        abrirConexion();
        try {
            Statement stmt = conexion.createStatement();
            String consulta = "INSERT INTO public.\"Producto\"(\n" +
"	\"Nombre\", \"Id\", \"Cantidad\", \"Precio\")\n" +
"	VALUES ('"+producto.getNombre()+"', '"+producto.getId()+"', '"+producto.getCantidad()+"', '"+producto.getPrecio()+"');";
            stmt.executeUpdate(consulta);            
        } 
        catch (SQLException ex) {
            Logger.getLogger(accesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion();
    }
    
    public Producto consultarProducto(String id){
        abrirConexion();
        Producto busqueda = null;
        try {
            Statement stmt = conexion.createStatement();
            String consulta = "SELECT \"Nombre\", \"Id\", \"Cantidad\", \"Precio\"\n" +
"	FROM public.\"Producto\" WHERE \"Id\" = '"+id+"';";
             ResultSet resultado = stmt.executeQuery(consulta);
             if(resultado.next()){
                 
                 busqueda.setNombre(resultado.getString("Nombre"));
                 busqueda.setId(resultado.getString("Id"));
                 busqueda.setCantidad(Integer.parseInt(resultado.getString("Cantidad")));
                 busqueda.setPrecio(Double.parseDouble(resultado.getString("Precio")));
             }
        } 
        catch (SQLException ex) {
            Logger.getLogger(accesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion();
        return busqueda;
    }
    
    public void modificarProducto(Producto producto){
     abrirConexion();
        try {
            Statement stmt = conexion.createStatement();
            String consulta = "UPDATE public.\"Producto\"\n" +
"	SET \"Nombre\"='"+producto.getNombre()+"', \"Id\"='"+producto.getId()+"', \"Cantidad\"='"+producto.getCantidad()+"', \"Precio\"='"+producto.getPrecio()+"'\n" +
"	WHERE \"Id\" = '"+producto.getId()+"';";
            stmt.executeUpdate(consulta);            
        } 
        catch (SQLException ex) {
            Logger.getLogger(accesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion();   
    }
    
    public void eliminarProducto(String id){
        try {
            Statement stmt = conexion.createStatement();
            String consulta = "DELETE FROM public.\"Producto\"\n" +
"	WHERE \"Id\" = '"+id+"';";
            stmt.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(accesoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
