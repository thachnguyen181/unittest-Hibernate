package tma.com.Hibernate;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import tma.com.repository.ICustomerGroupRepository;
import tma.com.service.CustomerGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerGroupServiceTest {
	
	@MockBean
	private ICustomerGroupRepository customerGroupRepository;
	
	@Autowired
	private CustomerGroupService customerGroupService;
	
	/*@Before
	public void setUp() {
		Mockito.when(customerGroupRepository.findAll())
        .thenReturn(IntStream.range(0, 10)
                             .mapToObj(i -> new CustomerGroup("" + i))
                             .collect(Collectors.toList()));
	}*/
}
