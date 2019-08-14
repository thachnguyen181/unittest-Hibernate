package tma.com.Hibernate;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
			.andExpect((ResultMatcher)content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect((ResultMatcher) jsonPath("$.id", is(1)))
			.andExpect((ResultMatcher) jsonPath("$.name", is("bronze")));
		
		verify(customerGroupService, times(1)).getById(1);
        verifyNoMoreInteractions(customerGroupService);
	}
}
