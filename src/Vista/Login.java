package Vista;

import Modelo.Validaciones;
import Modelo.ControladorCliente;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;

/**
 * [Login] Clase de interfaz GUI de usuario y contraseña que permite acceder a la interfaz Menu
 * @since 24/11/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class Login extends javax.swing.JFrame {
     
    //Declaracion de variables estaticas
    public static Border bordeNegro;
    public static Image iconoVentana; 
    public static ControladorCliente logicaCliente;
    public static String usuarioActual;
    
    //Declaracion de variables de clase   
    private Validaciones validacionTotal;


//=======================================================================================================   
    /**
    * Constructor de la clase Login
    * @since Login.java
    */ 
    
    public Login() 
    {
        initComponents();
        
        //Inicializacion de Variables
        validacionTotal = new Validaciones();
        logicaCliente = new ControladorCliente();
        bordeNegro = new EtchedBorder(30,Color.black, Color.white);        
        iconoVentana = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Menu/logoMinimo.png"));     
        usuarioActual="";
        
        //Implementacion de ajustes
        ajustesGraficos();

    }
    
//=======================================================================================================
    /**
     * Metodo que realiza ajustes visuales a la interfaz
     */
    public final void ajustesGraficos()
    {        
        this.setLocationRelativeTo(null); 
        this.setIconImage(iconoVentana);
        this.getRootPane().setBorder(Login.bordeNegro);
        jLabelUsuario.requestFocus();         
    }
