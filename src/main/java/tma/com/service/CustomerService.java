package tma.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tma.com.dto.CustomerDTO;
import tma.com.model.Customer;
import tma.com.model.CustomerGroup;
import tma.com.repository.ICustomerGroupRepository;
import tma.com.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private ICustomerGroupRepository customerGroupRepository;
	
	@Autowired
	private ICustomerGroupService customerGroupService;
	
	public List<CustomerDTO> getAll() {
		// TODO Auto-generated method stub
		
		ArrayList<CustomerDTO> customerDtos = new ArrayList<CustomerDTO>();
		for (Customer i : customerRepository.findAll()) {
			CustomerDTO customerDto = new CustomerDTO();
			customerDto.setId(i.getId());
			customerDto.setName(i.getName());
			customerDto.setGroupId(i.getCustomerGroup().getId());
			customerDtos.add(customerDto);
		}
		return customerDtos;
	}
	
	public Customer insert(CustomerDTO customerDto) {
		// TODO Auto-generated method stub
		
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setAddress(customerDto.getAddress());
		customer.setCustomerGroup(getById(customerDto.getGroupId()));
		return customerRepository.save(customer);
	}
	
	public CustomerGroup getById(int id) {
		// TODO Auto-generated method stub
		
		CustomerGroup customerGroup = customerGroupRepository.findById(id).get();
		if (customerGroup != null) {
			return customerGroup;
		} else {
			return null;
		}
	}
}
