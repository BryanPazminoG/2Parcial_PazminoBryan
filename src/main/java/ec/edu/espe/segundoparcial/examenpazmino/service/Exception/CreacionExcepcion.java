package ec.edu.espe.segundoparcial.examenpazmino.service.Exception;

public class CreacionExcepcion extends RuntimeException {
    public CreacionExcepcion(String message){
        super(message);
    }
    public CreacionExcepcion(String message, Exception cause){
        super(message, cause);
    }

}
