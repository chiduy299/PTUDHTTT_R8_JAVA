package PTUDHTTT.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import PTUDHTTT.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
	 // CRUD
	@Query("{name : {$regex:?0}}")
	List<Product> findByName(String name);
	
	List<Product> findById(UUID id);

}
