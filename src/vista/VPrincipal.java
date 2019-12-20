package vista;

import vista.utilidades.LinePainter;
import vista.utilidades.TextStyle;
import vista.utilidades.TextLineNumber;
import controlador.ControladorDeArchivos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Pedro
 */
public class VPrincipal extends javax.swing.JFrame {

    private MouseListener mListener;
    private int nTabs = 0;

    private JPopupMenu pop;
    private JMenuItem jMIPNuevo;
    private JMenuItem jMIPAbrir;
    private JMenuItem jMIPGuardar;
    private JMenuItem jMIPCerrarActual;
    private JMenuItem jMIPCerrarTodo;
    private JMenuItem jMIPVerificar;
    private JMenuItem jMIIra;

    /**
     * Creates new form VPrincipal
     */
    public VPrincipal() {
        initComponents();
        this.inicializarPops();
        this.setLocationRelativeTo(null);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        inicializarMouseListener();
        this.jMINuevoActionPerformed(null);
    }
    
    private void actualizarVista() {
        if (this.jTPArchivos.getTabCount() == 0) {
            this.jMIPGuardar.setEnabled(false);
            this.jMIPCerrarActual.setEnabled(false);
            this.jMIPCerrarTodo.setEnabled(false);
            this.jMIPVerificar.setEnabled(false);

            this.jMIGuardar.setEnabled(false);
            this.jMIVerificar.setEnabled(false);
            this.jMIIr.setEnabled(false);
        } else {
            this.jMIPGuardar.setEnabled(true);
            this.jMIPCerrarActual.setEnabled(true);
            this.jMIPCerrarTodo.setEnabled(true);
            this.jMIPVerificar.setEnabled(true);

            this.jMIGuardar.setEnabled(true);
            this.jMIVerificar.setEnabled(true);
            this.jMIIr.setEnabled(true);
        }
    }

