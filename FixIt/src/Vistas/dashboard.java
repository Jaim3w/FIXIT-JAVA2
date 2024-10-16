package Vistas;

import Modelo.globalVars;
import java.awt.Color;
import javax.swing.ImageIcon;

public class dashboard extends javax.swing.JFrame {


    public dashboard() {
        setUndecorated(true);

        // Inicializamos los componentes
        initComponents();
        
        // Aplicamos el borde redondeado al JFrame
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50)); // Bordes redondeados
        
        // Configuramos el color de fondo y el layout
        setBackground(new Color(0, 0, 0, 0)); // Fondo transparente


        // Instancia del JPanel form llamado "dashboardpanel"
        dashboardpanel panel = new dashboardpanel();
        
        // Establece el layout del jpCallDashboardPanel para que ocupe todo el espacio disponible
        jpCallDashboardPanel.setLayout(new java.awt.BorderLayout());
        
        // Añade el dashboardpanel al jpCallDashboardPanel
        jpCallDashboardPanel.add(panel);
        
        // Refresca el panel para asegurarte de que se muestre correctamente
        jpCallDashboardPanel.revalidate();
        jpCallDashboardPanel.repaint();
        
        String correoUsuario = globalVars.correoUsuario;
        System.out.println("El correo del usuario en sesión es: " + correoUsuario);
        
        setIconImage(new ImageIcon("C:\\Users\\pc\\Desktop\\FIXIT-JAVA2\\FixIt\\src\\Vistas\\Imagenes\\LogitoFixit.png").getImage());

        
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCallDashboardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));
        setResizable(false);

        javax.swing.GroupLayout jpCallDashboardPanelLayout = new javax.swing.GroupLayout(jpCallDashboardPanel);
        jpCallDashboardPanel.setLayout(jpCallDashboardPanelLayout);
        jpCallDashboardPanelLayout.setHorizontalGroup(
            jpCallDashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        jpCallDashboardPanelLayout.setVerticalGroup(
            jpCallDashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpCallDashboardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpCallDashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpCallDashboardPanel;
    // End of variables declaration//GEN-END:variables
}
