package PTUDHTTT.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import PTUDHTTT.model.Shipper;
import PTUDHTTT.repository.ShipperRepository;

@RestController
@RequestMapping("/api")
public class ShipperController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ShipperRepository Prepo;
	
	// neu ko co area => get_all
	// neu co => get theo area
	@GetMapping("/shipper/get_by_area")
	public ResponseEntity<ApiResponse<List<Shipper>>> GetAllShipperByAreaType(@RequestParam String area_id) {
		try {
			System.out.println(area_id);
			Query query = new Query();
			query.addCriteria(Criteria.where("area_type").is(area_id));
			List<Shipper> shipperlst = mongoTemplate.find(query, Shipper.class);
			if (shipperlst.isEmpty()) { 
				List<Shipper> shipperlst_temp = new ArrayList<Shipper>();
				Prepo.findAll().forEach(shipperlst_temp::add);
				ApiResponse<List<Shipper>> resp_temp = new ApiResponse<List<Shipper>>(0,"Success",shipperlst_temp);
				return new ResponseEntity<>(resp_temp, HttpStatus.OK);
			}
			ApiResponse<List<Shipper>> resp = new ApiResponse<List<Shipper>>(0,"Success",shipperlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
