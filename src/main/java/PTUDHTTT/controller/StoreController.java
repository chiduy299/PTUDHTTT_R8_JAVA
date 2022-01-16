package PTUDHTTT.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import PTUDHTTT.model.ApiResponse;
import PTUDHTTT.model.Product;
import PTUDHTTT.model.Store;
import PTUDHTTT.repository.StoreRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class StoreController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private StoreRepository Prepo;
	// neu ko co area => get_all
	// neu co => get theo area
	
	@GetMapping("/store/get_by_area")
	public ResponseEntity<ApiResponse<List<Store>>> GetAllStorerByAreaType(@RequestParam String area_id) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("area_type").is(area_id));
			List<Store> storelst = mongoTemplate.find(query, Store.class);
			if (storelst.isEmpty()) { 
				List<Store> storelst_temp = new ArrayList<Store>();
				Prepo.findAll().forEach(storelst_temp::add);
				ApiResponse<List<Store>> resp_temp = new ApiResponse<List<Store>>(0,"Success",storelst_temp);
				return new ResponseEntity<>(resp_temp, HttpStatus.OK);
			}
			ApiResponse<List<Store>> resp = new ApiResponse<List<Store>>(0,"Success",storelst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/store/search_by_name")
	public ResponseEntity<ApiResponse<List<Store>>> GetStoreByName(@RequestParam String name) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").regex(name,"i"));
			List<Store> storelst = mongoTemplate.find(query, Store.class);
			if (storelst.isEmpty()) { 
				ApiResponse<List<Store>> resp = new ApiResponse<List<Store>>(0,"Success",storelst);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}
			ApiResponse<List<Store>> resp = new ApiResponse<List<Store>>(0,"Success",storelst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/store/get_by_id")
	public ResponseEntity<ApiResponse<Optional<Store>>> GetStoreById(@RequestParam String store_id) {
		try {
			Optional<Store> storelst = Prepo.findById(store_id);
			if (storelst.isPresent() == false) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<Optional<Store>> resp = new ApiResponse<Optional<Store>>(0,"Success",storelst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
