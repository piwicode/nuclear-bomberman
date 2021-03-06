/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bomber;

import com.google.common.collect.Lists;
import java.awt.Image;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import space.app.common.LocationHandler;

/**
 *
 * @author pierre
 */
public class ImageFrame extends javax.swing.JFrame {

    private final List<String> sources = Lists.newArrayList();
    private final LocationHandler locHandler;
    /**
     * Creates new form ImageFrame
     */
    public ImageFrame() {
        initComponents();
        locHandler=new LocationHandler(this);
        locHandler.load();
    }
    private static final Future<ImageFrame> theFrame;

    static {
        FutureTask<ImageFrame> task = new FutureTask<>(new Callable<ImageFrame>() {

            @Override
            public ImageFrame call() throws Exception {
                return new ImageFrame();
            }
        });
        SwingUtilities.invokeLater(task);
        theFrame = task;
    }

    public static void setText(final String animTitle, final String toString) {
        try {
            final ImageFrame frame = theFrame.get();            
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    frame.tabForSource(animTitle).setText(toString);
                }
            });

        } catch (InterruptedException ex) {
            Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addImage(final String sourceName, final String name, final Image image) {
        try {
            final ImageFrame frame = theFrame.get();
            
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    frame.setVisible(true);
                    frame.tabForSource(sourceName).addImage(name, image);
                }
            });

        } catch (InterruptedException ex) {
            Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 800));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItem1.setText("Save Spritesheet");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem2.setText("Help");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("About");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    private ImagePanel tabForSource(String sourceName) {
        int lastIndexOf = sources.lastIndexOf(sourceName);
        if (lastIndexOf >= 0) {
            return (ImagePanel) jTabbedPane1.getComponentAt(lastIndexOf);
        }
        sources.add(sourceName);
        final ImagePanel imagePanel = new ImagePanel();
        jTabbedPane1.addTab(sourceName, imagePanel);
        return imagePanel;

    }
}
