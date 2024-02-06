package ec.edu.espe.segundoparcial.examenpazmino.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.segundoparcial.examenpazmino.domain.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    public List<Producto> findByRucEmpresaOrderByDescripcion(String rucEmpresa);
    public Producto findByCodigoUnicoProducto(String codigoUnico);

}
