package main;

import Vistas.dashboard;
import Vistas.frmProveedor;
import Modelo.mdlProveedor;
import Controlador.ctrlProveedor;
import Vistas.frmRegistrarse;
import Vistas.frmRegistroParte2;
import com.formdev.flatlaf.FlatLightLaf;


public class main {

    
    public static void main(String[] args) {
        FlatLightLaf.install();

        java.awt.EventQueue.invokeLater(() -> {
           frmRegistrarse.initfrmRegistrarse();
        });
        
        
        
    }
}
