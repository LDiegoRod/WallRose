package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtExistencias;
	private JTextField txtUnidad;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioProducto dialog = new FormularioProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioProducto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(39, 34, 48, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Existencias");
		lblNewLabel_1.setBounds(39, 92, 48, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(104, 31, 96, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtExistencias = new JTextField();
		txtExistencias.setBounds(104, 89, 96, 20);
		contentPanel.add(txtExistencias);
		txtExistencias.setColumns(10);
		
		txtUnidad = new JTextField();
		txtUnidad.setBounds(330, 31, 96, 20);
		contentPanel.add(txtUnidad);
		txtUnidad.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(330, 89, 96, 20);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Unidad");
		lblNewLabel_2.setBounds(268, 34, 48, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setBounds(268, 92, 48, 14);
		contentPanel.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {

						    String nombre = txtNombre.getText();
						    String unidad = txtUnidad.getText();
						    
						    float existencias = Float.parseFloat(txtExistencias.getText());
						    float precio = Float.parseFloat(txtPrecio.getText());

				
						    control.WallRose.getInstancia().crearProducto(nombre, existencias, unidad, precio);


						    control.WallRose.getInstancia().guardarDatos();

						    javax.swing.JOptionPane.showMessageDialog(null, "¡Producto guardado exitosamente!");
						    dispose(); 

						} catch (NumberFormatException ex) {
						    javax.swing.JOptionPane.showMessageDialog(null, "Por favor ingrese solo números válidos en Existencias y Precio.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
						} catch (IllegalArgumentException ex) {
						    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al guardar", javax.swing.JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        dispose(); 
				    }
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}