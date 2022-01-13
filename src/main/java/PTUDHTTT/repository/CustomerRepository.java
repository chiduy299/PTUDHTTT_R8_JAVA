package PTUDHTTT.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import PTUDHTTT.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	 // CRUD
	
}
