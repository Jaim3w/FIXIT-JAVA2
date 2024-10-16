
package Controlador;


//import Vistas.frmReporte;
import Vistas.frmReporteFram;
import Modelo.Conexion;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ctrlReporte implements MouseListener{
        frmReporteFram Vista;
        
        
        public ctrlReporte(frmReporteFram vista) {

        this.Vista = vista;
        
        Vista.btnReporte.addMouseListener(this);
        Vista.btnCancelar.addMouseListener(this);
        
    }

    
    private void mostrarReporte(){
    
        try{
            
            Map<String, Object> parametros = new HashMap<>();
            
            parametros.put("identificador", Vista.txtNombre.getText());
            
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vistas/Factura_fixit.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report, parametros, Conexion.getConexion());
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setTitle("Nombre Reporte");
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
        
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(Vista, "No se pudo actualizar la cita", "Error", JOptionPane.WARNING_MESSAGE);
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnReporte) {
        mostrarReporte();
        
        if (e.getSource() == Vista.btnCancelar) {
            Vista.dispose();
        }
    }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
}
