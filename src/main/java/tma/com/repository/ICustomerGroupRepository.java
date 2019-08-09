package tma.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tma.com.model.CustomerGroup;

public interface ICustomerGroupRepository extends JpaRepository<CustomerGroup, Integer>{

	@Query("SELECT g FROM CustomerGroup g WHERE g.name = :name")
	CustomerGroup getByName(@Param("name") String name);
}
