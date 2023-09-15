package br.com.gt.aws_project01.controller;

import java.util.Optional;

import br.com.gt.aws_project01.enums.EventType;
import br.com.gt.aws_project01.service.ProductPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gt.aws_project01.model.Product;
import br.com.gt.aws_project01.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductPublisher productPublisher;
	
	@GetMapping
	public Iterable<Product> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Product> obj = repository.findById(id);
		if(obj.isPresent()) {
			return new ResponseEntity<Product>(obj.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Product dto) {
		Product obj = repository.save(dto);
		productPublisher.publisherProductEvent(obj, EventType.PRODUCT_CREATED, "username 01");
		return new ResponseEntity<Product>(obj, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product dto, @PathVariable("id") Long id) {
		if(repository.existsById(id)) {
			dto.setId(id);
			Product obj = repository.save(dto);
			productPublisher.publisherProductEvent(obj, EventType.PRODUCT_UPDATE, "username 02");
			return new ResponseEntity<Product>(obj, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Optional<Product> obj = repository.findById(id);
		if(obj.isPresent()) {
			Product p = obj.get();
			repository.delete(p);
			productPublisher.publisherProductEvent(p, EventType.PRODUCT_DELETED, "username 03");
            return new ResponseEntity<Product>(obj.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("code")
	public ResponseEntity<?> findByCode(@RequestParam String code) {
		Optional<Product> obj = repository.findByCode(code);
		if(obj.isPresent()) {
			return new ResponseEntity<Product>(obj.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
