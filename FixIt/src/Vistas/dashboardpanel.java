package Vistas;

import Funciones.DarkLightSwitchIcon;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class dashboardpanel extends javax.swing.JPanel {

  
    public dashboardpanel() {

         // Inicializamos los componentes
    initComponents();        

    // Configuramos el color de fondo y el layout
    setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    
     // Instalar el LookAndFeel FlatIntelliJLaf por defecto
        try {
            FlatIntelliJLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar un estilo de fuente minimalista
        Font minimalFont = new Font("SansSerif", Font.PLAIN, 14); // Fuente sencilla y moderna
        UIManager.put("defaultFont", minimalFont); // Aplicar la fuente a todos los componentes

    // Configuramos el estilo del jtSwitch
    jtSwitch.putClientProperty(FlatClientProperties.STYLE, ""
            + "arc:999;"
            + "borderWidth:0;"
            + "focusWidth:0;"
            + "innerFocusWidth:0");

    // Asignar el icono animado
    DarkLightSwitchIcon switchIcon = new DarkLightSwitchIcon();
    jtSwitch.setIcon(switchIcon);

    // Listener para cambiar de tema con retardo
    jtSwitch.addActionListener(new ActionListener() {

        private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
        private ScheduledFuture<?> scheduledFuture;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            scheduledFuture = scheduled.schedule(() -> {
                changeThemes(jtSwitch.isSelected());
            }, 500, TimeUnit.MILLISECONDS);
        }
    });
    }
    
private void changeThemes(boolean dark) {
    if (FlatLaf.isLafDark() != dark) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            if (!dark) {
                // Configura el tema claro
                FlatIntelliJLaf.setup();

                // Colores para el tema claro
                UIManager.put("Component.accentColor", new Color(255, 223, 0)); // Color amarillo
                UIManager.put("Table.alternateRowColor", new Color(255, 255, 224)); // Amarillo pálido
                UIManager.put("Table.selectionBackground", new Color(255, 223, 0)); // Fondo amarillo en selección
                UIManager.put("Table.selectionForeground", Color.BLACK); // Texto negro en selección
                UIManager.put("Component.unselectedForeground", new Color(169, 169, 169)); // Texto gris cuando no está seleccionado
                UIManager.put("Component.selectionBackground", new Color(255, 223, 0, 128)); // Fondo amarillo opaco en selección
                
                                UIManager.put("Label.foreground", Color.BLACK); // Texto de JLabel blanco en tema oscuro


                // Cambiar color de borde de enfoque y seleccionado
                UIManager.put("Component.focusedBorderColor", new Color(255, 223, 0, 128)); // Borde de enfoque amarillo opaco
                UIManager.put("Component.selectedBorderColor", new Color(255, 223, 0, 128)); // Borde seleccionado amarillo opaco

            } else {
                // Configura el tema oscuro
                FlatDarculaLaf.setup();

                // Colores para el tema oscuro
                UIManager.put("Component.accentColor", new Color(255, 223, 0)); // Color amarillo
                UIManager.put("Table.alternateRowColor", new Color(50, 50, 50)); // Un color oscuro para alternar
                UIManager.put("Table.selectionBackground", new Color(255, 223, 0)); // Fondo amarillo en selección
                UIManager.put("Table.selectionForeground", Color.WHITE); // Texto blanco en selección
                UIManager.put("Component.unselectedForeground", new Color(200, 200, 200)); // Texto gris claro cuando no está seleccionado
                UIManager.put("Component.selectionBackground", new Color(255, 223, 0, 128)); // Fondo amarillo opaco en selección

                // Cambiar color de borde de enfoque y seleccionado
                UIManager.put("Component.focusedBorderColor", new Color(255, 223, 0, 128)); // Borde de enfoque amarillo opaco
                UIManager.put("Component.selectedBorderColor", new Color(255, 223, 0, 128)); // Borde seleccionado amarillo opaco

                // Asegurar que todos los JLabel sean blancos en el tema oscuro
                UIManager.put("Label.foreground", Color.WHITE); // Texto de JLabel blanco en tema oscuro
            }

            FlatLaf.updateUI();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
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

        whiteRoundedPanelF1 = new Vistas.elementosTwo.WhiteRoundedPanelF();
        transparenteRoundedPanel1 = new Vistas.elementosTwo.TransparenteRoundedPanel();
        lbl_inicio = new javax.swing.JLabel();
        lbl_carros = new javax.swing.JLabel();
        lbl_citas = new javax.swing.JLabel();
        lbl_facturas = new javax.swing.JLabel();
        lbl_proveedores = new javax.swing.JLabel();
        lbl_ventas = new javax.swing.JLabel();
        lbl_usuarios_y_empleados = new javax.swing.JLabel();
        panelContent = new Vistas.elementosTwo.TransparenteRoundedPanel();
        transparenteRoundedPanel2 = new Vistas.elementosTwo.TransparenteRoundedPanel();
        imgMinimize = new javax.swing.JLabel();
        imgExit = new javax.swing.JLabel();
        lbl_perfil = new javax.swing.JLabel();
        jtSwitch = new javax.swing.JToggleButton();
        imgFondod = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        whiteRoundedPanelF1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_inicio.setBackground(new java.awt.Color(255, 255, 255));
        lbl_inicio.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_inicio.setForeground(new java.awt.Color(0, 27, 59));
        lbl_inicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/ichomew.png"))); // NOI18N
        lbl_inicio.setText("Inicio");
        lbl_inicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_inicio.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_inicio.setIconTextGap(40);
        lbl_inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_inicioMouseClicked(evt);
            }
        });

        lbl_carros.setBackground(new java.awt.Color(0, 0, 0));
        lbl_carros.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_carros.setForeground(new java.awt.Color(0, 27, 59));
        lbl_carros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_carros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iccarw.png"))); // NOI18N
        lbl_carros.setText("Carros");
        lbl_carros.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_carros.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_carros.setIconTextGap(40);
        lbl_carros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_carrosMouseClicked(evt);
            }
        });

        lbl_citas.setBackground(new java.awt.Color(255, 255, 255));
        lbl_citas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_citas.setForeground(new java.awt.Color(0, 27, 59));
        lbl_citas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_citas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iccitasw.png"))); // NOI18N
        lbl_citas.setText("Citas");
        lbl_citas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_citas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_citas.setIconTextGap(40);
        lbl_citas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_citasMouseClicked(evt);
            }
        });

        lbl_facturas.setBackground(new java.awt.Color(255, 255, 255));
        lbl_facturas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_facturas.setForeground(new java.awt.Color(0, 27, 59));
        lbl_facturas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_facturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icfacturacionw.png"))); // NOI18N
        lbl_facturas.setText("Facturación");
        lbl_facturas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_facturas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_facturas.setIconTextGap(40);
        lbl_facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_facturasMouseClicked(evt);
            }
        });

        lbl_proveedores.setBackground(new java.awt.Color(255, 255, 255));
        lbl_proveedores.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_proveedores.setForeground(new java.awt.Color(0, 27, 59));
        lbl_proveedores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_proveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icproveedoresw.png"))); // NOI18N
        lbl_proveedores.setText("Proveedores");
        lbl_proveedores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_proveedores.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_proveedores.setIconTextGap(40);
        lbl_proveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_proveedoresMouseClicked(evt);
            }
        });

        lbl_ventas.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ventas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_ventas.setForeground(new java.awt.Color(0, 27, 59));
        lbl_ventas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icstadisticsw.png"))); // NOI18N
        lbl_ventas.setText("Ventas");
        lbl_ventas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_ventas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_ventas.setIconTextGap(40);
        lbl_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ventasMouseClicked(evt);
            }
        });

        lbl_usuarios_y_empleados.setBackground(new java.awt.Color(255, 255, 255));
        lbl_usuarios_y_empleados.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_usuarios_y_empleados.setForeground(new java.awt.Color(0, 27, 59));
        lbl_usuarios_y_empleados.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_usuarios_y_empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icusersw.png"))); // NOI18N
        lbl_usuarios_y_empleados.setText("Usuarios");
        lbl_usuarios_y_empleados.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_usuarios_y_empleados.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_usuarios_y_empleados.setIconTextGap(40);
        lbl_usuarios_y_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_usuarios_y_empleadosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transparenteRoundedPanel1Layout = new javax.swing.GroupLayout(transparenteRoundedPanel1);
        transparenteRoundedPanel1.setLayout(transparenteRoundedPanel1Layout);
        transparenteRoundedPanel1Layout.setHorizontalGroup(
            transparenteRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transparenteRoundedPanel1Layout.createSequentialGroup()
                .addGroup(transparenteRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbl_carros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_inicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_citas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_facturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_ventas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_usuarios_y_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        transparenteRoundedPanel1Layout.setVerticalGroup(
            transparenteRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transparenteRoundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_carros, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_citas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_facturas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(lbl_usuarios_y_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        whiteRoundedPanelF1.add(transparenteRoundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 290, 680));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        whiteRoundedPanelF1.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 1040, 670));

        imgMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icminimizew.png"))); // NOI18N
        imgMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMinimizeMouseClicked(evt);
            }
        });

        imgExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icexitw.png"))); // NOI18N
        imgExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgExitMouseClicked(evt);
            }
        });

        lbl_perfil.setBackground(new java.awt.Color(255, 255, 255));
        lbl_perfil.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_perfil.setForeground(new java.awt.Color(0, 27, 59));
        lbl_perfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icperfilw.png"))); // NOI18N
        lbl_perfil.setText("Inicio");
        lbl_perfil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        lbl_perfil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl_perfil.setIconTextGap(20);
        lbl_perfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_perfilMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transparenteRoundedPanel2Layout = new javax.swing.GroupLayout(transparenteRoundedPanel2);
        transparenteRoundedPanel2.setLayout(transparenteRoundedPanel2Layout);
        transparenteRoundedPanel2Layout.setHorizontalGroup(
            transparenteRoundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transparenteRoundedPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lbl_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(501, 501, 501)
                .addComponent(jtSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imgMinimize)
                .addGap(2, 2, 2)
                .addComponent(imgExit)
                .addGap(15, 15, 15))
        );
        transparenteRoundedPanel2Layout.setVerticalGroup(
            transparenteRoundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transparenteRoundedPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(transparenteRoundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgMinimize)
                    .addComponent(imgExit)
                    .addComponent(jtSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        whiteRoundedPanelF1.add(transparenteRoundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 1040, 140));

        imgFondod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/dashboardfondoblanco.png"))); // NOI18N
        whiteRoundedPanelF1.add(imgFondod, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(whiteRoundedPanelF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(whiteRoundedPanelF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_proveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_proveedoresMouseClicked
        frmProveedor pr = new frmProveedor();
    pr.setSize(1040, 670);
    pr.setLocation(0, 0);
        setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(pr, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_proveedoresMouseClicked

    private void lbl_inicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_inicioMouseClicked
        frmInicio in = new frmInicio();
    in.setSize(1040, 670);
    in.setLocation(0, 0);
    setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(in, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    
    }//GEN-LAST:event_lbl_inicioMouseClicked

    private void lbl_carrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_carrosMouseClicked
        frmCarros cr = new frmCarros();
    cr.setSize(1040, 670);
    cr.setLocation(0, 0);
     setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(cr, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_carrosMouseClicked

    private void lbl_citasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_citasMouseClicked
        frmCitas ct = new frmCitas();
    ct.setSize(1040, 670);
    ct.setLocation(0, 0);
     setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(ct, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_citasMouseClicked

    private void lbl_facturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_facturasMouseClicked
    frmFacturacion fr = new frmFacturacion();
    fr.setSize(1040, 670);
    fr.setLocation(0, 0);
     setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(fr, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_facturasMouseClicked

    private void lbl_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ventasMouseClicked
        frmVentas vt = new frmVentas();
    vt.setSize(1040, 670);
    vt.setLocation(0, 0);
     setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(vt, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_ventasMouseClicked

    private void lbl_usuarios_y_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_usuarios_y_empleadosMouseClicked
        frmUsuarios us = new frmUsuarios();
    us.setSize(1040, 670);
    us.setLocation(0, 0);
     setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(us, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_usuarios_y_empleadosMouseClicked

    private void imgMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMinimizeMouseClicked
           JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtiene el JFrame que contiene el JPanel
    if (frame != null) {
        frame.setState(JFrame.ICONIFIED);  // Minimiza el JFrame
    }
    }//GEN-LAST:event_imgMinimizeMouseClicked

    private void imgExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgExitMouseClicked
        System.exit(0);  // Cierra la aplicación por completo
    }//GEN-LAST:event_imgExitMouseClicked

    private void lbl_perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_perfilMouseClicked
          frmPerfilAd pa = new frmPerfilAd();
    pa.setSize(1040, 670);
    pa.setLocation(0, 0);
     setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
    panelContent.removeAll();
    panelContent.add(pa, BorderLayout.CENTER);
    panelContent.revalidate();
    panelContent.repaint();
    }//GEN-LAST:event_lbl_perfilMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel imgExit;
    private javax.swing.JLabel imgFondod;
    public javax.swing.JLabel imgMinimize;
    private javax.swing.JToggleButton jtSwitch;
    public javax.swing.JLabel lbl_carros;
    public javax.swing.JLabel lbl_citas;
    public javax.swing.JLabel lbl_facturas;
    public javax.swing.JLabel lbl_inicio;
    public javax.swing.JLabel lbl_perfil;
    public javax.swing.JLabel lbl_proveedores;
    public javax.swing.JLabel lbl_usuarios_y_empleados;
    public javax.swing.JLabel lbl_ventas;
    public Vistas.elementosTwo.TransparenteRoundedPanel panelContent;
    private Vistas.elementosTwo.TransparenteRoundedPanel transparenteRoundedPanel1;
    private Vistas.elementosTwo.TransparenteRoundedPanel transparenteRoundedPanel2;
    private Vistas.elementosTwo.WhiteRoundedPanelF whiteRoundedPanelF1;
    // End of variables declaration//GEN-END:variables
}