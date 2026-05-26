package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class FormularioOrden extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCantidad;
	private JTable tblLineas;
	private JComboBox<String> cbClientes;
	private JComboBox<String> cbProductos;
	private JLabel lblSubtotalVal;
	private JLabel lblImpuestoVal;
	private JLabel lblTotalVal;
	private DefaultTableModel modeloLineas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioOrden dialog = new FormularioOrden();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioOrden() {
		setTitle("Nueva Orden de Compra");
		setBounds(100, 100, 600, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(20, 20, 60, 14);
		contentPanel.add(lblCliente);

		cbClientes = new JComboBox<String>();
		cbClientes.setBounds(80, 16, 250, 22);
		contentPanel.add(cbClientes);


		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(20, 60, 60, 14);
		contentPanel.add(lblProducto);

		cbProductos = new JComboBox<String>();
		cbProductos.setBounds(80, 56, 200, 22);
		contentPanel.add(cbProductos);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(290, 60, 60, 14);
		contentPanel.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(350, 57, 80, 20);
		contentPanel.add(txtCantidad);
		txtCantidad.setColumns(10);

		JButton btnAgregarLinea = new JButton("Agregar Línea");
		btnAgregarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String prodSeleccionado = (String) cbProductos.getSelectedItem();

				    int codProd = Integer.parseInt(prodSeleccionado.split(" - ")[0]);

				    logica.Producto p = control.WallRose.getInstancia().obtenerProducto(codProd);
				    
				    float cantidad = Float.parseFloat(txtCantidad.getText());
				    float totalLinea = cantidad * p.getPrecio();

				    modeloLineas.addRow(new Object[]{prodSeleccionado, cantidad, p.getPrecio(), totalLinea});

				    actualizarTotales();

				    txtCantidad.setText("");

				} catch (NumberFormatException ex) {
				    javax.swing.JOptionPane.showMessageDialog(null, "Por favor ingrese una cantidad numérica válida.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException ex) {
				    javax.swing.JOptionPane.showMessageDialog(null, "No hay productos disponibles para seleccionar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAgregarLinea.setBounds(440, 56, 120, 22);
		contentPanel.add(btnAgregarLinea);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 100, 540, 180);
		contentPanel.add(scrollPane);

		tblLineas = new JTable();
		modeloLineas = new DefaultTableModel();
		modeloLineas.addColumn("Producto");
		modeloLineas.addColumn("Cantidad");
		modeloLineas.addColumn("Precio Unit.");
		modeloLineas.addColumn("Total");
		tblLineas.setModel(modeloLineas);
		scrollPane.setViewportView(tblLineas);


		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(380, 290, 80, 14);
		contentPanel.add(lblSubtotal);

		lblSubtotalVal = new JLabel("0.0");
		lblSubtotalVal.setBounds(480, 290, 80, 14);
		contentPanel.add(lblSubtotalVal);

		JLabel lblImpuesto = new JLabel("Impuesto (13%):");
		lblImpuesto.setBounds(380, 310, 100, 14);
		contentPanel.add(lblImpuesto);

		lblImpuestoVal = new JLabel("0.0");
		lblImpuestoVal.setBounds(480, 310, 80, 14);
		contentPanel.add(lblImpuestoVal);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(380, 330, 80, 14);
		contentPanel.add(lblTotal);

		lblTotalVal = new JLabel("0.0");
		lblTotalVal.setBounds(480, 330, 80, 14);
		contentPanel.add(lblTotalVal);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar Orden");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	
				    if (modeloLineas.getRowCount() == 0) {
				        javax.swing.JOptionPane.showMessageDialog(null, "La orden debe tener al menos un producto agregado.");
				        return;
				    }

				    String clienteSel = (String) cbClientes.getSelectedItem();
				    String idCliente = clienteSel.split(" - ")[0];

				    int numOrden = control.WallRose.getInstancia().crearOrdenCompra(idCliente);

				    for (int i = 0; i < modeloLineas.getRowCount(); i++) {
				        String prodStr = (String) modeloLineas.getValueAt(i, 0);
				        int codProd = Integer.parseInt(prodStr.split(" - ")[0]);
				        float cantidad = (float) modeloLineas.getValueAt(i, 1);

				        control.WallRose.getInstancia().agregarLinea(numOrden, codProd, cantidad);
				    }

				    javax.swing.JOptionPane.showMessageDialog(null, "¡Orden #" + numOrden + " creada exitosamente!");
				    dispose();

				} catch (Exception ex) {
				    javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar la orden: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);

		cargarClientes();
		cargarProductos();
	}


	private void cargarClientes() {
		List<logica.Cliente> clientes = control.WallRose.getInstancia().obtenerClientes();
		for (logica.Cliente c : clientes) {
			cbClientes.addItem(c.getId() + " - " + c.getNombre());
		}
	}

	private void cargarProductos() {
		List<logica.Producto> productos = control.WallRose.getInstancia().obtenerProductos();
		for (logica.Producto p : productos) {
			cbProductos.addItem(p.getCodigo() + " - " + p.getNombre());
		}
	}
	private void actualizarTotales() {
	    float subtotal = 0;

	    for (int i = 0; i < modeloLineas.getRowCount(); i++) {
	        subtotal += (float) modeloLineas.getValueAt(i, 3);
	    }

	    float impuesto = subtotal * 0.13f;
	    float total = subtotal + impuesto;

	    lblSubtotalVal.setText(String.valueOf(subtotal));
	    lblImpuestoVal.setText(String.valueOf(impuesto));
	    lblTotalVal.setText(String.valueOf(total));
	}
}