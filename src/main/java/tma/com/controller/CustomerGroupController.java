package tma.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tma.com.dto.CustomerGroupDTO;
import tma.com.model.CustomerGroup;
import tma.com.service.ICustomerGroupService;

@RestController
public class CustomerGroupController {
	
	@Autowired
	private ICustomerGroupService customerGroupService;
	
	@GetMapping(value = "/customergroups")
	public ResponseEntity<List<CustomerGroupDTO>> getAll() {
		return new ResponseEntity<List<CustomerGroupDTO>>(customerGroupService.getAll(), HttpStatus.OK); 
	}
	
	@GetMapping(value = "/customergroups/{id}")
	public ResponseEntity<CustomerGroup> getById(@PathVariable int id) {
		CustomerGroup customerGroup = customerGroupService.getById(id);
		return new ResponseEntity<CustomerGroup>(customerGroup, HttpStatus.OK);
	}	
	
	@PostMapping(value = "/customergroups")
	public ResponseEntity<CustomerGroup> createNew(@RequestBody CustomerGroupDTO customerGroupDto) {
		CustomerGroup customerGroupCreated = new CustomerGroup();
		if (customerGroupService.exists(customerGroupDto)) {
			return new ResponseEntity<CustomerGroup>(customerGroupCreated, HttpStatus.BAD_REQUEST);
		}
		customerGroupCreated = customerGroupService.insert(customerGroupDto);
		return new ResponseEntity<CustomerGroup>(customerGroupCreated, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/customergroup")
	public ResponseEntity<CustomerGroupDTO> getByName(@RequestParam String name) {		
		CustomerGroupDTO customerGroupDto = customerGroupService.getByName(name);
		return new ResponseEntity<CustomerGroupDTO>(customerGroupDto, HttpStatus.OK);
	}
	
	@PutMapping(value = "/customergroups")
	public ResponseEntity<String> update(@RequestBody CustomerGroupDTO customerGroupDto) {
		if (customerGroupService.update(customerGroupDto)) {
			return new ResponseEntity<String> ("Edited!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String> ("Failure!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/customergroups/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		if (customerGroupService.delete(id)) {
			return new ResponseEntity<String> ("Delete!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String> ("Id is not exist!", HttpStatus.NOT_FOUND);
		}
	}
}
