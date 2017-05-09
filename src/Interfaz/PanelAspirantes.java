/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.Aspirante;
import Logica.Profesion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Carlos
 */
public class PanelAspirantes extends JPanel implements ActionListener, ItemListener{
    
   /*
    variables necesarias para los datos del panel
    */
    JLabel LNombre, LApellido, LCedula, LImagen, LCorreo, LProfesion, LMImagen, LGenero, LFechaNacimiento;
    JTextField JTNombre, JTApellido, JTCedula, JTImagen, JTCorreo;
    JComboBox CMProfesion;
    JButton BGuardar, BBuscar, BValidar;
    JCheckBox CBHombre, CBMujer;
    ButtonGroup CBGrpoGenero; 
    
   
    private Aspirante persona;
    private List<Aspirante> listaArtista;
    private Profesion profesion;
    private VentanaPrincipal ventana;
    private String Correo;
    private ImageIcon imagenVacia;
    private DialogoVerPersona dialog;
    JXDatePicker calendario;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    
    /*
    contructor de la clase del panel
    */
    public PanelAspirantes(VentanaPrincipal ventana){
        this.ventana = ventana;
        setBorder(BorderFactory.createTitledBorder("REGISTRAR PERSONA"));
        listaArtista = new ArrayList<Aspirante>();
        setLayout(null);
          
        /*
        todos lo JLable con sus nombres
        */
        LImagen = new JLabel ("FOTO:");
        LNombre = new JLabel ("Nombre:");
        LApellido = new JLabel ("Apellido:");
        LCedula = new JLabel ("Cedula: ");
        LCorreo =  new JLabel ("Correo:");
        LProfesion = new JLabel ("Profesion:");
        LGenero = new JLabel ("Genero: ");
        LFechaNacimiento = new JLabel("Fecha Nacimiento: ");
        /*
       check box del genero
        */
        CBGrpoGenero =  new ButtonGroup();
        
        CBHombre = new JCheckBox("Hombre");
        CBHombre.setBounds(140, 174, 100, 20);
        CBHombre.addItemListener(this);
        CBMujer = new JCheckBox("Mujer");
        CBMujer.setBounds(240, 174, 100, 20);
        CBMujer.addItemListener(this);
        
        CBGrpoGenero.add(CBHombre);
        CBGrpoGenero.add(CBMujer);
        
        add(CBHombre);
        add(CBMujer);
        /*
        los JLabel en su ubicacion
        */
        LImagen.setBounds(30, 30, 100, 20);
        add(LImagen);  
        LNombre.setBounds(30, 54, 100, 20);
        add(LNombre);
        LApellido.setBounds(30, 78, 100, 20);
        add(LApellido);
        LCedula.setBounds(30, 102, 100, 20);
        add(LCedula);
        LCorreo.setBounds(30, 126, 100, 20);
        add(LCorreo);
        LProfesion.setBounds(30, 150, 100, 20);
        add(LProfesion);
        LGenero.setBounds(30, 174, 100, 20);
        add(LGenero);
        LFechaNacimiento.setBounds(30, 200, 150, 20);
        add(LFechaNacimiento);
        /*
        los JTxfile con su ubicacion
        */
        JTImagen = new JTextField();
        JTImagen.setBounds(140, 30, 150, 20);
        JTImagen.setFont( LImagen.getFont( ).deriveFont( Font.PLAIN ) );
        add(JTImagen);
        JTNombre = new JTextField();
        JTNombre.setBounds(140, 54, 150, 20);
        add(JTNombre);
        JTApellido = new JTextField();
        JTApellido.setBounds(140, 78, 150, 20);
        add(JTApellido);
        JTCedula = new JTextField();
        JTCedula.setBounds(140, 102, 150, 20);
        add(JTCedula);
        JTCorreo = new JTextField();
        JTCorreo.setBounds(140, 126, 150, 20);
        add(JTCorreo);
        
        /*
        combox de la profesion con su ubicacion
        */
        CMProfesion = new JComboBox(profesion.values());
        CMProfesion.setBounds(140, 150, 150, 20);
        add(CMProfesion);
        
        /*
        botones dentro del panel y su ubicacion
        */
        BBuscar = new JButton("Buscar Foto");
        BBuscar.setBounds(350, 200, 250, 20);
        BBuscar.addActionListener(this);
        add(BBuscar);
        
        BGuardar = new JButton("Crear");
        BGuardar.setBounds(140, 226, 130, 20);
        BGuardar.addActionListener(this);
        add(BGuardar);
        
        /*
        imagen por defecto
        */
        ImageIcon icono = new ImageIcon("./data/imagenes/fotodefecto.JPG");
        
        LMImagen = new JLabel( icono);
        LMImagen.setBorder( new LineBorder( Color.BLACK, 1 ) );
        LMImagen.setBounds(350, 30, 250, 150);
        add(LMImagen);
        /*
        muestra el calendario con su ubicacion
        */
        calendario = new JXDatePicker();
        calendario.setDate(new Date());
        calendario.setFormats(formatter);
        calendario.setBounds(140, 200 , 150, 20);
        add(calendario);
        
       

       
        
    }
 
