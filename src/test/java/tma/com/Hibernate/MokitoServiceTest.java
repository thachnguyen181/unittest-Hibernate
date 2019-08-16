package tma.com.Hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import tma.com.controller.CustomerGroupController;
import tma.com.dto.CustomerGroupDTO;
import tma.com.model.CustomerGroup;
import tma.com.repository.ICustomerGroupRepository;
import tma.com.service.CustomerGroupService;

@RunWith(MockitoJUnitRunner.class)
public class MokitoServiceTest {
	
	@InjectMocks
	private CustomerGroupService customerGroupService;
	
	@Mock
	private ICustomerGroupRepository customerGroupRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*@Test
	public void testGetCustomerGroupByName() {
		CustomerGroupDTO customerGroupDto = new CustomerGroupDTO();
		customerGroupDto.setName("testtest");
		when(customerGroupService.getByName("testtest")).thenReturn(customerGroupDto);
		
	}*/
	
	@Test
	public void testGetCustomerGroupByName() {
		CustomerGroup customerGroup = new CustomerGroup();
		customerGroup.setName("testtest");
		when(customerGroupRepository.getByName("testtest")).thenReturn(customerGroup);
		CustomerGroupDTO customerGroupDto = customerGroupService.getByName("testtest");
		verify(customerGroupRepository.getByName("testtest"));
		assertEquals("testtest", customerGroupDto.getName());
	}
}
