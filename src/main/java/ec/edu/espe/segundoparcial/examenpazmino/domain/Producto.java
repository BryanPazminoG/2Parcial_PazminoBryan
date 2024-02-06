package ec.edu.espe.segundoparcial.examenpazmino.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Document(collection = "Productos")
public class Producto {

    @Id
    private String id;

    private String codigoUnicoProducto;
    private String rucEmpresa;
    private String descripcion;
    private Float precio;

    private List<Comentario> comentarios;

    @Version
    private Long version;

}
