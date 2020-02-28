package Vista;

import Modelo.Preguntas;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import javax.swing.*;

/**
 * [Menu] Clase de la interfaz GUI del Cliente
 *
 * @since 24/11/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */
public class Menu extends javax.swing.JFrame {

    //Declaracion de variables estaticas
    public static Image iconoVentana;
    public static DefaultListModel modeloLista;  
    public static int estadoExamen = 0;

    //Declaracion de variables de clase
    private ImageIcon iconoObtener;
    private ImageIcon iconoRegresar;  
    private Set<Integer> indexDeshabilitar;
    
    //Declaracion de variables auxiliares
    private Preguntas preguntaActual;
    private String opcionElegida;
    private int numeroPregunta;
    private boolean estadoPanel;

//=======================================================================================================
    /**
     * Constructor de la clase Menu
     * @since Menu.java
     */
    public Menu(){

        initComponents();

        //Inicializacion de variables  
        iconoObtener = new ImageIcon(getClass().getResource("/Imagenes/Menu/obtener.png"));
        iconoRegresar = new ImageIcon(getClass().getResource("/Imagenes/Menu/regresar.png"));
        iconoVentana = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Menu/logoMinimo.png"));
        modeloLista = new DefaultListModel();
        indexDeshabilitar = new HashSet<>();
        estadoPanel = false;
        opcionElegida = "";
        preguntaActual = null;

        //Implementacion de ajustes
        ajustesGraficos();
        ajustesFuncionales();

    }

//=======================================================================================================
    /**
     * Metodo que pone los numeros del Jlist segun la cantidad de preguntas del
     * archivo cargado en el servidor
     */
    public final void ajusteLista() 
    {
        int tamano = Login.logicaCliente.tamanoPreguntas();
        for (int i = 0; i < tamano; i++) {
            modeloLista.addElement(i + 1);
        }
        jListPreguntas.setModel(modeloLista);
    }
//=======================================================================================================    

    /**
     * Metodo que limpia el Jlist de preguntas ubicado en la izquierda de la GUI
     */
    public final void limpiarLista() 
    {
        modeloLista.clear();
    }
//=======================================================================================================

    /**
     * Metodo que realiza ajustes graficos a la interfaz GUI
     */
    public final void ajustesGraficos() 
    {
        jLabelTitulo.setText(jLabelTitulo.getText()+" "+Login.usuarioActual);
        jTextAreaTexto.setEditable(false);
        this.setLocationRelativeTo(null);
        this.getRootPane().setBorder(Login.bordeNegro);
        this.setIconImage(iconoVentana);
    }
//=======================================================================================================

    /**
     * Metodo que realiza ajustes en la funcionalidad de la interfaz GUI
     */

    public final void ajustesFuncionales() 
    {
        configuracionJList(jListPreguntas);
    }
//=======================================================================================================

    /**
     * Metodo que pone el color en un Jpanel cuando gana el foco
     * @param panel Componente a la que se aplicara el metodo
     */
    public void ponerColor(JPanel panel) 
    {
        panel.setBackground(new Color(240, 240, 240));
    }

//=======================================================================================================
    /**
     * Metodo que pone el color original en un Jpanel cuando pierde el foco
     * @param panel Componente a la que se aplicara el metodo
     */
    public void repintarColor(JPanel panel) {
        panel.setBackground(new Color(204, 204, 204));
    }

//=======================================================================================================
    /**
     * Sobreescritura del metodo para obtener el renderizado de una celda de una
     * lista, permite deshabilitar la celda
     * @param lista Variable que almacena la lista a la cual se aplicara el metodo
     */
    
    public void celdasJList(JList lista) 
    {
        
        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c;
                if (indexDeshabilitar.contains(index)) {
                    c = super.getListCellRendererComponent(
                            list, value, index, false, false);
                    c.setEnabled(false);
                } else {
                    c = super.getListCellRendererComponent(
                            list, value, index, isSelected, cellHasFocus);
                }
                return c;
            }
        });
    }
