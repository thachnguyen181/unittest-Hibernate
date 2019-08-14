package tma.com.Hibernate;

import static org.hamcrest.CoreMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tma.com.controller.CustomerGroupController;
import tma.com.dto.CustomerGroupDTO;
import tma.com.model.Customer;
import tma.com.model.CustomerGroup;
import tma.com.service.CustomerGroupService;

@RunWith(SpringRunner.class)
//@WebMvcTest(value = CustomerGroupController.class, secure = false)
public class CustomerGroupControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerGroupService customerGroupService;
	
	//Customer mockCustomer = new Customer("abc", "def");
	
	@Test
	public void getCustomerGroupByName() throws Exception {
		Mockito.when(customerGroupService.getByName(Mockito.anyString())).thenReturn(new CustomerGroupDTO("test"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customergroups").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		JSONAssert.assertEquals("{id:8, name:test}", result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createCustomerGroup() throws Exception {
		CustomerGroup mockCustomerGroup = new CustomerGroup();
		CustomerGroupDTO customerGroupDto = new CustomerGroupDTO();
		customerGroupDto.setName(Mockito.anyString());
		//Mockito.when(CustomerGroupService.insert(customerGroupDto)).thenReturn(mockCustomerGroup);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/customergroups").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:9, name:test1}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
