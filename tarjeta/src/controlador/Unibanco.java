package controlador;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoTransaccion;
import dominio.Transaccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Unibanco {
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

    public Unibanco() {
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente){
        if(!buscarCliente(cliente.getCedula()).isPresent())
            clientes.add(cliente);
    }

    public Optional<Cliente> buscarCliente(String numero){
        return clientes.stream().filter(cliente -> cliente.getCedula().equals(numero)).findFirst();
    }

    public void agregarCuenta(Cuenta cuenta){
        if(!buscarCuenta(cuenta.getNumero()).isPresent()) {
        	cuentas.add(cuenta);
        } else {
        	throw new RuntimeException("La cuenta ya existe");
        }
    }

    public Optional<Cuenta> buscarCuenta(String numero){
        return cuentas.stream().filter(cuenta -> cuenta.getNumero().equals(numero)).findFirst();
    }
    
    public String transaccion(Transaccion transaccion, String numeroCuenta) {
    	Optional<Cuenta> cuenta = buscarCuenta(numeroCuenta);
    	if(!cuenta.isPresent()) {
    		throw new RuntimeException("El numero de cuenta no existe");
    	}
    	if(transaccion.getTipoTransaccion() == TipoTransaccion.CONSIGNACION) {
    		return cuenta.get().agregarConsignacion(transaccion);
    	}
    	if(transaccion.getTipoTransaccion() == TipoTransaccion.RETIRO) {
    		return cuenta.get().retiro(transaccion);
    	}else {
    		return "Saldo: "+cuenta.get().calcularSaldo();
    	}
    }
}
