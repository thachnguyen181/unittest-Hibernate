package tma.com.Hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tma.com.model.CustomerGroup;
import tma.com.repository.ICustomerGroupRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyJPAUnitTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ICustomerGroupRepository customerGroupRepository;
	
	@Test
	public void should_find_no_customergroups_if_repository_is_empty() {
		Iterable<CustomerGroup> customerGroups = customerGroupRepository.findAll();
		
		assertThat(customerGroups).isEmpty();
	}
	
	@Test
	public void should_store_a_customer() {
		CustomerGroup customerGroup = customerGroupRepository.save(new CustomerGroup("example"));
		
		assertThat(customerGroup).hasFieldOrPropertyWithValue("name", "example");
	}
	
	@Test
	public void should_find_all_customer() {
		CustomerGroup customerGroup1 = new CustomerGroup("example");
		entityManager.persist(customerGroup1);
		
		CustomerGroup customerGroup2 = new CustomerGroup("demo");
		entityManager.persist(customerGroup2);
		
		Iterable<CustomerGroup> customerGroups = customerGroupRepository.findAll();
		
		assertThat(customerGroups).hasSize(2).contains(customerGroup1, customerGroup2);
	}
	
	@Test
	public void should_find_customer_group_by_id() {
		CustomerGroup customerGroup1 = new CustomerGroup("example");
		entityManager.persist(customerGroup1);
		
		CustomerGroup customerGroup2 = new CustomerGroup("demo");
		entityManager.persist(customerGroup2);
		
		CustomerGroup foundCustomerGroup = customerGroupRepository.findById(customerGroup2.getId()).get();
		
		assertThat(foundCustomerGroup).isEqualTo(customerGroup2);
	}
}
