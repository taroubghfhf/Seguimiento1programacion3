package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dominio.validador.Validador.validarObligatorio;
import static dominio.validador.Validador.validarNoVacio;

public class Cuenta {
	private String numero;
	private TipoCuenta tipoCuenta;
	private Double saldo;
	private Cliente cliente;
	private List<Transaccion> transacciones;

	public Cuenta(String numero, TipoCuenta tipoCuenta, Double saldo, Optional<Cliente> cliente) {
		validarObligatorio(numero, "Debe ingresar el numero de cuenta");
		validarNoVacio(numero, "El numero es invalido");
		validarObligatorio(saldo, "Debe ingresar un valor en saldo");
		validarObligatorio(cliente, "Debe ingresar un cliente valido");
		if (!cliente.isPresent()) {
			throw new RuntimeException("Debe buscar un cliente valido");
		}
		this.numero = numero;
		this.tipoCuenta = tipoCuenta;
		this.saldo = saldo;
		this.cliente = cliente.get();
		this.transacciones = new ArrayList<Transaccion>();
	}

	public String agregarConsignacion(Transaccion transaccion) {
		transaccion.setEstado(Estado.EXITOSA);
		transacciones.add(transaccion);
		calcularSaldo();
		return mensaje(transaccion);
	}

	public String retiro(Transaccion transaccion) {
		if (calcularSaldo() >= transaccion.getValor()) {
			transacciones.add(transaccion);
			transaccion.setEstado(Estado.EXITOSA);
			calcularSaldo();
		} else {
			transaccion.setEstado(Estado.SIN_FONDO);
			calcularSaldo();
		}
		return mensaje(transaccion);
	}

	public double calcularSaldo() {
		double consignacion = transacciones.stream().filter(t -> t.getTipoTransaccion() == TipoTransaccion.CONSIGNACION)
				.mapToDouble(t -> t.getValor()).sum();
		double retiro = transacciones.stream().filter(t -> t.getTipoTransaccion() == TipoTransaccion.RETIRO)
				.mapToDouble(t -> t.getValor()).sum();
		return  (saldo + consignacion) - retiro;
	}

	public String mensaje(Transaccion transaccion) {
		return "Transacción: " + transaccion.getTipoTransaccion() + "\n" + "Estado: " + transaccion.getEstado() + "\n"
				+ "Fecha: " + transaccion.getFecha() + "\n" + "Hora: " + transaccion.getHora() + "\n" + "Cliente: "
				+ cliente.getNombre() + "\n" + "Numero cuenta: " + numero;
	}

	public String getNumero() {
		return numero;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}
}