    /*
    valida el correo electronico, y devueleve un boolena
    */
    private boolean validarDatosCorreo(String Correo) {
        Pattern pat = null;
        Matcher mat = null;
        
        pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");// aquie es la validacion del correp con los caracteres que puede tener el correo
        mat = pat.matcher(Correo);
        
        if(mat.find()){
            return true;
        }else {
            return false;
        }
        
    }
   /*
    convierte la gecha a un string para envialas a la tabla
    */
    public String convertirFecha(){ 
       Date Fecha = calendario.getDate();
       String fechaS = formatter.format(Fecha);
       return fechaS;
    }
  /*
    metodoq ue obtine la edad para enviala a la lista
    */
    public int obtenerEdad(){
        Date FechaN = calendario.getDate();
        Date FechaA = new Date();
        
        int años = FechaA.getYear() - FechaN.getYear();
        int mes = FechaA.getMonth() - FechaN.getMonth();
        int dia = FechaA.getDay() - FechaN.getDay();
        
        if(mes < 0 || mes == 0 && dia <0){
            años =  años - 1;
        }
        return años;
    }
    
 /*
    obtine el string de la imagen para poder hacer las operaciones necesarias
    */
    public String getImagen( ){
        return JTImagen.getText();
        
    }
    /*
    parcea el string de la cedula a double
    */
    public double getCedula(){
        double Cedula =  Double.parseDouble(JTCedula.getText());        
        return Cedula;
    }
    /*
    obtien el string del nombre
    */
    public String getNombre(){
        return JTNombre.getText();
    }
    /*
    obtine el string del apellido 
    */
    public String getApellido(){
        return JTApellido.getText();
    }
    /*
    obtiene el string del correo
    */
    public String getCorreo(){
        return JTCorreo.getText();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        activa el boton de buscar imagen, busca el archibo y manda el erroro si la iamgen no esta dentro de la carpeta
        */
        if(BBuscar == e.getSource()){
            JFileChooser fc = new JFileChooser( "./data/imagenes" );
            fc.setDialogTitle( "Imagen del Aspirante" );
            int resultado = fc.showOpenDialog( this );
            if( resultado == JFileChooser.APPROVE_OPTION ){
                File archivo = fc.getSelectedFile( );
                String strArchivo = archivo.getAbsolutePath( );
                String strCarpetaImagenes = new File( "data/imagenes" ).getAbsolutePath( );

                if( strArchivo.startsWith( strCarpetaImagenes ) ){
                    JTImagen.setText( "data/imagenes/" + archivo.getName( ) );
                    ImageIcon icono = new ImageIcon(archivo.toString());
                    Icon ico = new ImageIcon(icono.getImage().getScaledInstance(LMImagen.getWidth(),LMImagen.getHeight(), Image.SCALE_DEFAULT));
                    LMImagen.setIcon(ico);
                    
                }else{
                    JOptionPane.showMessageDialog( this, "La imagen debe estar en la carpeta " + strCarpetaImagenes );
                }
                
            }
            /*
            guarda los datos despues de validar cada uno de los datos y envia mensaje de creado o error segun la validacion
            */
        }if(BGuardar == e.getSource()){
            if(validarDatosArtista() &&  validarDatosCorreo(JTCorreo.getText())){
            persona = new Aspirante(JTNombre.getText(),JTApellido.getText(), JTImagen.getText(), getCedula() ,JTCorreo.getText(),((Profesion) CMProfesion.getSelectedItem()),convertirFecha(), obtenerEdad());
            listaArtista.add(persona);
            JOptionPane.showMessageDialog(null, "Aspirante  creado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            vaciarDatos();
            
           }else{
               JOptionPane.showMessageDialog(this, "email invalido", "intente de nuevo", JOptionPane.INFORMATION_MESSAGE);
                LCorreo.setForeground(Color.red);
                JTCorreo.requestFocus();
           }
            Date dateCalendar = calendario.getDate();
            
          
        }      
            
    }

    /*
    vacia los datos para poder ingresar o corregir una persona 
    */
    private void vaciarDatos() {
        
        ImageIcon icono2 =  new ImageIcon("./data/imagenes/fotodefecto.JPG");
        LMImagen.setIcon(icono2);
        JTImagen.setText("");
        LImagen.setForeground(Color.BLACK);
        JTNombre.setText("");
        LNombre.setForeground(Color.BLACK);
        JTApellido.setText("");
        LApellido.setForeground(Color.BLACK);
        JTCedula.setText("");
        LCedula.setForeground(Color.BLACK);
        JTCorreo.setText("");
        LCorreo.setForeground(Color.BLACK);
        CMProfesion.setSelectedIndex(0);
        
    }
    /*
    valida cada uno de los datos y devuelve un bolleano para verificar lso datos si es true o false
    */
    
    private boolean validarDatosArtista() {
        if(JTNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            LNombre.setForeground(Color.red);
            return false;
        } else if(CMProfesion.getSelectedItem() == profesion.SELECCIONE) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un genero.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            return false;        
        }  else if(JTApellido.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe ingresar el Apellido.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            LApellido.setForeground(Color.red);
        } else if(JTCedula.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe ingresar La cedula ", "Mensaje", JOptionPane.ERROR_MESSAGE);
            LCedula.setForeground(Color.red);            
        }else if (JTCorreo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe ingresarel correo.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            LCorreo.setForeground(Color.red);
        }
        
        return true;
    }
    
/*
    obtine los datos de la lista
    */
    public List<Aspirante> getListaArtista() {
        return listaArtista;
    }

    /*
    activacion del check box del genero en el panel
    */
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(CBHombre == ie.getSource()){
            if(CBHombre == ie.getSource()){
                System.out.println("Selecciono Hombre");
                JOptionPane.showMessageDialog(null, "Seleciono Hombre", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
           
        
        }
        if(CBMujer == ie.getSource()){
            if(CBMujer == ie.getSource()){
                System.out.println("Seleciono Mujer");
                JOptionPane.showMessageDialog(null, "Seleciono Mujer", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
           
            }
        }

    }
}
