package dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import static dominio.validador.Validador.validarObligatorio;
import static dominio.validador.Validador.validarPositivo;

public class Transaccion {
    private Estado estado;
    private Double valor;
    private LocalTime hora;
    private LocalDate fecha;
    private TipoTransaccion tipoTransaccion;

    public Transaccion( Double valor,TipoTransaccion tipoTransaccion) {
    	validarObligatorio(valor, "Debe ingresar un valor en la transaccion");
    	validarPositivo(valor, "Debe ingresar un valor positivo");
        this.valor = valor;
        this.hora = LocalTime.now();
        this.fecha = LocalDate.now();
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public void setEstado(Estado estado) {
		this.estado = estado;
	}

    public Estado getEstado() {
        return estado;
    }

    public Double getValor() {
        return valor;
    }

    public LocalTime getHora() {
        return hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }
}
