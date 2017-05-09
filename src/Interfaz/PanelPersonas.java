/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jeyson Duarte
 */

/*
Clase que muestra el boton el la ventana principal
*/
public class PanelPersonas extends JPanel implements ActionListener {
    
    JButton BVista;// boton que activa la vuista
    private DialogoVerPersona dialog;// etiqtea que muestra el dialogo
    private VentanaPrincipal ventana;// etiquera para añadir al panel princuapl
    
    
    /*
    contructo de la clase
    */
    public PanelPersonas(VentanaPrincipal ventana) {
         this.ventana = ventana;// etiqueta para mostraren la evntana princupal
         setLayout(null);// se actualiza el panel
         
         BVista = new JButton("Ver Personas");// nombre del boton
         BVista.setFont(new Font("Serif", Font.BOLD, 18));// letra y tamaño del boton
         BVista.addActionListener(this);// activacion del boton
         BVista.setBounds(130, 10, 150, 20);// unicacion y tamaño del boton
         add(BVista);// se añade el boton al panel
         
       
       
         
         dialog = new DialogoVerPersona(this.ventana);// se llama la calse dialogo para la activacion del boton
         
    }
    
    /*
    activa el boton de ver personas y muestra el dialogo de la tabla
    */
   @Override
    public void actionPerformed(ActionEvent e) {
        dialog.actualizarTabla();// primero actualiza la lista 
        dialog.setVisible(true);// luego hace visible la ventana del dialogo
        
    }
    
    
}
