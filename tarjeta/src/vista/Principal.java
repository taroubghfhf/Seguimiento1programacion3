package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.Unibanco;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Unibanco unibanco;
	private JButton btnCrearCliente;
	private JButton btnCrearCuenta;
	private JButton btnTransacciones; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		unibanco = new Unibanco();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 487, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnCrearCliente = new JButton("Crear cliente");
		btnCrearCliente.addActionListener(this);
		btnCrearCliente.setBounds(22, 180, 117, 29);
		panel.add(btnCrearCliente);
		
		JLabel lblNewLabel = new JLabel("UniBanco");
		lblNewLabel.setBounds(189, 84, 58, 16);
		panel.add(lblNewLabel);
		
		btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.addActionListener(this);
		btnCrearCuenta.setBounds(163, 180, 117, 29);
		panel.add(btnCrearCuenta);
		
		btnTransacciones = new JButton("Transacciones");
		btnTransacciones.addActionListener(this);
		btnTransacciones.setBounds(299, 180, 141, 29);
		panel.add(btnTransacciones);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCrearCliente) {
			abrirCrearCliente();
		}
		if(e.getSource()== btnCrearCuenta) {
			abrirCrearCuenta();
		}
		if(e.getSource()== btnTransacciones) {
		   abrirTransacciones();	
		}
	}
	
	public void abrirCrearCliente() {
		VentanaCrearCliente crearUsuario = new VentanaCrearCliente(unibanco);
		crearUsuario.setVisible(true);
		this.setVisible(false);
	}
	
	public void abrirCrearCuenta() {
		VentanaCrearCuenta crearCuenta = new VentanaCrearCuenta(unibanco);
		crearCuenta.setVisible(true);
		this.setVisible(false);
	}
	
	public void abrirTransacciones() {
		VentanaTransaccion transaccion = new VentanaTransaccion(unibanco);
		transaccion.setVisible(true);
		this.setVisible(false);
	}
	
	
}
