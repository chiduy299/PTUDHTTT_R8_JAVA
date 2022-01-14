package PTUDHTTT.controller;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import PTUDHTTT.model.ApiResponse;
import PTUDHTTT.model.Product;
import PTUDHTTT.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ProductRepository Prepo;
	
	@GetMapping("/product/get_all")
	public ResponseEntity<ApiResponse<List<Product>>> GetAllProduct(@RequestParam(required=false,defaultValue = "0")int page, @RequestParam(required=false,defaultValue = "0") int limit) {
		try {			
			Query query = new Query().skip(page*limit).limit(limit);
			List<Product> productlst = mongoTemplate.find(query, Product.class);
			if (productlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<Product>> resp = new ApiResponse<List<Product>>(0,"Success",productlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			//System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/search")
	public ResponseEntity<ApiResponse<List<Product>>> SearchProductByName(@RequestParam String name,@RequestParam(required=false,defaultValue = "0") int page,@RequestParam(required=false,defaultValue = "0") int limit) {
		try {
			if (name.length() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
			}
			Query query = new Query().skip(page*limit).limit(limit);
			query.addCriteria(Criteria.where("product_name").regex(name,"i"));
			List<Product> productlst = mongoTemplate.find(query, Product.class);
			if (productlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<Product>> resp = new ApiResponse<List<Product>>(0,"Success",productlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/get_product_by_id")
	public ResponseEntity<ApiResponse<Optional<Product>>> GetProductById(@RequestParam String id) {
		try {
			Optional<Product> productlst = Prepo.findById(id);
			if (productlst.isPresent() == false) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<Optional<Product>> resp = new ApiResponse<Optional<Product>>(0,"Success",productlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/get_essential_product")
	public ResponseEntity<ApiResponse<List<Product>>> GetEssentialProduct(@RequestParam(required=false,defaultValue = "0")int page, @RequestParam(required=false,defaultValue = "0") int limit) {
		try {
			Query query = new Query().skip(page*limit).limit(limit);
			query.with(Sort.by(Sort.Direction.DESC, "sell_number"));
			List<Product> productlst = mongoTemplate.find(query,Product.class);
			if (productlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<Product>> resp = new ApiResponse<List<Product>>(0,"Success",productlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/get_product_by_date")
	public ResponseEntity<ApiResponse<List<Product>>> GetProductByDate(@RequestParam String from_date, @RequestParam String to_date, @RequestParam(required=false,defaultValue = "0")int page, @RequestParam(required=false,defaultValue = "0") int limit) {
		try {
	        //default, ISO_LOCAL_DATE
	        ZoneId zoneId = ZoneId.systemDefault();
	        LocalDate local_from_date = LocalDate.parse(from_date);
	        LocalDate local_to_date = LocalDate.parse(to_date);
	        long epoch_from_date = local_from_date.atStartOfDay(zoneId).toEpochSecond();
	        long epoch_to_date = local_to_date.atStartOfDay(zoneId).toEpochSecond();
	        
	        Query query=Query.query( new Criteria().andOperator(
	        		Criteria.where("create_at").gte(epoch_from_date),
	        		Criteria.where("create_at").lte(epoch_to_date)
	                )).skip(page*limit).limit(limit);
			List<Product> productlst = mongoTemplate.find(query, Product.class);
			ApiResponse<List<Product>> resp = new ApiResponse<List<Product>>(0,"Success",productlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/get_product_by_store_id")
	public ResponseEntity<ApiResponse<List<Product>>> GetProductByStore(@RequestParam String id_store, @RequestParam(required=false,defaultValue = "0")int page, @RequestParam(required=false,defaultValue = "0") int limit) {
		try {
			Query query = new Query().skip(page*limit).limit(limit);
			query.addCriteria(Criteria.where("store_id").is(id_store));
			List<Product> productlst = mongoTemplate.find(query, Product.class);
			if (productlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<Product>> resp = new ApiResponse<List<Product>>(0,"Success",productlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