//======================================================================================================= 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPpal = new javax.swing.JPanel();
        jPanelContain = new javax.swing.JPanel();
        jPanelLogin = new javax.swing.JPanel();
        jLabelCerrar = new javax.swing.JLabel();
        jLabelimgUsuario = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jSeparatorUsuario = new javax.swing.JSeparator();
        jLabelimgDireccionIP = new javax.swing.JLabel();
        jLabelDireccionIP = new javax.swing.JLabel();
        jTextFieldDireccionIP = new javax.swing.JTextField();
        jSeparatorDirecionIP = new javax.swing.JSeparator();
        jLabelimgPuerto = new javax.swing.JLabel();
        jLabelPuerto = new javax.swing.JLabel();
        jTextFieldPuerto = new javax.swing.JTextField();
        jSeparatorPuerto = new javax.swing.JSeparator();
        jButtonLogin = new java.awt.Button();
        jPanelBuffer = new javax.swing.JPanel();
        jLabelimgBuffer = new javax.swing.JLabel();
        jLabelBuffer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanelPpal.setBackground(new java.awt.Color(255, 255, 255));

        jPanelContain.setBackground(new java.awt.Color(255, 255, 255));
        jPanelContain.setLayout(new java.awt.CardLayout());

        jPanelLogin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCerrar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabelCerrar.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCerrar.setText("X");
        jLabelCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelCerrarMousePressed(evt);
            }
        });
        jPanelLogin.add(jLabelCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 27, -1));

        jLabelimgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/usuario.png"))); // NOI18N
        jPanelLogin.add(jLabelimgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 31));

        jLabelUsuario.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(51, 51, 51));
        jLabelUsuario.setText("Usuario");
        jPanelLogin.add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jTextFieldUsuario.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldUsuario.setText("Digite su nombre de usuario");
        jTextFieldUsuario.setBorder(null);
        jTextFieldUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldUsuarioFocusGained(evt);
            }
        });
        jPanelLogin.add(jTextFieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 302, 23));

        jSeparatorUsuario.setBackground(new java.awt.Color(41, 128, 185));
        jSeparatorUsuario.setForeground(new java.awt.Color(41, 128, 185));
        jPanelLogin.add(jSeparatorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 302, 10));

        jLabelimgDireccionIP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/direccionIP.png"))); // NOI18N
        jLabelimgDireccionIP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelLogin.add(jLabelimgDireccionIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 31));

        jLabelDireccionIP.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabelDireccionIP.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDireccionIP.setText("Direccion IP");
        jPanelLogin.add(jLabelDireccionIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jTextFieldDireccionIP.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldDireccionIP.setText("localhost");
        jTextFieldDireccionIP.setBorder(null);
        jTextFieldDireccionIP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDireccionIPFocusGained(evt);
            }
        });
        jPanelLogin.add(jTextFieldDireccionIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 302, 23));

        jSeparatorDirecionIP.setBackground(new java.awt.Color(41, 128, 185));
        jSeparatorDirecionIP.setForeground(new java.awt.Color(41, 128, 185));
        jPanelLogin.add(jSeparatorDirecionIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 302, 10));

        jLabelimgPuerto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/puerto.png"))); // NOI18N
        jLabelimgPuerto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelLogin.add(jLabelimgPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 31));

        jLabelPuerto.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabelPuerto.setForeground(new java.awt.Color(51, 51, 51));
        jLabelPuerto.setText("Puerto");
        jPanelLogin.add(jLabelPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jTextFieldPuerto.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldPuerto.setText("4000");
        jTextFieldPuerto.setBorder(null);
        jTextFieldPuerto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPuertoFocusGained(evt);
            }
        });
        jPanelLogin.add(jTextFieldPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 302, 23));

        jSeparatorPuerto.setBackground(new java.awt.Color(41, 128, 185));
        jSeparatorPuerto.setForeground(new java.awt.Color(41, 128, 185));
        jPanelLogin.add(jSeparatorPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 302, 10));

        jButtonLogin.setBackground(new java.awt.Color(41, 128, 185));
        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setLabel("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        jPanelLogin.add(jButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 328, 97, 35));

        jPanelContain.add(jPanelLogin, "card2");

        jPanelBuffer.setBackground(new java.awt.Color(255, 255, 255));

        jLabelimgBuffer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/buffer.gif"))); // NOI18N

        jLabelBuffer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelBuffer.setForeground(new java.awt.Color(41, 128, 185));
        jLabelBuffer.setText("Ingresando...");

        javax.swing.GroupLayout jPanelBufferLayout = new javax.swing.GroupLayout(jPanelBuffer);
        jPanelBuffer.setLayout(jPanelBufferLayout);
        jPanelBufferLayout.setHorizontalGroup(
            jPanelBufferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBufferLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addGroup(jPanelBufferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelimgBuffer, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBuffer))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanelBufferLayout.setVerticalGroup(
            jPanelBufferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBufferLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabelimgBuffer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelBuffer)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jPanelContain.add(jPanelBuffer, "card2");

        javax.swing.GroupLayout jPanelPpalLayout = new javax.swing.GroupLayout(jPanelPpal);
        jPanelPpal.setLayout(jPanelPpalLayout);
        jPanelPpalLayout.setHorizontalGroup(
            jPanelPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelPpalLayout.setVerticalGroup(
            jPanelPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPpal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPpal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        String usuario = jTextFieldUsuario.getText();
        String IP = jTextFieldDireccionIP.getText();
        String puerto = jTextFieldPuerto.getText();
        
        String arregloValidar[] = {usuario, IP, puerto};
        
        if (!(validacionTotal.validarExcepciones(arregloValidar, 2, 1, 0).equals("")))
        {
            new jAdvertencia(this, true,validacionTotal.validarExcepciones(arregloValidar, 2, 1, 0)).setVisible(true);                 
        }
        
        else
        {
            
            logicaCliente.setDatosConexion(usuario,IP,puerto);
            logicaCliente.iniciarCliente();
            if (logicaCliente.getDatosConexion())
            {
                jPanelLogin.setVisible(false);
                jPanelBuffer.setVisible(true);
                usuarioActual = usuario;
                new java.util.Timer().schedule(new TimerTask() {
                @Override
                public void run() 
                {
                    new Menu().setVisible(true);
                    dispose();
                }
                },1000*2);
            }

            else
            {
                new jAdvertencia(this, true,"<html><center>El servidor con la IP y puerto especificados no esta en linea</html></center>").setVisible(true);
            }   
        }     
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jTextFieldUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioFocusGained
        jTextFieldUsuario.setText("");
    }//GEN-LAST:event_jTextFieldUsuarioFocusGained

    private void jLabelCerrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarMousePressed
        System.exit(0);
    }//GEN-LAST:event_jLabelCerrarMousePressed

    private void jTextFieldDireccionIPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccionIPFocusGained
        jTextFieldDireccionIP.setText("");
    }//GEN-LAST:event_jTextFieldDireccionIPFocusGained

    private void jTextFieldPuertoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPuertoFocusGained
        jTextFieldPuerto.setText("");
    }//GEN-LAST:event_jTextFieldPuertoFocusGained
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
   
  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button jButtonLogin;
    private javax.swing.JLabel jLabelBuffer;
    private javax.swing.JLabel jLabelCerrar;
    private javax.swing.JLabel jLabelDireccionIP;
    private javax.swing.JLabel jLabelPuerto;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelimgBuffer;
    private javax.swing.JLabel jLabelimgDireccionIP;
    private javax.swing.JLabel jLabelimgPuerto;
    private javax.swing.JLabel jLabelimgUsuario;
    private javax.swing.JPanel jPanelBuffer;
    private javax.swing.JPanel jPanelContain;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JSeparator jSeparatorDirecionIP;
    private javax.swing.JSeparator jSeparatorPuerto;
    private javax.swing.JSeparator jSeparatorUsuario;
    private javax.swing.JTextField jTextFieldDireccionIP;
    private javax.swing.JTextField jTextFieldPuerto;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}