    private void inicializarPops() {
        this.pop = new JPopupMenu();

        jMIIra = new JMenuItem("Ir a...");
        jMIIra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_I,
                java.awt.event.InputEvent.CTRL_MASK));
        jMIIra.addActionListener(a -> {
            this.jMIIrActionPerformed(a);
        });

        jMIPNuevo = new JMenuItem("Nuevo archivo");
        jMIPNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_N,
                java.awt.event.InputEvent.CTRL_MASK));
        this.jMIPNuevo.setIcon(this.jMINuevo.getIcon());
        this.jMIPNuevo.addActionListener((ActionListener) -> {
            this.jMINuevoActionPerformed(ActionListener);
        });

        jMIPAbrir = new JMenuItem("Abrir archivo");
        jMIPAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_O,
                java.awt.event.InputEvent.CTRL_MASK));
        this.jMIPAbrir.setIcon(this.jMIAbrir.getIcon());
        this.jMIPAbrir.addActionListener((actionListener) -> {
            this.jMIAbrirActionPerformed(actionListener);
        });

        jMIPGuardar = new JMenuItem("Guardar actual");
        jMIPGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.CTRL_MASK));
        this.jMIPGuardar.setIcon(this.jMIGuardar.getIcon());
        this.jMIPGuardar.addActionListener((actionListener) -> {
            this.jMIGuardarActionPerformed(actionListener);
        });

        jMIPCerrarActual = new JMenuItem("Cerrar actual");
        this.jMIPCerrarActual.setIcon(this.jMICerrar.getIcon());
        jMIPCerrarActual.addActionListener((action) -> {
            this.jTPArchivos.remove(this.jTPArchivos.getSelectedIndex());
            this.actualizarVista();
        });

        this.jMIPCerrarTodo = new JMenuItem("Cerrar todo");
        this.jMIPCerrarTodo.setIcon(this.jMICerrar.getIcon());
        this.jMIPCerrarTodo.addActionListener((ActionEvent e) -> {
            this.jTPArchivos.removeAll();
            this.actualizarVista();
        });

        this.jMIPVerificar = new JMenuItem("Verificar actual");
        this.jMIPVerificar.setIcon(this.jMIVerificar.getIcon());
        this.jMIPVerificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_V,
                java.awt.event.InputEvent.ALT_MASK));
        this.jMIPVerificar.addActionListener((a) -> {
            jMIVerificarActionPerformed(a);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTPArchivos = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMINuevo = new javax.swing.JMenuItem();
        jMIAbrir = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMIGuardar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMICerrar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMIIr = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMIVerificar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        JMIInformacion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTPArchivos.setBackground(new java.awt.Color(51, 51, 51));
        jTPArchivos.setForeground(new java.awt.Color(51, 51, 51));
        jTPArchivos.setAutoscrolls(true);
        jTPArchivos.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        jTPArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPArchivosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTPArchivosMousePressed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("File");

        jMINuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMINuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/new.png"))); // NOI18N
        jMINuevo.setText("Nuevo archivo");
        jMINuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMINuevoActionPerformed(evt);
            }
        });
        jMenu1.add(jMINuevo);

        jMIAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMIAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/open.png"))); // NOI18N
        jMIAbrir.setText("Abrir archivo");
        jMIAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(jMIAbrir);
        jMenu1.add(jSeparator1);

        jMIGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMIGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/save.png"))); // NOI18N
        jMIGuardar.setText("Guardar archivo");
        jMIGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(jMIGuardar);
        jMenu1.add(jSeparator2);

        jMICerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMICerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/close.png"))); // NOI18N
        jMICerrar.setText("Cerrar");
        jMICerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICerrarActionPerformed(evt);
            }
        });
        jMenu1.add(jMICerrar);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Navegación");

        jMIIr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMIIr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/goto.png"))); // NOI18N
        jMIIr.setText("Ir a...");
        jMIIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIIrActionPerformed(evt);
            }
        });
        jMenu4.add(jMIIr);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Depuración");

        jMIVerificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMIVerificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/validator.png"))); // NOI18N
        jMIVerificar.setText("Verificar archivo");
        jMIVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIVerificarActionPerformed(evt);
            }
        });
        jMenu3.add(jMIVerificar);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Ayuda");

        JMIInformacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        JMIInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/list.png"))); // NOI18N
        JMIInformacion.setText("Acerca de...");
        JMIInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIInformacionActionPerformed(evt);
            }
        });
        jMenu2.add(JMIInformacion);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPArchivos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMINuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMINuevoActionPerformed
        //Se crea el pnale de texto.
        JTextPane textPane = new JTextPane();
        //Se configura el panel de texto.
        textPane.setContentType("text/plain");
        textPane.setEditorKit(new StyledEditorKit());
        textPane.setMargin(new Insets(5, 5, 5, 5));
        textPane.addMouseListener(mListener);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 18));
        //Se crea el panel scroll y se le añade el panel de texto.
        JScrollPane sp = new JScrollPane(textPane);
        //Se determina el nombre de la nueva pestaña.
        String nombre = this.nTabs == 0
                ? "Nuevo archivo"
                : "Nuevo archivo (" + this.nTabs + ")";
        //Se agrega una nueva pestaña al panel de pestañas, con el nombre 
        //y el panel scroll que se crearon antes.
        this.jTPArchivos.add(nombre, sp);
        //Se aumentaa el contador de pestañas.
        this.nTabs++;
        //Se muestra la pestaña añadida.
        this.jTPArchivos.setSelectedIndex(jTPArchivos.getTabCount() - 1);
        textPane.requestFocus();
        //Activa los menús dependiendo de si hay pestañas abiertas o no.
        this.actualizarVista();

        //Se añaden los efectos visuales del editor.
        new LinePainter(textPane).setColor(new Color(230, 230, 250));
        textPane.setStyledDocument(new TextStyle());
        TextLineNumber tln = new TextLineNumber(textPane);
        sp.setRowHeaderView(tln);

        //Código por defecto.
        textPane.setText("INICIO"
                + "\n#Código principal aquí."
                + "\n\nFINAL"
                + "\n#Implementación de funciones aquí.");
    }//GEN-LAST:event_jMINuevoActionPerformed

    private void jTPArchivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPArchivosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPArchivosMouseClicked

    private void jTPArchivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPArchivosMousePressed
        //Menú click derecho.
        if (SwingUtilities.isRightMouseButton(evt)) {
            this.pop.removeAll();
            this.pop.add(this.jMIPCerrarActual);
            this.pop.add(this.jMIPCerrarTodo);
            this.pop.add(new JPopupMenu.Separator());
            this.pop.add(this.jMIPGuardar);
            this.pop.add(new JPopupMenu.Separator());
            this.pop.add(this.jMIPVerificar);
            this.pop.add(new JPopupMenu.Separator());
            this.pop.add(this.jMIPNuevo);
            this.pop.add(this.jMIPAbrir);
            this.showPopup(evt);
        }
    }//GEN-LAST:event_jTPArchivosMousePressed

    private void jMIAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAbrirActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("AFE", "afe");
        chooser.setFileFilter(filter);
        int seleccion = chooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            File file = archivo.getAbsolutePath().endsWith(".afe")
                    ? new File(archivo.getAbsolutePath())
                    : new File(archivo.getAbsolutePath() + ".afe");
            JTextPane textPanel = new JTextPane(new TextStyle());
            textPanel.setContentType("text/plain");
            textPanel.setEditorKit(new StyledEditorKit());
            textPanel.setMargin(new Insets(5, 5, 5, 5));
            textPanel.addMouseListener(mListener);
            textPanel.setFont(new Font("Monospaced", Font.PLAIN, 18));
            JScrollPane sp = new JScrollPane(textPanel);
            String nombre = file.getName();
            this.jTPArchivos.add(nombre, sp);
            this.nTabs++;
            //Carga rl archivo
            new ControladorDeArchivos().abrir(textPanel, file);
            this.jTPArchivos.setSelectedIndex(jTPArchivos.getTabCount() - 1);
            textPanel.requestFocus();
            this.actualizarVista();

            //Efectos visuales.
            new LinePainter(textPanel).setColor(new Color(230, 230, 250));
            textPanel.setStyledDocument(new TextStyle());
            TextLineNumber tln = new TextLineNumber(textPanel);
            sp.setRowHeaderView(tln);
        }
    }//GEN-LAST:event_jMIAbrirActionPerformed

    private void jMIGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGuardarActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("AFE", "afe");
        chooser.setFileFilter(filter);
        int seleccion = chooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            File file = archivo.getAbsolutePath().endsWith(".afe")
                    ? new File(archivo.getAbsolutePath())
                    : new File(archivo.getAbsolutePath() + ".afe");
            JViewport viewport = ((JScrollPane) jTPArchivos.getSelectedComponent()).getViewport();
            JEditorPane editorPane = (JTextPane) viewport.getView();
            //Guardar.
            new ControladorDeArchivos().guardar(editorPane.getText(), file);
        }
    }//GEN-LAST:event_jMIGuardarActionPerformed

    private void jMICerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMICerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMICerrarActionPerformed

    private void jMIVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIVerificarActionPerformed
        //Selecciona el panel de texto que se está utilizando.
        JViewport viewport = ((JScrollPane) jTPArchivos.getSelectedComponent()).getViewport();
        JTextPane textpane = (JTextPane) viewport.getView();
        
        new JFResultado(textpane).setVisible(true);
    }//GEN-LAST:event_jMIVerificarActionPerformed

    private void jMIIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIIrActionPerformed
        JViewport viewport = ((JScrollPane) jTPArchivos.getSelectedComponent()).getViewport();
        JTextPane textPane = (JTextPane) viewport.getView();

        new GoTo(this, true, textPane).setVisible(true);
    }//GEN-LAST:event_jMIIrActionPerformed

    private void JMIInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIInformacionActionPerformed
        
        System.out.println("Hola mundo");
        new About(this, true).setVisible(true);
        
    }//GEN-LAST:event_JMIInformacionActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows"
                    + ".WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("Principal - Error con el LookAndFeel.");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VPrincipal().setVisible(true);
        });
    }

    private void showPopup(MouseEvent e) {
        pop.show(e.getComponent(), e.getX(), e.getY());
    }

    private void inicializarMouseListener() {
        this.mListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    pop.removeAll();
                    pop.add(jMIIra);
                    pop.add(new JPopupMenu.Separator());
                    pop.add(jMIPCerrarActual);
                    pop.add(jMIPCerrarTodo);
                    pop.add(new JPopupMenu.Separator());
                    pop.add(jMIPGuardar);
                    pop.add(new JPopupMenu.Separator());
                    pop.add(jMIPVerificar);
                    showPopup(e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMIInformacion;
    private javax.swing.JMenuItem jMIAbrir;
    private javax.swing.JMenuItem jMICerrar;
    private javax.swing.JMenuItem jMIGuardar;
    private javax.swing.JMenuItem jMIIr;
    private javax.swing.JMenuItem jMINuevo;
    private javax.swing.JMenuItem jMIVerificar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTPArchivos;
    // End of variables declaration//GEN-END:variables

}
