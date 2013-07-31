/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.syfaro.lwjgl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Greg
 */
public class UI extends javax.swing.JFrame {
    DefaultListModel listModel = new DefaultListModel();

    /**
     * Creates new form UI
     */
    public UI() {
        initComponents();
        jList1.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList(listModel);
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jProgressBar1.setFocusable(false);
        jProgressBar1.setStringPainted(true);

        jLabel1.setText("Current Status:");

        jLabel2.setText("Idle");

        jButton1.setText("Update LWJGL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel3.setText("Note:");

        jLabel4.setText("Do not change the");

        jLabel5.setText("version unless you");

        jLabel6.setText("are told to do so");

        jLabel7.setText("Default Value:");

        jLabel8.setText("loading");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        listModel.removeAllElements();
        
        Iterator it = Main.versions.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String key = (String) pairs.getKey();
            String value = (String) pairs.getValue();
            listModel.addElement(key);
        }
        
        jList1.setSelectedValue(Main.defaults.get(Main.shortOS), true);
        jLabel8.setText((String) Main.defaults.get(Main.shortOS));
    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Thread() {
            @Override
            public void run() {
                jButton1.setEnabled(false);
                
                String selectedVersion = (String) jList1.getSelectedValue();
                HashMap downloads;
                HashMap delete;
                HashMap rename;
                HashMap move;
                try {
                    HashMap<String, HashMap> functions = XMLLoader.loadActions(selectedVersion);
                    downloads = functions.get("download");
                    delete = functions.get("delete");
                    rename = functions.get("copy");
                    move = functions.get("move");
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                } catch (SAXException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }

                jProgressBar1.setMaximum(downloads.size()+delete.size()+rename.size()+move.size());
                jProgressBar1.setValue(0);

                Iterator it = downloads.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    String key = (String) pairs.getKey();
                    String value = (String) pairs.getValue();
                    jProgressBar1.setValue(jProgressBar1.getValue()+1);
                    jLabel2.setText("Downloading " + key);
                    UpdaterFunctions.DownloadFile(Main.downloadLocation + key, value);
                }

                it = rename.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    String key = (String) pairs.getKey();
                    String value = (String) pairs.getValue();
                    jProgressBar1.setValue(jProgressBar1.getValue()+1);
                    jLabel2.setText("Renaming " + key);
                    try {
                        UpdaterFunctions.CopyFile(Main.downloadLocation + key, value);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }

                it = move.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    String key = (String) pairs.getKey();
                    String value = (String) pairs.getValue();
                    jProgressBar1.setValue(jProgressBar1.getValue()+1);
                    jLabel2.setText("Moving " + key);
                    UpdaterFunctions.MoveFile(Main.downloadLocation + key, value);
                }

                it = delete.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    String key = (String) pairs.getKey();
                    jProgressBar1.setValue(jProgressBar1.getValue()+1);
                    jLabel2.setText("Deleting " + key);
                    UpdaterFunctions.DeleteFile(Main.downloadLocation + key);
                }
                
                jLabel2.setText("Finished!");
            }
        }.start();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}