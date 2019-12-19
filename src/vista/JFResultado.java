/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorDeArchivos;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Pedro
 */
public class JFResultado extends javax.swing.JFrame {

    JTextPane ep;

    /**
     * Creates new form JFResultado
     *
     * @param ep
     */
    public JFResultado(JTextPane ep) {
        initComponents();
        this.setTitle("Resultados");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.ep = ep;

        this.ejecutarComprobación(this.ep);
    }

    private void ejecutarComprobación(JEditorPane ep) {
        List<String> codigo = Arrays.asList(ep.getText().split("\\n"));

        new ControladorDeArchivos().verificar(this.jTPResultados, codigo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTPResultados = new javax.swing.JTextPane();
        jBCerrar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMIGuardarResultado = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMIClose = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jTPResultados.setEditable(false);
        jScrollPane1.setViewportView(jTPResultados);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBCerrar.setText("Cerrar");
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMIGuardarResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/save.png"))); // NOI18N
        jMIGuardarResultado.setText("Guardar resultado");
        jMIGuardarResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGuardarResultadoActionPerformed(evt);
            }
        });
        jMenu1.add(jMIGuardarResultado);
        jMenu1.add(jSeparator1);

        jMIClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/close.png"))); // NOI18N
        jMIClose.setText("Salir");
        jMIClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICloseActionPerformed(evt);
            }
        });
        jMenu1.add(jMIClose);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Navegación");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/goto.png"))); // NOI18N
        jMenuItem1.setText("Ir a...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(365, Short.MAX_VALUE)
                .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(508, Short.MAX_VALUE)
                .addComponent(jBCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIGuardarResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGuardarResultadoActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
        chooser.setFileFilter(filter);
        int seleccion = chooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            File file = archivo.getAbsolutePath().endsWith(".txt")
                    ? new File(archivo.getAbsolutePath())
                    : new File(archivo.getAbsolutePath() + ".txt");
            new ControladorDeArchivos().guardar(this.jTPResultados.getText(), file);
        }
    }//GEN-LAST:event_jMIGuardarResultadoActionPerformed

    private void jMICloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMICloseActionPerformed
        this.jBCerrarActionPerformed(evt);
    }//GEN-LAST:event_jMICloseActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new GoTo(this, true, ep).setVisible(true);
        ep.requestFocus();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCerrar;
    private javax.swing.JMenuItem jMIClose;
    private javax.swing.JMenuItem jMIGuardarResultado;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextPane jTPResultados;
    // End of variables declaration//GEN-END:variables
}
