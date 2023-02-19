package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Unibanco;
import dominio.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaCrearCliente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Unibanco unibanco;
	private JPanel contentPane;
	private JTextField txNombre;
	private JTextField txApellido;
	private JTextField txCedula;
	private JTextField txDireccion;
	private JTextField txEmail;
	private JButton btnCrearCliente;
	private JButton btnLimpiarCampos;


	public VentanaCrearCliente(Unibanco unibanco){
		init();
		this.unibanco = unibanco;
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 394, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 36, 56, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Crear Cliente");
		lblNewLabel.setBounds(162, 8, 122, 14);
		panel.add(lblNewLabel);
		
		txNombre = new JTextField();
		txNombre.setBounds(76, 33, 292, 20);
		panel.add(txNombre);
		txNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(10, 67, 56, 14);
		panel.add(lblNewLabel_2);
		
		txApellido = new JTextField();
		txApellido.setBounds(76, 64, 292, 20);
		panel.add(txApellido);
		txApellido.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cedula:");
		lblNewLabel_3.setBounds(10, 101, 56, 14);
		panel.add(lblNewLabel_3);
		
		txCedula = new JTextField();
		txCedula.setBounds(76, 95, 292, 20);
		panel.add(txCedula);
		txCedula.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_4.setBounds(10, 137, 63, 14);
		panel.add(lblNewLabel_4);
		
		txDireccion = new JTextField();
		txDireccion.setBounds(76, 134, 292, 20);
		panel.add(txDireccion);
		txDireccion.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setBounds(10, 180, 56, 14);
		panel.add(lblNewLabel_5);
		
		txEmail = new JTextField();
		txEmail.setBounds(76, 177, 292, 20);
		panel.add(txEmail);
		txEmail.setColumns(10);
		
		btnCrearCliente = new JButton("Crear Cliente ");
		btnCrearCliente.setBounds(227, 227, 135, 23);
		panel.add(btnCrearCliente);
		btnCrearCliente.addActionListener(this);
		
		btnLimpiarCampos = new JButton("Limpiar campos ");
		btnLimpiarCampos.setBounds(35, 227, 135, 23);
		panel.add(btnLimpiarCampos);
		btnLimpiarCampos.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCrearCliente) {
			crearCliente();
		}
		if(e.getSource() == btnLimpiarCampos) {
			limpiarCampos();
		}
	}
	
	public void crearCliente() {
		String nombre = txNombre.getText();
		String apellido = txApellido.getText();
		String cedula = txCedula.getText();
		String direccion = txDireccion.getText();
		String email = txEmail.getText();
		try {
			Cliente cliente = new Cliente(nombre, apellido, cedula, direccion, email);
			unibanco.agregarCliente(cliente);
			limpiarCampos();
			JOptionPane.showMessageDialog(null, "!Usuario creado!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void limpiarCampos() {
		txNombre.setText("");
		txApellido.setText("");
		txCedula.setText("");
		txDireccion.setText("");
		txEmail.setText("");
	}
}
