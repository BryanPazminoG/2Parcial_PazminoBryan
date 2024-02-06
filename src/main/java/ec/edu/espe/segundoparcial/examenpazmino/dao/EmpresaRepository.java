package ec.edu.espe.segundoparcial.examenpazmino.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.segundoparcial.examenpazmino.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>  {

    public Empresa findByRuc(String ruc);

}
