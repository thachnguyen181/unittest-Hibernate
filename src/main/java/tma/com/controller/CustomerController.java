package tma.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tma.com.dto.CustomerDTO;
import tma.com.model.Customer;
import tma.com.service.ICustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping(value = "/customers")
	public ResponseEntity<List<CustomerDTO>> getAll() {
		return new ResponseEntity<List<CustomerDTO>>(customerService.getAll(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/customers")
	public ResponseEntity<Customer> insert(@RequestBody CustomerDTO customerDto) {
		Customer customer = customerService.insert(customerDto);
		if (customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.BAD_REQUEST);
		}
	}
}
