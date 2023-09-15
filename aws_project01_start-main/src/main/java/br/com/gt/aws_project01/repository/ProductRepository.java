package br.com.gt.aws_project01.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gt.aws_project01.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	Optional<Product> findByCode(String code);
}
