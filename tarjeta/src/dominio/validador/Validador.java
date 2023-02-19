package dominio.validador;

public class Validador {

	
	public static void validarObligatorio(Object object, String msj) {
		if(object == null) {
			throw new RuntimeException(msj);
		}
	}
	
	public static void validarNoVacio(String obj,String msj) {
		if(obj.trim().equals("")) {
			throw new RuntimeException(msj);
		}
	}
	
	public static void validarPositivo(Double obj,String msj) {
		if(obj<=0) {
			throw new RuntimeException(msj);
		}
	}
	
}
