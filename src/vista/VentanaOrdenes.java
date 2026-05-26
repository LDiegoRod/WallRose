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

public class VentanaOrdenes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaOrdenes dialog = new VentanaOrdenes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaOrdenes() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 274, 219);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JButton btnNewButton = new JButton("Nueva Orden");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FormularioOrden formulario = new FormularioOrden();
					formulario.setModal(true);
					formulario.setVisible(true);
					
					cargarOrdenes();
				}
			});
			btnNewButton.setBounds(308, 11, 103, 22);
			contentPanel.add(btnNewButton);
		}
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
		cargarOrdenes();
	}
	public void cargarOrdenes() {

	    java.util.List<logica.OrdenCompra> listaOrdenes = control.WallRose.getInstancia().obtenerOrdenes();

	    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
	    modelo.addColumn("Número");
	    modelo.addColumn("ID Cliente");
	    modelo.addColumn("Monto Total");

	    for (logica.OrdenCompra o : listaOrdenes) {
	        Object[] fila = new Object[3];
	        fila[0] = o.getNumero(); 
	        fila[1] = o.getCliente();
	        fila[2] = o.getMontoTotal(); 
	        modelo.addRow(fila);
	    }
 
	    table.setModel(modelo);
	}
}
