package tma.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tma.com.dto.CustomerGroupDTO;
import tma.com.model.CustomerGroup;
import tma.com.repository.ICustomerGroupRepository;

@Service
public class CustomerGroupService implements ICustomerGroupService{
	
	@Autowired
	private ICustomerGroupRepository customerGroupRepository;

	public List<CustomerGroupDTO> getAll() {
		// TODO Auto-generated method stub
		
		ArrayList<CustomerGroupDTO> customerGroups = new ArrayList<CustomerGroupDTO>();
		for (CustomerGroup i : customerGroupRepository.findAll()) {
			CustomerGroupDTO customerGroupDto = new CustomerGroupDTO();
			customerGroupDto.setId(i.getId());
			customerGroupDto.setName(i.getName());
			customerGroups.add(customerGroupDto);
		}
		return customerGroups;
	}

	public CustomerGroup insert(CustomerGroupDTO customerGroupDto) {
		// TODO Auto-generated method stub
		
		CustomerGroup customerGroup = new CustomerGroup();
		//customerGroup.setId(customerGroupDto.getId());
		customerGroup.setName(customerGroupDto.getName());
		return customerGroupRepository.save(customerGroup);
	}

	public CustomerGroup getById(int id) {
		// TODO Auto-generated method stub
		//CustomerGroupDTO customerDto = new CustomerGroupDTO();
		
		CustomerGroup customerGroup = customerGroupRepository.findById(id).get();
		if (customerGroup != null) {
			return customerGroup;
		} else {
			return null;
		}
	}

	public CustomerGroupDTO getByName(String name) {
		// TODO Auto-generated method stub
		
		CustomerGroup customerGroup = customerGroupRepository.getByName(name);
		if (customerGroup == null) {
			return null;
		}
		CustomerGroupDTO customerGroupDto = new CustomerGroupDTO();
		customerGroupDto.setId(customerGroup.getId());
		customerGroupDto.setName(customerGroup.getName());
		return customerGroupDto;
	}
}
