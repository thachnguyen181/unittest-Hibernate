package tma.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tma.com.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	
}
