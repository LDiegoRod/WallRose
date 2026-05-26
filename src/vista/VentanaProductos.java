package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaProductos dialog = new VentanaProductos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaProductos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 21, 253, 198);
		contentPanel.add(scrollPane);
		
		tblProductos = new JTable();
		scrollPane.setViewportView(tblProductos);
		
		JButton btnNewButton = new JButton("Nuevo Producto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FormularioProducto formulario = new FormularioProducto();
				formulario.setModal(true);
				formulario.setVisible(true);
				cargarProductos();
			}
		});
		btnNewButton.setBounds(288, 18, 138, 22);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        dispose(); 
				    }
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	public void cargarProductos() {

	    java.util.List<logica.Producto> listaProductos = control.WallRose.getInstancia().obtenerProductos();


	    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
	    modelo.addColumn("Código");
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Existencias");
	    modelo.addColumn("Unidad");
	    modelo.addColumn("Precio");


	    for (logica.Producto p : listaProductos) {
	        Object[] fila = new Object[5]; 
	        fila[0] = p.getCodigo();
	        fila[1] = p.getNombre();
	        fila[2] = p.getExistencias();
	        fila[3] = p.getUnidad();
	        fila[4] = p.getPrecio();
	        modelo.addRow(fila); 
	    }

	    tblProductos.setModel(modelo);
	}
}
