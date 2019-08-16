package tma.com.Hibernate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tma.com.controller.CustomerGroupController;
import tma.com.dto.CustomerGroupDTO;
import tma.com.model.CustomerGroup;
import tma.com.service.CustomerGroupService;

public class CustomerGroupControllerUnitTest {

	private MockMvc mockMvc;

	@Mock
	private CustomerGroupService customerGroupService;

	@InjectMocks
	private CustomerGroupController customerGroupController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerGroupController).addFilters(new CORSFilter()).build();
	}

	@Test
	public void test_get_all_sucess() throws Exception {
		List<CustomerGroupDTO> customerGroups = Arrays.asList(
				new CustomerGroupDTO("bronze"),
				new CustomerGroupDTO("silver"),
				new CustomerGroupDTO("gold"));
		
		when(customerGroupService.getAll()).thenReturn(customerGroups);
		
		mockMvc.perform(get("/customergroups"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			//.andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
            .andExpect((ResultMatcher) jsonPath("$[0].name", is("bronze")))
            .andExpect((ResultMatcher) jsonPath("$[1].name", is("silver")))
            .andExpect((ResultMatcher) jsonPath("$[2].name", is("gold")));
		
		verify(customerGroupService, times(1)).getAll();
		verifyNoMoreInteractions(customerGroupService);
	}
	
	@Test
	public void test_get_by_id_success() throws Exception {
		CustomerGroup customerGroup = new CustomerGroup(1, "bronze");
		
		when(customerGroupService.getById(1)).thenReturn(customerGroup);
		
		mockMvc.perform(get("/customergroups/{id}", 1))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect((ResultMatcher) jsonPath("$.id", is(1)))
			.andExpect((ResultMatcher) jsonPath("$.name", is("bronze")));
		
		verify(customerGroupService, times(1)).getById(1);
        verifyNoMoreInteractions(customerGroupService);
	}
	
	@Test
	public void test_create_new_customer_group_success() throws Exception {
		CustomerGroupDTO customerGroupDto = new CustomerGroupDTO("test2");
		
		when(customerGroupService.exists(customerGroupDto)).thenReturn(false);
		doNothing().when(customerGroupService).insert(customerGroupDto);
		
		mockMvc.perform(
				post("/customergroups")
					.contentType(MediaType.APPLICATION_JSON))
					//.content(asJsonString(customerGroupDto)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost/customergroups/")));
		verify(customerGroupService, times(1)).exists(customerGroupDto);
		verifyNoMoreInteractions(customerGroupService);
	}
	
	@Test
	public void test_update_customer_group_success() throws Exception {
	    CustomerGroupDTO customerGroupDto = new CustomerGroupDTO("test2");
	    CustomerGroup customerGroup = new CustomerGroup(customerGroupDto.getName());
	    when(customerGroupService.getById(customerGroupDto.getId())).thenReturn(customerGroup);
	    doNothing().when(customerGroupService).update(customerGroupDto);
	    mockMvc.perform(
	            put("/customergroup/{id}", customerGroupDto.getId())
	                    .contentType(MediaType.APPLICATION_JSON))
	                    //.content(asJsonString(customerGroupDto)))
	            .andExpect(status().isOk());
	    verify(customerGroupService, times(1)).getById(customerGroupDto.getId());
	    verify(customerGroupService, times(1)).update(customerGroupDto);
	    verifyNoMoreInteractions(customerGroupService);
	}
	
	@Test
	public void test_delete_customer_group_success() throws Exception {
	    CustomerGroup customerGroup = new CustomerGroup(4, "TamNT");
	    when(customerGroupService.getById(customerGroup.getId())).thenReturn(customerGroup);
	    doNothing().when(customerGroupService).delete(customerGroup.getId());
	    mockMvc.perform(
	            delete("/customergroups/{id}", customerGroup.getId()))
	            .andExpect(status().isOk());
	    verify(customerGroupService, times(1)).getById(customerGroup.getId());
	    verify(customerGroupService, times(1)).delete(customerGroup.getId());
	    verifyNoMoreInteractions(customerGroupService);
	}
}
