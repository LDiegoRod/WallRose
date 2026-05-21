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

public class VentanaClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaClientes dialog = new VentanaClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaClientes() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 261, 175);
		contentPanel.add(scrollPane);
		
		tblClientes = new JTable();
		scrollPane.setViewportView(tblClientes);
		
		JButton btnNuevoCliente = new JButton("Nuevo Cliente");
		btnNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioCliente formulario = new FormularioCliente();

				formulario.setModal(true); 
				formulario.setVisible(true);
				cargarClientes(); 
			}
		});
		btnNuevoCliente.setBounds(93, 197, 118, 22);
		contentPanel.add(btnNuevoCliente);
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
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarClientes();
	}
	
	public void cargarClientes() {
	    java.util.List<logica.Cliente> listaClientes = control.WallRose.getInstancia().obtenerClientes();

	    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
	    modelo.addColumn("ID");
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Email");

	    for (logica.Cliente c : listaClientes) {
	        Object[] fila = new Object[3];
	        fila[0] = c.getId();
	        fila[1] = c.getNombre();
	        fila[2] = c.getEmail();
	        modelo.addRow(fila); 
	    }

	    tblClientes.setModel(modelo);
	}
}