//=======================================================================================================  

    /**
     * Metodo que permite agregar al mapa de acciones de un JList, acciones de
     * moviento entre las filas
     * @param lista Variable que almacena el JList al cual se le va asignar un
     * mapa de acciones
     */
    public void mapaAccionesLista(JList lista) {
        desactivarItemJList(indexDeshabilitar, "");
        ActionMap am = lista.getActionMap();

        //Mapa de Accion Proxima Seleccion
        am.put("selectNextRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lista.getSelectedIndex();
                for (int i = index + 1; i < lista.getModel().getSize(); i++) {
                    if (!indexDeshabilitar.contains(i)) {
                        lista.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

        //Mapa de accion Seleccion Previa
        am.put("selectPreviousRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lista.getSelectedIndex();
                for (int i = index - 1; i >= 0; i--) {
                    if (!indexDeshabilitar.contains(i)) {
                        lista.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });
    }
//=======================================================================================================

    /**
     * Metodo que permite agregar un JList las caracteristicas de al seleccionar
     * un elemento deshabilitar el mismo
     * @param lista Variable que almacena el JLista al que se le aplicara a
     * configuracion
     */
    public void configuracionJList(JList lista) {
        
        celdasJList(lista);
        mapaAccionesLista(lista);

    }
//=======================================================================================================    

    /**
     * Metodo que permite desactivar un elemento especfico del JList
     * @param set Variable que almacena el mapa de acciones
     * @param index Variable que almacena la posicion del item a desactivar
     */

    public void desactivarItemJList(Set set, String index) {
        set.clear();
        set.addAll(Arrays.stream(index.split(",")).map(String::trim).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toSet()));
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

        buttonGroup = new javax.swing.ButtonGroup();
        jPanelPpal = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelSubtitulo = new javax.swing.JLabel();
        jLabelSalir = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelAcerca = new javax.swing.JLabel();
        jButtonPreguntas = new javax.swing.JButton();
        jPanelSlider = new diu.swe.habib.JPanelSlider.JPanelSlider();
        jPanelVacio = new javax.swing.JPanel();
        jPanelSlide = new javax.swing.JPanel();
        jPanelObtener = new javax.swing.JPanel();
        jLabelObtener = new javax.swing.JLabel();
        jLabelimgObtener = new javax.swing.JLabel();
        jScrollPaneLista = new javax.swing.JScrollPane();
        jListPreguntas = new javax.swing.JList<>();
        jPanelMenu = new javax.swing.JPanel();
        jPanelPreguntas = new javax.swing.JPanel();
        jRadioButtonOpcionA = new javax.swing.JRadioButton();
        jRadioButtonOpcionD = new javax.swing.JRadioButton();
        jRadioButtonOpcionC = new javax.swing.JRadioButton();
        jRadioButtonOpcionB = new javax.swing.JRadioButton();
        jPanelEnviar = new javax.swing.JPanel();
        jLabelEnviar = new javax.swing.JLabel();
        jLabelimgEnviar = new javax.swing.JLabel();
        jPanelNombrePregunta = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jPanelDescripcion = new javax.swing.JPanel();
        jScrollPaneAreaTexto = new javax.swing.JScrollPane();
        jTextAreaTexto = new javax.swing.JTextArea();
        jPanelTiempo = new javax.swing.JPanel();
        jLabelTiempo = new javax.swing.JLabel();
        jLabeimglTiempo = new javax.swing.JLabel();
        jLabelTemp = new javax.swing.JLabel();
        jPanelCancelar = new javax.swing.JPanel();
        jLabelCancelar = new javax.swing.JLabel();
        jLabelimgCancelar = new javax.swing.JLabel();
        jLabelCalificacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanelPpal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPpal.setMinimumSize(new java.awt.Dimension(860, 600));
        jPanelPpal.setPreferredSize(new java.awt.Dimension(854, 600));
        jPanelPpal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTitulo.setBackground(new java.awt.Color(45, 118, 232));
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSubtitulo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelSubtitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSubtitulo.setText("Sistema de realizacion de examen");
        jPanelTitulo.add(jLabelSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 160, -1));

        jLabelSalir.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabelSalir.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSalir.setText("X");
        jLabelSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSalirMousePressed(evt);
            }
        });
        jPanelTitulo.add(jLabelSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 20, -1));

        jLabelTitulo.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Welcome");
        jPanelTitulo.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 500, -1));

        jLabelAcerca.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabelAcerca.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAcerca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAcerca.setText("?");
        jLabelAcerca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAcercaMousePressed(evt);
            }
        });
        jPanelTitulo.add(jLabelAcerca, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 10, -1));

        jButtonPreguntas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/obtener.png"))); // NOI18N
        jButtonPreguntas.setBorder(null);
        jButtonPreguntas.setContentAreaFilled(false);
        jButtonPreguntas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreguntasActionPerformed(evt);
            }
        });
        jPanelTitulo.add(jButtonPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 80));

        jPanelPpal.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 120));

        jPanelSlider.setBorder(null);
        jPanelSlider.setPreferredSize(new java.awt.Dimension(110, 480));

        jPanelVacio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelVacioLayout = new javax.swing.GroupLayout(jPanelVacio);
        jPanelVacio.setLayout(jPanelVacioLayout);
        jPanelVacioLayout.setHorizontalGroup(
            jPanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanelVacioLayout.setVerticalGroup(
            jPanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jPanelSlider.add(jPanelVacio, "card2");

        jPanelSlide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelObtener.setBackground(new java.awt.Color(204, 204, 204));
        jPanelObtener.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelObtenerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelObtenerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelObtenerMousePressed(evt);
            }
        });
        jPanelObtener.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelObtener.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelObtener.setForeground(new java.awt.Color(45, 118, 232));
        jLabelObtener.setText("Obtener");
        jPanelObtener.add(jLabelObtener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabelimgObtener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/mano.png"))); // NOI18N
        jPanelObtener.add(jLabelimgObtener, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 30, 30));

        jPanelSlide.add(jPanelObtener, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 90, 64));

        jListPreguntas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jListPreguntas.setForeground(new java.awt.Color(45, 118, 232));
        jListPreguntas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneLista.setViewportView(jListPreguntas);

        jPanelSlide.add(jScrollPaneLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, 320));

        jPanelSlider.add(jPanelSlide, "card3");

        jPanelPpal.add(jPanelSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMenu.setMinimumSize(new java.awt.Dimension(860, 600));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(854, 600));
        jPanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPreguntas.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelPreguntasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelPreguntasMouseExited(evt);
            }
        });

        buttonGroup.add(jRadioButtonOpcionA);
        jRadioButtonOpcionA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButtonOpcionA.setForeground(new java.awt.Color(45, 118, 232));

        buttonGroup.add(jRadioButtonOpcionD);
        jRadioButtonOpcionD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButtonOpcionD.setForeground(new java.awt.Color(45, 118, 232));

        buttonGroup.add(jRadioButtonOpcionC);
        jRadioButtonOpcionC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButtonOpcionC.setForeground(new java.awt.Color(45, 118, 232));

        buttonGroup.add(jRadioButtonOpcionB);
        jRadioButtonOpcionB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButtonOpcionB.setForeground(new java.awt.Color(45, 118, 232));

        javax.swing.GroupLayout jPanelPreguntasLayout = new javax.swing.GroupLayout(jPanelPreguntas);
        jPanelPreguntas.setLayout(jPanelPreguntasLayout);
        jPanelPreguntasLayout.setHorizontalGroup(
            jPanelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPreguntasLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButtonOpcionA, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonOpcionC, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonOpcionB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonOpcionD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanelPreguntasLayout.setVerticalGroup(
            jPanelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPreguntasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButtonOpcionA)
                    .addComponent(jRadioButtonOpcionB))
                .addGap(18, 18, 18)
                .addGroup(jPanelPreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonOpcionC)
                    .addComponent(jRadioButtonOpcionD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelMenu.add(jPanelPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 530, 90));

        jPanelEnviar.setBackground(new java.awt.Color(204, 204, 204));
        jPanelEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelEnviarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelEnviarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelEnviarMousePressed(evt);
            }
        });
        jPanelEnviar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelEnviar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEnviar.setForeground(new java.awt.Color(45, 118, 232));
        jLabelEnviar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEnviar.setText(" Enviar");
        jPanelEnviar.add(jLabelEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 70, -1));

        jLabelimgEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/enviar.png"))); // NOI18N
        jPanelEnviar.add(jLabelimgEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        jPanelMenu.add(jPanelEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 70, 70));

        jPanelNombrePregunta.setBackground(new java.awt.Color(204, 204, 204));
        jPanelNombrePregunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelNombrePreguntaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelNombrePreguntaMouseExited(evt);
            }
        });
        jPanelNombrePregunta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(45, 118, 232));
        jLabelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelNombrePregunta.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 11, 473, 20));

        jPanelMenu.add(jPanelNombrePregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 530, 40));

        jPanelDescripcion.setBackground(new java.awt.Color(204, 204, 204));
        jPanelDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelDescripcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelDescripcionMouseExited(evt);
            }
        });
        jPanelDescripcion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextAreaTexto.setColumns(20);
        jTextAreaTexto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextAreaTexto.setRows(5);
        jTextAreaTexto.setText("\n");
        jTextAreaTexto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPaneAreaTexto.setViewportView(jTextAreaTexto);

        jPanelDescripcion.add(jScrollPaneAreaTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 510, 180));

        jPanelMenu.add(jPanelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 530, 200));

        jPanelTiempo.setBackground(new java.awt.Color(204, 204, 204));
        jPanelTiempo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelTiempoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelTiempoMouseExited(evt);
            }
        });
        jPanelTiempo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTiempo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelTiempo.setForeground(new java.awt.Color(45, 118, 232));
        jLabelTiempo.setText("Tiempo restante");
        jPanelTiempo.add(jLabelTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 15, -1, 20));

        jLabeimglTiempo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabeimglTiempo.setForeground(new java.awt.Color(45, 118, 232));
        jLabeimglTiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/timer.png"))); // NOI18N
        jPanelTiempo.add(jLabeimglTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, -1));

        jLabelTemp.setFont(new java.awt.Font("DS-Digital", 1, 24)); // NOI18N
        jLabelTemp.setForeground(new java.awt.Color(45, 118, 232));
        jPanelTiempo.add(jLabelTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));
        jLabelTemp.getAccessibleContext().setAccessibleDescription("");

        jPanelMenu.add(jPanelTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 300, 50));

        jPanelCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jPanelCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelCancelarMousePressed(evt);
            }
        });
        jPanelCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCancelar.setForeground(new java.awt.Color(45, 118, 232));
        jLabelCancelar.setText("Cancelar ");
        jPanelCancelar.add(jLabelCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabelimgCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/cancelar.png"))); // NOI18N
        jPanelCancelar.add(jLabelimgCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        jPanelMenu.add(jPanelCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 70, 70));

        jLabelCalificacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCalificacion.setForeground(new java.awt.Color(255, 51, 51));
        jLabelCalificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelMenu.add(jLabelCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 200, -1));

        jPanelPpal.add(jPanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 120, 590, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSalirMousePressed
        new jConfirmacion(this, true, "<html><center>¿Esta seguro que desea salir del cliente? </center></html>").setVisible(true);
        if(jConfirmacion.opcion ==1)
        {
            if(estadoExamen==1)
            {
                new jAdvertencia(this, true, "<html><center> Hay un examen activo en estos momentos usted no puede salir, por favor intente mas tarde </center></html>").setVisible(true);
            }
            
            else
            {
                System.exit(0);
            }
        }   
    }//GEN-LAST:event_jLabelSalirMousePressed

    private void jPanelPreguntasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPreguntasMouseEntered
        ponerColor(jPanelPreguntas);
    }//GEN-LAST:event_jPanelPreguntasMouseEntered

    private void jPanelPreguntasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPreguntasMouseExited
        repintarColor(jPanelPreguntas);
    }//GEN-LAST:event_jPanelPreguntasMouseExited

    private void jPanelEnviarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEnviarMouseEntered
        ponerColor(jPanelEnviar);
    }//GEN-LAST:event_jPanelEnviarMouseEntered

    private void jPanelEnviarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEnviarMouseExited
        repintarColor(jPanelEnviar);
    }//GEN-LAST:event_jPanelEnviarMouseExited

    private void jPanelCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelarMouseEntered
        ponerColor(jPanelCancelar);
    }//GEN-LAST:event_jPanelCancelarMouseEntered

    private void jPanelCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelarMouseExited
        repintarColor(jPanelCancelar);
    }//GEN-LAST:event_jPanelCancelarMouseExited

    private void jPanelNombrePreguntaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNombrePreguntaMouseEntered
        ponerColor(jPanelNombrePregunta);
    }//GEN-LAST:event_jPanelNombrePreguntaMouseEntered

    private void jPanelNombrePreguntaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNombrePreguntaMouseExited
        repintarColor(jPanelNombrePregunta);
    }//GEN-LAST:event_jPanelNombrePreguntaMouseExited

    private void jPanelDescripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDescripcionMouseEntered
        ponerColor(jPanelDescripcion);
    }//GEN-LAST:event_jPanelDescripcionMouseEntered

    private void jPanelDescripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDescripcionMouseExited
        repintarColor(jPanelDescripcion);
    }//GEN-LAST:event_jPanelDescripcionMouseExited

    private void jPanelTiempoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTiempoMouseEntered
        ponerColor(jPanelTiempo);
    }//GEN-LAST:event_jPanelTiempoMouseEntered

    private void jPanelTiempoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelTiempoMouseExited
        repintarColor(jPanelTiempo);
    }//GEN-LAST:event_jPanelTiempoMouseExited

    private void jPanelObtenerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelObtenerMouseEntered
        ponerColor(jPanelObtener);
    }//GEN-LAST:event_jPanelObtenerMouseEntered

    private void jPanelObtenerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelObtenerMouseExited
        repintarColor(jPanelObtener);
    }//GEN-LAST:event_jPanelObtenerMouseExited

    private void jPanelObtenerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelObtenerMousePressed
        
        numeroPregunta = jListPreguntas.getSelectedIndex();
        
        if (numeroPregunta == -1) {
            new jAdvertencia(this, true, "<html><center>No ha seleccionado ninguna pregunta</center></html>").setVisible(true);
        } 
        
        else {
            try {

                Login.logicaCliente.pedirPregunta(numeroPregunta);
                preguntaActual = Login.logicaCliente.recibirPregunta();
                desactivarItemJList(indexDeshabilitar, Login.logicaCliente.getOcupadas());
                jListPreguntas.repaint();
                
                if (preguntaActual.getEstado().equals("Ocupada")) 
                {
                    jPanelSlider.nextPanel(10,jPanelSlide,jPanelSlider.left);
                    jButtonPreguntas.setIcon(iconoObtener);
                    estadoPanel=false;
                    jLabelNombre.setText(preguntaActual.getNombre());
                    jTextAreaTexto.setText(preguntaActual.getDescripcion());
                    jRadioButtonOpcionA.setText(preguntaActual.getOpciones()[0]);
                    jRadioButtonOpcionB.setText(preguntaActual.getOpciones()[1]);
                    jRadioButtonOpcionC.setText(preguntaActual.getOpciones()[2]);
                    jRadioButtonOpcionD.setText(preguntaActual.getOpciones()[3]);
                    jPanelObtener.setVisible(false);
                    jButtonPreguntas.setEnabled(false);
                } 
                else if (preguntaActual.getEstado().equals("Ocupada por otro")) 
                {
                    new jAdvertencia(this, true, "<html><center>La pregunta se encuentra ocupada seleccione otra, mientras es liberada</center></html>").setVisible(true);
                }
                
                else if (preguntaActual.getEstado().equals("Respondida")) 
                {
                    new jAdvertencia(this, true, "<html><center>La pregunta se encuentra respondida seleccione otra, mientras es liberada</center></html>").setVisible(true);
                }
            } 
            
            catch (IOException | ClassNotFoundException ex) {
                new jAdvertencia(this, true, "<html><center>La pregunta no se pudo cargar</center></html>").setVisible(true);
            }
        }
    }//GEN-LAST:event_jPanelObtenerMousePressed

    private void jPanelEnviarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEnviarMousePressed
        String opcion = "";
        numeroPregunta = jListPreguntas.getSelectedIndex();
        if (numeroPregunta == -1) 
        {
            new jAdvertencia(this, true, "<html><center>No ha seleccionado ninguna pregunta</center></html>").setVisible(true);
        } 
        
        else {

            if (jRadioButtonOpcionA.isSelected()) {
                opcion = "A";
            }

            if (jRadioButtonOpcionB.isSelected()) {
                opcion = "B";
            }

            if (jRadioButtonOpcionC.isSelected()) {
                opcion = "C";
            }

            if (jRadioButtonOpcionD.isSelected()) {
                opcion = "D";
            }

            if (opcion.equals("")) 
            {
                new jAdvertencia(this, true, "<html><center>No ha seleccionado ninguna respuesta</center></html>").setVisible(true);
            } 
            else 
            {
                new jConfirmacion(this, true, "<html><center>¿Esta seguro que desea enviar esta respuesta (esta no se puede cambiar) ?</center></html>").setVisible(true);
                if (jConfirmacion.opcion == 1) 
                {
                    Login.logicaCliente.enviarRespuesta(numeroPregunta, opcion);
                    preguntaActual = null;
                    jButtonPreguntas.setEnabled(true);
                    jPanelObtener.setVisible(true);
                    jLabelNombre.setText("");
                    jTextAreaTexto.setText("");
                    jRadioButtonOpcionA.setText("");
                    jRadioButtonOpcionB.setText("");
                    jRadioButtonOpcionC.setText("");
                    jRadioButtonOpcionD.setText("");
                    buttonGroup.clearSelection();
                    limpiarLista();
                    new jInformacion(this, true, "<html><center> La respuesta se envio correctamente </center></html>").setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jPanelEnviarMousePressed

    private void jPanelCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelarMousePressed
       
        if (preguntaActual != null) 
        {
            new jConfirmacion(this, true, "<html><center>¿Esta seguro que desea liberar la pregunta(esto ocasionara que otro estudiante pueda responderla) ?</center></html>").setVisible(true);
            if (jConfirmacion.opcion == 1) 
            {
                preguntaActual = null;
                jListPreguntas.repaint();
                Login.logicaCliente.enviarLibre(numeroPregunta);
                jLabelNombre.setText("");
                jTextAreaTexto.setText("");
                jRadioButtonOpcionA.setText("");
                jRadioButtonOpcionB.setText("");
                jRadioButtonOpcionC.setText("");
                jRadioButtonOpcionD.setText("");
                jButtonPreguntas.setEnabled(true);
                jPanelObtener.setVisible(true);
                buttonGroup.clearSelection();
                limpiarLista();
            }
        } 
        else 
        {            
            new jAdvertencia(this, true, "<html><center>No ha seleccionado ninguna pregunta</center></html>").setVisible(true);
            jPanelSlider.nextPanel(10,jPanelVacio,jPanelSlider.left);
            estadoPanel=false;
            jButtonPreguntas.setIcon(iconoObtener); 
            limpiarLista();
        }
    }//GEN-LAST:event_jPanelCancelarMousePressed

    private void jLabelAcercaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAcercaMousePressed
        new jInformacion(this, true,"© 2017 Julian Castaño, reservados todos los derechos.").setVisible(true);
    }//GEN-LAST:event_jLabelAcercaMousePressed

    private void jButtonPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreguntasActionPerformed
            if(estadoPanel==false)
            {
                jPanelSlider.nextPanel(10,jPanelSlide,jPanelSlider.right);
                jButtonPreguntas.setIcon(iconoRegresar);
                estadoPanel=true;
                ajusteLista();  
                desactivarItemJList(indexDeshabilitar, Login.logicaCliente.getRespondidas());
                jListPreguntas.repaint();
                              
            }
        
            else if (estadoPanel == true)
            {
                jPanelSlider.nextPanel(10,jPanelVacio,jPanelSlider.left);
                estadoPanel=false;
                jButtonPreguntas.setIcon(iconoObtener);              
                desactivarItemJList(indexDeshabilitar, Login.logicaCliente.getRespondidas());
                jListPreguntas.repaint();
                limpiarLista();
            }
    }//GEN-LAST:event_jButtonPreguntasActionPerformed

