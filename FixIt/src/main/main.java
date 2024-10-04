package main;

import Vistas.Loginjava;
import Vistas.frmRegistrarse;
import Vistas.pantallainicio;
import com.formdev.flatlaf.FlatLightLaf;


public class main {

    
    public static void main(String[] args) {
        FlatLightLaf.install();

        java.awt.EventQueue.invokeLater(() -> {
            frmRegistrarse.initfrmRegistrarse();
        });
        
        
    }
}
