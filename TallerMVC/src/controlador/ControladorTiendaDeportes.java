package controlador;

import db.accesoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Producto;
import vista.TiendaDeportiva;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juanf
 */
public class ControladorTiendaDeportes implements ActionListener{
    
    private TiendaDeportiva gui;
    private Producto modelo;
    private accesoDB db;

    public ControladorTiendaDeportes(TiendaDeportiva gui, Producto modelo) {
        this.gui = gui;
        this.modelo = modelo;
        this.gui.consultarBoton.addActionListener(this);
        this.gui.crearBoton.addActionListener(this);
        this.gui.eliminarBoton.addActionListener(this);
        this.gui.modificarBoton.addActionListener(this);
        db = new accesoDB();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.gui.consultarBoton){
            Producto temp = db.consultarProducto(this.gui.idText.getText());
        }else if(ae.getSource() == this.gui.crearBoton){
            
        }else if(ae.getSource() == this.gui.eliminarBoton){
            
        }else if(ae.getSource() == this.gui.modificarBoton){
            
        }
    }
    
    
}
