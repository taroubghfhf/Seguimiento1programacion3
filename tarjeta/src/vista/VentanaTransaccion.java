package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Unibanco;
import dominio.TipoTransaccion;
import dominio.Transaccion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VentanaTransaccion extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txNumeroCuenta;
	private JTextField txValor;
	private JButton btnTransaccion;
	private JButton btnLimpiarCampos;
	private JComboBox cbTipoTransaccion;
	private Unibanco unibanco;

	public VentanaTransaccion(Unibanco unibanco) {
		this.unibanco = unibanco;
		init();
	}

	public void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 256);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Numero de cuenta :");
		lblNewLabel_1.setBounds(10, 32, 113, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Transaccion");
		lblNewLabel.setBounds(180, 0, 71, 15);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel);

		txNumeroCuenta = new JTextField();
		txNumeroCuenta.setBounds(158, 29, 178, 20);
		panel.add(txNumeroCuenta);
		txNumeroCuenta.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Valor:");
		lblNewLabel_2.setBounds(10, 65, 46, 14);
		panel.add(lblNewLabel_2);

		txValor = new JTextField();
		txValor.setBounds(158, 62, 178, 20);
		panel.add(txValor);
		txValor.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tipo Transacci\u00F3n:");
		lblNewLabel_3.setBounds(10, 100, 113, 14);
		panel.add(lblNewLabel_3);

		cbTipoTransaccion = new JComboBox();
		cbTipoTransaccion.setModel(new DefaultComboBoxModel(new String[] { "CONSIGNACION", "RETIRO", "SALDO" }));
		cbTipoTransaccion.setBounds(158, 96, 130, 22);
		panel.add(cbTipoTransaccion);

		btnTransaccion = new JButton("Realizar Transaccion");
		btnTransaccion.addActionListener(this);
		btnTransaccion.setBounds(222, 164, 192, 23);
		panel.add(btnTransaccion);

		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.addActionListener(this);
		btnLimpiarCampos.setBounds(34, 164, 149, 23);
		panel.add(btnLimpiarCampos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiarCampos) {
			limpiarCampos();
		}
		if (e.getSource() == btnTransaccion) {
			transaccion();
		}

	}

	public void transaccion() {
		Double valor ;
		try {
			String numeroCuenta = txNumeroCuenta.getText();
			String tipoTransaccion = (String) cbTipoTransaccion.getSelectedItem();
			if(tipoTransaccion.equals("SALDO")) {
				valor = 1.0;
			}else {
				valor = Double.parseDouble(txValor.getText());
			}
			Transaccion transaccion = new Transaccion(valor, TipoTransaccion.valueOf(tipoTransaccion));
			String mensaje = unibanco.transaccion(transaccion, numeroCuenta);
			JOptionPane.showMessageDialog(null, mensaje);
			limpiarCampos();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void limpiarCampos() {
		txNumeroCuenta.setText("");
		txValor.setText("");
	}

}
