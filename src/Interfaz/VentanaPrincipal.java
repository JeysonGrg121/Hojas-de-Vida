/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */

/*
Clase ventana principal, aqui en esta calse se mostrara todo el programa de la interfaz al usuario final
*/
public class VentanaPrincipal extends JFrame  implements ActionListener{
    private DialogoVerPersona dialog;// variable que llama al dialogo
    private PanelAspirantes panelAspirantes;//variable que llama al panel para registrar
    private PanelPersonas panel2;// etiqueta del panel que llama al boton
    private JMenuBar menuBar;// menus de las ventana en la parte superiro
    private JMenu MFile;//almacenos los dos campos del historial
    private JMenu MEdit;// menus al lado del histrial
    private JMenuItem MNewProject;// menu delnombre de los aspirantes
    private JMenuItem MNewFile;
    private JMenuItem MNexit;// menu de salida
    private JMenuItem MUndo;
    
    private static final String PERSONAS_REGISTRADAS = "LISTA ASPIRANTES";//etiqueta del menu para mostrara los oingresados
    private static final String CREADORES = "CREADORES";// etiqueta para el menu de crear
    private static final String EXIT = "EXIT";// etiqueta para salir
    
    /*
    contructor de la ventana principal
    */
    public VentanaPrincipal() {
        setSize(720, 400);// tamaño de la ventana
        setLocationRelativeTo(null);// ubicacion en el centro de la pantalla
        setTitle("Registro Hojas de Vida");// titulo de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// boton de cerrrar
        setResizable(false);//bloquea el boton de agrandar la ventana
        setLayout(null);// actualiza el panel
        
        panelAspirantes = new PanelAspirantes(this);// llama al panel aspirantes
        panelAspirantes.setBounds(0, 0, 700, 300);// ubicacion y tamaño del panel en la ventana
        add(panelAspirantes);// se añade al panel
       
        
        
        panel2 = new PanelPersonas(this);// panel del v¿boton ver personas
        panel2.setBounds(150, 300, 430, 50);// tamaño y ubicaion del panel
        add(panel2);// se añade a la ventana
         
        menuBar = new JMenuBar();        // creacion del menu de barra
        setJMenuBar(menuBar);// se añaade al panel
        
        
        MFile = new JMenu("HISTORIAL");// etiqeta del menu
        
        MNewProject = new JMenuItem("ASPIRANTES REGISTRADOS");// nombre que los aspirantes
        MNewProject.setActionCommand(PERSONAS_REGISTRADAS);// etiqueta del menu que es ver personas 
        MNewProject.addActionListener(this);// activacion del menu al hacer click
        dialog = new DialogoVerPersona(this);// se crea para llamar al dialigo

        MNexit = new JMenuItem("Exit");// nombre de la etiqueta para salir
        MNexit.setActionCommand(EXIT);// etiqueta del boton
        MNexit.addActionListener(this);// activacion del menu 
        
        MFile.add(MNewProject);// se añadade los dos anteriores al uno solo
        MFile.add(MNexit);// se añade a uno solo
        menuBar.add(MFile);// se añade al menu de  barra
        
        
        MEdit = new JMenu("INFO");// mueu de la infromacion
        
        MUndo = new JMenuItem("informacion");// nombre del mueno de imformacion
        MUndo.setActionCommand(CREADORES);
        MUndo.addActionListener(this);// activacion del menu
        MEdit.add(MUndo);// se añade al otro menu barr
        menuBar.add(MEdit);// se añadade al menu barra general
        
        setVisible(true);// se hace visible la ventana
        
    }
    /*
    metodo del panel para poder activar el boton desde la ventana
    */
    public PanelAspirantes getAspirantes() {
        return panelAspirantes;
    }
 /*
    metodo que escucha y activa cada obcion elegida dentro del panel
    */
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(PERSONAS_REGISTRADAS)) {// se elegue ver Personas, muestra el dialogo en una ventana auxiliar
            dialog.actualizarTabla();// llama el metodo de actualiza tabla para que muestre simepre lso datos ingresados
            dialog.setVisible(true);// hace visible la ventana auxiliar
        } else if(e.getActionCommand().equals(CREADORES)) {// muestra los datos del creador del programa al tener la opcion del boton
               JOptionPane.showMessageDialog(this, "AUTOR: \n Jeyson Duarte \n Universidad De Cundinamarca \n Programacion II");// se muestra la informacion en el Joption
        }else if(e.getActionCommand().equals(EXIT)) {// escucha el boton para salir
            System.exit(0); //sale del programa
        }
    }
}
