package dominio;

import static dominio.validador.Validador.validarObligatorio;
import static dominio.validador.Validador.validarNoVacio;


public class Cliente {
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String email;

    public Cliente(String nombre, String apellido, String cedula, String direccion, String email) {
    	validarObligatorio(nombre, "Debe ingresar el nombre");
    	validarNoVacio(nombre, "El campo Nombre no debe ser vacio");
    	validarObligatorio(apellido, "Debe ingresar el apellido");
    	validarNoVacio(apellido, "El campo Apeelido no debe ser vacio");
    	validarObligatorio(cedula,"Debe ingresar la cedula");
    	validarNoVacio(cedula, "El campo Cedula no debe ser vacio");
    	validarObligatorio(direccion, "Debe ingresar la direccion");
    	validarNoVacio(direccion, "El campo Direccion no debe ser vacio");
    	validarObligatorio(email, "Debe ingresar el email");
    	validarNoVacio(email, "El campo Email no debe ser vacio");
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }
}
