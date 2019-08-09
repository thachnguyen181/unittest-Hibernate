package tma.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tma.com.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

}
