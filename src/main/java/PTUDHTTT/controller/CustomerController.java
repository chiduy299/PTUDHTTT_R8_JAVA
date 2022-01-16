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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import PTUDHTTT.model.ApiResponse;
import PTUDHTTT.model.Customer;
import PTUDHTTT.model.Product;
import PTUDHTTT.repository.CustomerRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CustomerRepository Prepo;
	
	// neu ko co area => get_all
	// neu co => get theo area
	@GetMapping("/customer/get_by_area")
	public ResponseEntity<ApiResponse<List<Customer>>> GetAllCustomerByAreaType(@RequestParam String area_id) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("area_type").is(area_id));
			List<Customer> customerlst = mongoTemplate.find(query, Customer.class);
			if (customerlst.isEmpty()) { 
				List<Customer> customerlst_temp = new ArrayList<Customer>();
				Prepo.findAll().forEach(customerlst_temp::add);
				ApiResponse<List<Customer>> resp_temp = new ApiResponse<List<Customer>>(0,"Success",customerlst_temp);
				return new ResponseEntity<>(resp_temp, HttpStatus.OK);
			}
			ApiResponse<List<Customer>> resp = new ApiResponse<List<Customer>>(0,"Success",customerlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer/search_by_name")
	public ResponseEntity<ApiResponse<List<Customer>>> GetCustomerByName(@RequestParam String name) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").regex(name,"i"));
			List<Customer> customerlst = mongoTemplate.find(query, Customer.class);
			if (customerlst.isEmpty()) { 
				ApiResponse<List<Customer>> resp = new ApiResponse<List<Customer>>(0,"Success",customerlst);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}
			ApiResponse<List<Customer>> resp = new ApiResponse<List<Customer>>(0,"Success",customerlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer/search_by_phone")
	public ResponseEntity<ApiResponse<List<Customer>>> GetCustomerByPhone(@RequestParam String phone_number) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("phone").regex(phone_number,"i"));
			List<Customer> customerlst = mongoTemplate.find(query, Customer.class);
			if (customerlst.isEmpty()) { 
				ApiResponse<List<Customer>> resp = new ApiResponse<List<Customer>>(0,"Success",customerlst);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}
			ApiResponse<List<Customer>> resp = new ApiResponse<List<Customer>>(0,"Success",customerlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/customer/disable_enable")
	public ResponseEntity<ApiResponse<Customer>> DisableEnableCustomer(@RequestParam String request_id) {
		try {
			Optional<Customer> customer = Prepo.findById(request_id);
			if (customer.isPresent() == false) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			Customer cus = customer.get();
			if (cus.isIs_enabled() == true)
				cus.setIs_enabled(false);
			else
				cus.setIs_enabled(true);
			Customer cus_new = Prepo.save(cus);
			ApiResponse<Customer> resp = new ApiResponse<Customer>(0,"Success",cus_new);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}