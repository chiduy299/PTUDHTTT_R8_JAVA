package PTUDHTTT.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import PTUDHTTT.model.Store;

public interface StoreRepository extends MongoRepository<Store, String>{
	 // CRUD
	
}
