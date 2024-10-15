package Controlador;

import Modelo.ProductosRepuestos;
import Modelo.Proveedores;
import Modelo.mdlInventario;
import Vistas.frmInventario;
import Vistas.frmProductosRepuestos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlInventario implements MouseListener {

    private mdlInventario Modelo;
    private frmInventario Vista;
    private ProductosRepuestos mProductosRepuestos;
    private Proveedores mProveedores;

    public ctrlInventario(mdlInventario Modelo, frmInventario Vista, ProductosRepuestos mProductosRepuestos, Proveedores mProveedores) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.mProductosRepuestos = mProductosRepuestos;
        this.mProveedores = mProveedores;

        Vista.btnGuardarInventario.addMouseListener(this);
        Vista.btnBorrarInventario.addMouseListener(this);
        Vista.btnActualizarInventario.addMouseListener(this);
        Vista.btnBuscarInventario.addMouseListener(this);
        Vista.btnLimpiarInventario.addMouseListener(this);
        Vista.btnProductosRepuestos.addMouseListener(this);
        Vista.tbInventario.addMouseListener(this);

        // Carga el contenido de los combo box
        this.mProductosRepuestos.CargarComboCategorias(Vista.cmbProdcutosRepuestos);
        Vista.cmbProdcutosRepuestos.addActionListener(e -> {
            if (e.getSource() == Vista.cmbProdcutosRepuestos) {
                ProductosRepuestos selectedItem = (ProductosRepuestos) Vista.cmbProdcutosRepuestos.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getUUID_ProductoRepuesto();
                    mProductosRepuestos.setUUID_ProductoRepuesto(uuid);
                }
            }
        });

        // Carga el contenido de los combo box para proveedores
        this.mProveedores.CargarComboCategorias(Vista.cmbProveedores);
        Vista.cmbProveedores.addActionListener(e -> {
            if (e.getSource() == Vista.cmbProveedores) {
                Proveedores selectedItem = (Proveedores) Vista.cmbProveedores.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getCodigo();
                    mProveedores.setCodgio(uuid);
                }
            }
        });

        Modelo.mostrarInventario(Vista.tbInventario);
        Modelo.limpiarCampos(Vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnGuardarInventario) {
            manejarGuardarInventario();
        } else if (e.getSource() == Vista.btnActualizarInventario) {
            manejarActualizarInventario();
        } else if (e.getSource() == Vista.btnBorrarInventario) {
            manejarBorrarInventario();
        } else if (e.getSource() == Vista.btnLimpiarInventario) {
            Modelo.limpiarCampos(Vista);
        } else if (e.getSource() == Vista.tbInventario) {
            Modelo.cargarDatosEnVista(Vista);
        } else if (e.getSource() == Vista.btnProductosRepuestos) {
            frmProductosRepuestos nuevoProductosRepuestosFrame = new frmProductosRepuestos();
            nuevoProductosRepuestosFrame.setVisible(true);
            nuevoProductosRepuestosFrame.setLocationRelativeTo(null);
        }
        System.out.println("El componente ha sido clickeado."); // Mueve esta línea aquí
    }

    private void manejarGuardarInventario() {
        if (!validarCampos()) return;

        try {
            System.out.println("Guardando Inventario...");

            ProductosRepuestos productoRepuestoSeleccionado = (ProductosRepuestos) Vista.cmbProdcutosRepuestos.getSelectedItem();
            Proveedores proveedorSeleccionado = (Proveedores) Vista.cmbProveedores.getSelectedItem();

            Modelo.setUUID_productoRepuesto(productoRepuestoSeleccionado.getUUID_ProductoRepuesto());
            Modelo.setCantidad(Integer.parseInt(Vista.txtCantidad.getText()));
            Modelo.setCodigo_proveedor(proveedorSeleccionado.getCodigo());
            Modelo.setFechaSuministro(Vista.txtFechaInventario.getText());

            Modelo.insertarInventario();
            Modelo.mostrarInventario(Vista.tbInventario);
            Modelo.limpiarCampos(Vista);

            System.out.println("Inventario guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el Inventario: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el Inventario: " + ex.getMessage());
        }
    }

    private void manejarActualizarInventario() {
        if (!validarCampos()) return;

        try {
            ProductosRepuestos productoRepuestoSeleccionado = (ProductosRepuestos) Vista.cmbProdcutosRepuestos.getSelectedItem();
            Proveedores proveedorSeleccionado = (Proveedores) Vista.cmbProveedores.getSelectedItem();

            Modelo.setUUID_productoRepuesto(productoRepuestoSeleccionado.getUUID_ProductoRepuesto());
            Modelo.setCodigo_proveedor(proveedorSeleccionado.getCodigo());
            Modelo.setCantidad(Integer.parseInt(Vista.txtCantidad.getText()));
            Modelo.setFechaSuministro(Vista.txtFechaInventario.getText());

            Modelo.actualizarInventario(Vista.tbInventario);
            Modelo.mostrarInventario(Vista.tbInventario);
            Modelo.limpiarCampos(Vista);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al actualizar el Inventario", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void manejarBorrarInventario() {
        if (!validarCampos()) return;

        Modelo.eliminarInventario(Vista.tbInventario);
        Modelo.mostrarInventario(Vista.tbInventario);
        Modelo.limpiarCampos(Vista);
    }

    private boolean validarCampos() {
        if (Vista.txtCantidad.getText().isEmpty() || Vista.cmbProdcutosRepuestos.getSelectedItem() == null
                || Vista.txtFechaInventario.getText().isEmpty() || Vista.cmbProveedores.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Aquí puedes agregar la lógica que desees para cuando el mouse es presionado
        System.out.println("El mouse ha sido presionado.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Aquí puedes agregar la lógica que desees para cuando el mouse es liberado
        System.out.println("El mouse ha sido liberado.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Aquí puedes agregar la lógica que desees para cuando el mouse entra en el componente
        System.out.println("El mouse ha entrado en el componente.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Aquí puedes agregar la lógica que desees para cuando el mouse sale del componente
        System.out.println("El mouse ha salido del componente.");
    }
}