//=======================================================================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    public static javax.swing.JButton jButtonPreguntas;
    private javax.swing.JLabel jLabeimglTiempo;
    private javax.swing.JLabel jLabelAcerca;
    public static javax.swing.JLabel jLabelCalificacion;
    private javax.swing.JLabel jLabelCancelar;
    private javax.swing.JLabel jLabelEnviar;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelObtener;
    private javax.swing.JLabel jLabelSalir;
    private javax.swing.JLabel jLabelSubtitulo;
    public static javax.swing.JLabel jLabelTemp;
    private javax.swing.JLabel jLabelTiempo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelimgCancelar;
    private javax.swing.JLabel jLabelimgEnviar;
    private javax.swing.JLabel jLabelimgObtener;
    public static javax.swing.JList<String> jListPreguntas;
    public static javax.swing.JPanel jPanelCancelar;
    private javax.swing.JPanel jPanelDescripcion;
    private javax.swing.JPanel jPanelEnviar;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelNombrePregunta;
    private javax.swing.JPanel jPanelObtener;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelPreguntas;
    private javax.swing.JPanel jPanelSlide;
    private diu.swe.habib.JPanelSlider.JPanelSlider jPanelSlider;
    private javax.swing.JPanel jPanelTiempo;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelVacio;
    private javax.swing.JRadioButton jRadioButtonOpcionA;
    private javax.swing.JRadioButton jRadioButtonOpcionB;
    private javax.swing.JRadioButton jRadioButtonOpcionC;
    private javax.swing.JRadioButton jRadioButtonOpcionD;
    private javax.swing.JScrollPane jScrollPaneAreaTexto;
    private javax.swing.JScrollPane jScrollPaneLista;
    private javax.swing.JTextArea jTextAreaTexto;
    // End of variables declaration//GEN-END:variables


}
