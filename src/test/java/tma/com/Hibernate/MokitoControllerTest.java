package tma.com.Hibernate;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import tma.com.controller.CustomerGroupController;
import tma.com.dto.CustomerGroupDTO;
import tma.com.service.CustomerGroupService;

public class MokitoControllerTest {
	
	@InjectMocks
	private CustomerGroupController customerGroupController;
	
	@Mock
	private CustomerGroupService customerGroupService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetCustomerGroupByName() {
		ResponseEntity<CustomerGroupDTO> customerGroupDto = new ResponseEntity<CustomerGroupDTO>(null);
		((CustomerGroupDTO) customerGroupDto).setName("testtest");
		when(customerGroupController.getByName("testtest")).thenReturn(customerGroupDto);
		CustomerGroup customerGroup = CustomerGroupController.get("name");
	}
}
