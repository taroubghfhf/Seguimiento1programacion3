package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Unibanco;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VentanaCrearCuenta extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Unibanco unibanco;
	private JPanel contentPane;
	private JTextField txNumeroCuenta;
	private JTextField txSaldo;
	private JTextField txNumeroCliente;
	private JButton btnBuscarCliente;
	private JButton btnLimpiarCampos;
	private JButton btnCrearCuenta;
	private JLabel lbNombreValor;
	private JComboBox cbTipoCuenta;
	private Optional<Cliente> cliente;

	public VentanaCrearCuenta(Unibanco unibanco) {
		init();
		this.unibanco = unibanco;
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Numero:");
		lblNewLabel_1.setBounds(10, 33, 61, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Crear Cuenta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(178, 11, 119, 14);
		panel.add(lblNewLabel);

		txNumeroCuenta = new JTextField();
		txNumeroCuenta.setBounds(112, 30, 196, 20);
		panel.add(txNumeroCuenta);
		txNumeroCuenta.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tipo de cuenta:");
		lblNewLabel_2.setBounds(10, 58, 95, 14);
		panel.add(lblNewLabel_2);

		cbTipoCuenta = new JComboBox();
		cbTipoCuenta.setModel(new DefaultComboBoxModel(new String[] { "AHORRO", "CORRIENTE" }));
		cbTipoCuenta.setBounds(112, 54, 111, 22);
		panel.add(cbTipoCuenta);

		JLabel lblNewLabel_3 = new JLabel("Saldo:");
		lblNewLabel_3.setBounds(10, 92, 46, 14);
		panel.add(lblNewLabel_3);

		txSaldo = new JTextField();
		txSaldo.setBounds(112, 89, 153, 20);
		panel.add(txSaldo);
		txSaldo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cliente");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(177, 120, 46, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Numero:");
		lblNewLabel_5.setBounds(10, 145, 61, 14);
		panel.add(lblNewLabel_5);

		txNumeroCliente = new JTextField();
		txNumeroCliente.setBounds(112, 142, 132, 20);
		panel.add(txNumeroCliente);
		txNumeroCliente.setColumns(10);

		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setBounds(277, 141, 89, 23);
		panel.add(btnBuscarCliente);

		JLabel lblNewLabel_6 = new JLabel("Nombre:");
		lblNewLabel_6.setBounds(10, 180, 61, 14);
		panel.add(lblNewLabel_6);

		lbNombreValor = new JLabel("");
		lbNombreValor.setBounds(112, 180, 284, 14);
		panel.add(lbNombreValor);

		btnCrearCuenta = new JButton("Crear");
		btnCrearCuenta.addActionListener(this);
		btnCrearCuenta.setBounds(307, 227, 89, 23);
		panel.add(btnCrearCuenta);

		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.addActionListener(this);
		btnLimpiarCampos.setBounds(75, 227, 148, 23);
		panel.add(btnLimpiarCampos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarCliente) {
			buscarCliente();
		}
		if (e.getSource() == btnLimpiarCampos) {
			limpiarCampos();
		}
		if (e.getSource() == btnCrearCuenta) {
			crearCuenta();
		}
	}

	public void buscarCliente() {
		String numeroCliente = txNumeroCliente.getText();
		if (numeroCliente.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un numero de cliente valido");
		}
		cliente = unibanco.buscarCliente(numeroCliente);
		if (cliente.isPresent()) {
			lbNombreValor.setText(cliente.get().getNombre());
		} else {
			JOptionPane.showMessageDialog(null, "El cliente no existe");
			limpiarCamposCliente();
		}
	}
	
	public void crearCuenta() {
		String numero = txNumeroCuenta.getText();
		String tipoCuenta = (String)cbTipoCuenta.getSelectedItem();
		
		try {
			Double saldo = Double.parseDouble(txSaldo.getText());
			Cuenta cuenta = new Cuenta(numero, TipoCuenta.valueOf(tipoCuenta), saldo,cliente);
			unibanco.agregarCuenta(cuenta);
			JOptionPane.showMessageDialog(null, "Cuenta creada");
			limpiarCampos();
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese el valor de saldo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void limpiarCamposCliente() {
		txNumeroCliente.setText("");
		lbNombreValor.setText("");
	}

	public void limpiarCampos() {
		txNumeroCliente.setText("");
		lbNombreValor.setText("");
		txNumeroCuenta.setText("");
		txSaldo.setText("");
	}
}
