package Vistas;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;

public class pantallainicio extends javax.swing.JFrame {

        public static void initpantallainicio() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pantallainicio().setVisible(true);
            }
        });
    }

    /**
     * Creates new form pantallainicio
     */
    public pantallainicio() {
        // Establecemos el decorado de la ventana en false para poder personalizarla
        setUndecorated(true);
        
        // Configuramos FlatLaf como el LookAndFeel
        try {
            FlatLightLaf.setup(); // Usamos FlatLaf en su versión clara
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Inicializamos los componentes
        initComponents();
        
        // Aplicamos el borde redondeado al JFrame
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50)); // Bordes redondeados
        
        // Configuramos el color de fondo y el layout
        setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    }
    
    



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        whiteRoundedPanelF2 = new Vistas.elementosTwo.WhiteRoundedPanelF();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        whiteRoundedPanelF2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        whiteRoundedPanelF2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/l2.png"))); // NOI18N
        whiteRoundedPanelF2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(whiteRoundedPanelF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(whiteRoundedPanelF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private Vistas.elementosTwo.WhiteRoundedPanelF whiteRoundedPanelF2;
    // End of variables declaration//GEN-END:variables
}
