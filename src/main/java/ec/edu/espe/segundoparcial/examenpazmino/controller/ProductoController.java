package ec.edu.espe.segundoparcial.examenpazmino.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examenpazmino.domain.Comentario;
import ec.edu.espe.segundoparcial.examenpazmino.domain.Producto;
import ec.edu.espe.segundoparcial.examenpazmino.service.ProductoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<List<Producto>> buscarPorRuc(@PathVariable(name = "ruc") String ruc) {
        log.info("Obteniendo producto con Ruc: {}", ruc);
        try {
            return ResponseEntity.ok(productoService.obtenerProductosPorRuc(ruc));
        } catch(RuntimeException rte) {
            log.error("Error al obtener empresa por ruc.", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigoUnico/{codigoProducto}")
    public ResponseEntity<Producto> buscarPorCodigoUnico(@PathVariable(name = "codigoProducto") String codigoUnicoProducto) {
        log.info("Obteniendo producto con codigo: {}", codigoUnicoProducto);
        try {
            return ResponseEntity.ok(productoService.obtenerProductoPorCodigo(codigoUnicoProducto));
        } catch(RuntimeException rte) {
            log.error("Error al obtener empresa por ruc.", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> agregarEmpresa(@RequestBody Producto producto) {
        try {
            log.info("Va a crear un producto: {}", producto);
            productoService.crear(producto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte){
            log.error("Error al crear un producto.", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/comentarios/{codigoProducto}")
    public ResponseEntity<Void> crearComentario(@PathVariable(name = "codigoProducto") String codigoProducto, @RequestBody Comentario comentarios) {
        try {
            log.info("Se va a crear un comentario al codigo producto: {}", codigoProducto);
            productoService.crearComentario(codigoProducto, comentarios);
            return ResponseEntity.noContent().build(); 
        } catch (RuntimeException rte) {
            log.error("Error al crear un comentario.", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/comentarios/{codigoProducto}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorCodigoProducto(@PathVariable String codigoProducto) {
        try {
            log.info("Se va a obtener los comentarios asociados al codigo: {}", codigoProducto);
            return ResponseEntity.ok(productoService.obtenerComentariosPorCodigoProducto(codigoProducto));
        } catch (Exception e) {
            log.error("Error al obtener comentarios del producto", e);
            return ResponseEntity.badRequest().build();
        }  
    }

}
