package ec.edu.espe.segundoparcial.examenpazmino.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examenpazmino.dao.ProductoRepository;
import ec.edu.espe.segundoparcial.examenpazmino.domain.Comentario;
import ec.edu.espe.segundoparcial.examenpazmino.domain.Producto;
import ec.edu.espe.segundoparcial.examenpazmino.service.Exception.CreacionExcepcion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerProductosPorRuc(String rucEmpresa){
        try {
            log.info("Se va a listar todos los productos por el ruc: {}", rucEmpresa);
            return productoRepository.findByRucEmpresaOrderByDescripcion(rucEmpresa);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al listar los productos de la empresa.", e);
        }
    }

    public Producto obtenerProductoPorCodigo(String codigoProducto){
        try {
            log.info("Se va a obtener un producto con el codigo: {}", codigoProducto);
            return productoRepository.findByCodigoUnicoProducto(codigoProducto);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al obtener el producto por id.", e);
        }
    }

    //Obtener cometnarios por su codigo unico

    @Transactional
    public void crear(Producto producto){
        try {
            productoRepository.save(producto);
            log.info("Se creo el producto: {}", producto);
        } catch (Exception e) {
            throw new CreacionExcepcion("Ocurrió un error al crear la empresa", e);
        }
    }


    public void crearComentario(String codigoProducto, Comentario comentario){
        try {
            Producto producto = productoRepository.findByCodigoUnicoProducto(codigoProducto);
            
            if(producto != null){
                List<Comentario> comentariosExistentes = producto.getComentarios();
                if (comentariosExistentes == null) {
                    comentariosExistentes = new ArrayList<>();
                }
                comentariosExistentes.add(comentario);
                producto.setComentarios(comentariosExistentes);
                productoRepository.save(producto);
                log.info("Se creo el comentario: {} al codigo producto: {}", comentario, codigoProducto);
            } else {
                throw new RuntimeException("Ocurrió un error al encontrar el producto.");
            }
        } catch (Exception e) {
            throw new CreacionExcepcion("Ocurrió un error al crear un comentario.", e);
        }
    }

    public List<Comentario> obtenerComentariosPorCodigoProducto(String codigoProducto) {
        try {
            Producto producto = productoRepository.findByCodigoUnicoProducto(codigoProducto);
    
            if (producto != null) {
                log.info("Obteniendo comentarios del producto asociado al codigo: {}", codigoProducto);
                return producto.getComentarios();
            } else {
                throw new RuntimeException("Ocurrió un error al encontrar el producto con el código único: " + codigoProducto);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al encontrar el producto.");
        }
    }
    



}
