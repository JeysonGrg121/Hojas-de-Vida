/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.Aspirante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jeyson Duarte
 */

/*
Clase Que muestra la tabla en un Dialogo
*/
public class DialogoVerPersona extends JDialog implements ActionListener {
    private static final String OBTENER = "GUARDAR";// etiquetra para el boton guardar los datos en el archivo
    JPanel panel;// panel principal
    JTable tablaPersonas;// nombre de la tabla
    JButton Guardar;//boton guardar
    private DefaultTableModel dtm;// default de la tabla
    private VentanaPrincipal ventana;// la implementetacion a la ventana
    private Aspirante persona;// variable que llama a la calse aspirante en el paquete de la logica
    
    /*
    contructor de la clase Dialogo
    */
    public DialogoVerPersona(VentanaPrincipal ventana) {
        
        this.ventana = ventana; // muestra en la ventana
        setSize(1200, 400);// tamaño del dialogo
        setTitle("Dialogo Ver Personas");// nombre de la ventana dialogo
        
        
        
        Guardar = new JButton("GUARDAR");// creacion del codigo
        Guardar.setActionCommand(OBTENER);// etiqueta del boton
        Guardar.addActionListener(this);//escucha el boton
        add(Guardar, BorderLayout.PAGE_END);//ubicacion del boton
        
        panel = new JPanel();//panel donde se agregara la tabla
        panel.setBorder(BorderFactory.createTitledBorder("ASPIRANTES"));// nombre del panel
        panel.setBackground(Color.WHITE);// colode de fondo del panel
        panel.setLayout(null);// actualizacion del panel
        add(panel);// se añadade el apnel
        
        dtm = new DefaultTableModel();// default de la tabla
        tablaPersonas = new JTable(dtm);// creacion de la tabla
        
        Object[] columna = {"Foto", "Nombre", "Apellido"," Cedula", " Correo", "Profesion","Fecha De Nacimiento", "Edad"};// nombre de cada celda de la tabla
        for (Object columna1 : columna) {//for qe recorre la lista del los nombre de la tabla para crera los espacios y las divisones
            dtm.addColumn(columna1);// se añade cada espacio a la tabla
        }    
    
        JScrollPane scrollPane = new JScrollPane(tablaPersonas);// se crea la tabla en el panel
        scrollPane.setBounds(30, 30, 1000, 100);// tamañno de la tabla
        panel.add(scrollPane);// se añade al panel

        
    }
/*
    metodo que actualiza la tabla, primero borra los datos para que la tabla quede vacia y luego añade los campos agregados seguns se enviaron en el panel aspirantes
    */
    public void actualizarTabla() {
        
        DefaultTableModel modelo = (DefaultTableModel) tablaPersonas.getModel();
        int filas = tablaPersonas.getRowCount();
        for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        List<Aspirante> lista = this.ventana.getAspirantes().getListaArtista();
         for (Aspirante artista : lista) {
             Object[] fila = {artista.getImagen(), artista.getNombre(), artista.getApellido(),artista.getCedula(),artista.getCorreo(), artista.getProfesion().toString(),artista.getFechaDeNacimiento(),artista.getEdad()};
                dtm.addRow(fila);
             
        }
        
         
    }
    /*
    metodo que crea el arcgivo y guarda los datos en un .txt
    */
    private void guardaTabla(){
        try {

            String sucursalesCSVFile = "src/Archivos/DatosArchivo.txt";
            BufferedWriter bfw = new BufferedWriter(new FileWriter(sucursalesCSVFile ));
           
            
            for (int i = 0 ; i < tablaPersonas.getRowCount(); i++) //realiza un barrido por filas.
            {
                for(int j = 0 ; j < tablaPersonas.getColumnCount();j++) //realiza un barrido por columnas.
                {
                    bfw.write(tablaPersonas.getValueAt(i,j).toString());
                    if (j < tablaPersonas.getColumnCount() -1) { //agrega separador "," si no es el ultimo elemento de la fila.
                        bfw.write(",");
                    }
                }
                bfw.newLine(); //inserta nueva linea.
            }

            bfw.close(); //cierra archivo!
            JOptionPane.showMessageDialog(null,"EL ARCHIVO SE HA GUARDADO  EN  [src/Archivos/DatosArchivo] ", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR: OCURRIO UN PROBLEMA AL SALVAR EL ARCHIVO!" + e.getMessage());
        }
    }
    
   
    

    /*
    metodo que escucha el boton y lo ejecuta, de acuerdo a la activacion del boton se manda al metodo guadar tabla
    */
    @Override
    public void actionPerformed (final ActionEvent d) {
        if(d.getActionCommand().equals(OBTENER)){
            guardaTabla();

            }
       }

}