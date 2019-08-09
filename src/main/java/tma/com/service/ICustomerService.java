package tma.com.service;

import java.util.List;

import tma.com.dto.CustomerDTO;
import tma.com.model.Customer;

public interface ICustomerService {
	
	public List<CustomerDTO> getAll();
	public Customer insert(CustomerDTO customerDto);
}
