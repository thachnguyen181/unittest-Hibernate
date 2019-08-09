package tma.com.service;

import java.util.List;

import tma.com.dto.CustomerGroupDTO;
import tma.com.model.CustomerGroup;

public interface ICustomerGroupService {
	
	public List<CustomerGroupDTO> getAll();
	public CustomerGroup getById(int id);
	public CustomerGroup insert(CustomerGroupDTO customerGroupDto);
	public CustomerGroupDTO getByName(String name);
}
